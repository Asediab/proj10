package com.biblio.microservicebatch.steps;

import com.biblio.microservicebatch.model.Loan;
import com.biblio.microservicebatch.proxy.MicroserviceLoanProxy;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StepGetLoans implements Tasklet, StepExecutionListener {

    private List<Loan> loanList;

    @Autowired
    private MicroserviceLoanProxy loanProxy;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.loanList = new ArrayList<>();
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        loanList = loanProxy.loansByDateOfExpiration();
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution.getJobExecution().getExecutionContext().put("loansList", loanList);
        return ExitStatus.COMPLETED;
    }
}
