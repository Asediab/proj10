package com.biblio.microservicebatch.config;

import com.biblio.microservicebatch.steps.StepGetCopyOfDocument;
import com.biblio.microservicebatch.steps.StepGetLoans;
import com.biblio.microservicebatch.steps.StepGetUser;
import com.biblio.microservicebatch.steps.StepMailSend;
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

//@Configuration
public class TaskletsConfig {

    private JobBuilderFactory jobs;
    private StepBuilderFactory steps;
    private JobLauncher jobLauncher;

    private StepGetCopyOfDocument stepGetCopyOfDocument;
    private StepGetLoans stepGetLoans;
    private StepGetUser stepGetUser;
    private StepMailSend stepMailSend;

    public TaskletsConfig(JobBuilderFactory jobs, StepBuilderFactory steps, JobLauncher jobLauncher, StepGetCopyOfDocument stepGetCopyOfDocument, StepGetLoans stepGetLoans, StepGetUser stepGetUser, StepMailSend stepMailSend) {
        this.jobs = jobs;
        this.steps = steps;
        this.jobLauncher = jobLauncher;
        this.stepGetCopyOfDocument = stepGetCopyOfDocument;
        this.stepGetLoans = stepGetLoans;
        this.stepGetUser = stepGetUser;
        this.stepMailSend = stepMailSend;
    }


    @Bean
    protected Step getLoans() {
        return steps.get("getLoans").tasklet(stepGetLoans).build();
    }

    @Bean
    protected Step getUser() {
        return steps.get("getUser").tasklet(stepGetUser).build();
    }

    @Bean
    protected Step getCopyOfDocument() {
        return steps.get("getCopyOfDocument").tasklet(stepGetCopyOfDocument).build();
    }

    @Bean
    protected Step mailSend() {
        return steps.get("mailSend").tasklet(stepMailSend).build();
    }

    @Bean
    public Job job() {
        return jobs
                .get("taskletsJob")
                .start(getLoans())
                .next(getUser())
                .next(getCopyOfDocument())
                .next(mailSend())
                .build();
    }


//    @Scheduled(cron = "0 0 0 * * ?")
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(
                job(),
                new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters()
        );
    }
}
