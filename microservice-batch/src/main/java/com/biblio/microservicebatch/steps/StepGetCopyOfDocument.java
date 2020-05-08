package com.biblio.microservicebatch.steps;

import com.biblio.microservicebatch.model.Loan;
import com.biblio.microservicebatch.proxy.MicroserviceDocumentProxy;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StepGetCopyOfDocument implements Tasklet, StepExecutionListener {

    private ArrayList<Loan> loanListAndUserAndDoc;

    @Autowired
    private MicroserviceDocumentProxy documentProxy;


    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        this.loanListAndUserAndDoc = (ArrayList<Loan>) executionContext.get("loanListAndUser");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution.getJobExecution().getExecutionContext().put("loanListAndUserAndDoc", loanListAndUserAndDoc);
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for (Loan loan : loanListAndUserAndDoc) {
            loan.setCopyOfDocument(documentProxy.getDocumentByID(loan.getCopyOfDocumentId()));
        }
        return RepeatStatus.FINISHED;
    }
}
