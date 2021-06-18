package io.springbatch.springbatchlecture;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobRepositoryListener implements JobExecutionListener {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        String jobName = jobExecution.getJobInstance().getJobName();
        JobParameters jobParameters = new JobParametersBuilder().addString("requestDate", "20210102").toJobParameters();

        JobInstance jobInstance = jobRepository.createJobInstance(jobName,jobParameters);
        System.out.println("jobInstance = " + jobInstance);

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
        System.out.println("lastJobExecution = " + lastJobExecution);

    }
}
