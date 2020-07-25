package com.biblio.microservicebatch.config;

import com.biblio.microservicebatch.steps.StepGetCopyOfDocument;
import com.biblio.microservicebatch.steps.StepGetLoans;
import com.biblio.microservicebatch.steps.StepGetUser;
import com.biblio.microservicebatch.steps.StepMailSend;
import com.biblio.microservicebatch.steps.reservation.StepGetReservations;
import com.biblio.microservicebatch.steps.reservation.StepMailSendReservation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TaskletReservationMails {


        private JobBuilderFactory jobs;
        private StepBuilderFactory steps;
        private JobLauncher jobLauncher;

        private StepGetReservations stepGetReservations;
        private StepMailSendReservation stepMailSendReservation;

        public TaskletReservationMails(JobBuilderFactory jobs, StepBuilderFactory steps, JobLauncher jobLauncher, StepGetReservations stepGetReservations, StepMailSendReservation stepMailSendReservation) {
            this.jobs = jobs;
            this.steps = steps;
            this.jobLauncher = jobLauncher;
            this.stepGetReservations = stepGetReservations;
            this.stepMailSendReservation = stepMailSendReservation;
        }


        @Bean
        protected Step getReservations() {
            return steps.get("getReservations").tasklet(stepGetReservations).build();
        }

        @Bean
        protected Step mailSend() {
            return steps.get("stepMailSendReservation").tasklet(stepMailSendReservation).build();
        }

        @Bean
        public Job job() {
            return jobs
                    .get("TaskletReservationMails")
                    .start(getReservations())
                    .next(mailSend())
                    .build();
        }


        @Scheduled(cron = "0 0 0 * * ?")
        public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
            jobLauncher.run(
                    job(),
                    new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters()
            );
        }
    }