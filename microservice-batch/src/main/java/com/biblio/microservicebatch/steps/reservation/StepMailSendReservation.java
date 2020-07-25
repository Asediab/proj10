package com.biblio.microservicebatch.steps.reservation;

import com.biblio.microservicebatch.mail.MailSender;
import com.biblio.microservicebatch.mail.MailSenderImplReservation;
import com.biblio.microservicebatch.model.Loan;
import com.biblio.microservicebatch.model.ReservationDTO;
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
import java.util.List;

@Component
public class StepMailSendReservation implements Tasklet, StepExecutionListener {

    private List<ReservationDTO> reservationDTOS;

    @Autowired
    private MailSenderImplReservation mailSender;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        this.reservationDTOS = (List<ReservationDTO>) executionContext.get("reservationDTOS");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for (ReservationDTO reservation : reservationDTOS) {
            mailSender.accept(reservation);
        }
        return RepeatStatus.FINISHED;
    }
}
