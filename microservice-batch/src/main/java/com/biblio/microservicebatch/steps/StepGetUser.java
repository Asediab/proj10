package com.biblio.microservicebatch.steps;

import com.biblio.microservicebatch.model.Loan;
import com.biblio.microservicebatch.proxy.MicroserviceUserProxy;
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
public class StepGetUser implements Tasklet, StepExecutionListener {

    private ArrayList<Loan> loanListAndUser;

    @Autowired
    private MicroserviceUserProxy userProxy;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        this.loanListAndUser = (ArrayList<Loan>) executionContext.get("loansList");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution.getJobExecution().getExecutionContext().put("loanListAndUser", loanListAndUser);
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for (Loan loan : loanListAndUser) {
            loan.setUser(userProxy.getUserById(loan.getUserId()));
        }
        return RepeatStatus.FINISHED;
    }
}
