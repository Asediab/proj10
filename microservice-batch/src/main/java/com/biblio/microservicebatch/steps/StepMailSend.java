package com.biblio.microservicebatch.steps;

import com.biblio.microservicebatch.mail.MailSender;
import com.biblio.microservicebatch.model.Loan;
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
public class StepMailSend implements Tasklet, StepExecutionListener {

    private ArrayList<Loan> finalLoanList;

    @Autowired
    private MailSender mailSender;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        this.finalLoanList = (ArrayList<Loan>) executionContext.get("loanListAndUserAndDoc");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for (Loan loan : finalLoanList) {
            mailSender.accept(loan);
        }
        return RepeatStatus.FINISHED;
    }
}
