package com.biblio.microservicebatch.steps.reservation;

import com.biblio.microservicebatch.model.ReservationDTO;
import com.biblio.microservicebatch.proxy.MicroserviceReservationProxy;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StepGetReservations implements Tasklet, StepExecutionListener {

    private List<ReservationDTO> reservationDTOS;

    @Autowired
    private MicroserviceReservationProxy reservationProxy;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.reservationDTOS = new ArrayList<>();
    }


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        reservationDTOS = reservationProxy.sendListMail();
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution.getJobExecution().getExecutionContext().put("reservationDTOS", reservationDTOS);
        return ExitStatus.COMPLETED;
    }
}
