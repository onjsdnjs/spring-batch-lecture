package io.springbatch.springbatchlecture;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;

import java.util.Collection;

public class JobResultListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        System.out.println("");
        System.out.println("=================================== Job name : " + jobExecution.getJobInstance().getJobName() + " ===============================================");
        System.out.println("jobExecution.getJobInstance().getInstanceId() : " + jobExecution.getJobInstance().getInstanceId());
        System.out.println("jobExecution.getStatus() : " + jobExecution.getStatus());
        System.out.println("jobExecution.getExitStatus() : " + jobExecution.getExitStatus());
        System.out.println("jobExecution.getCreateTime() : " + jobExecution.getCreateTime());
        System.out.println("jobExecution.getStartTime() : " + jobExecution.getStartTime());
        System.out.println("jobExecution.getEndTime() : " + jobExecution.getEndTime());
        System.out.println("jobExecution.getExecutionContext : " + jobExecution.getExecutionContext());
        System.out.println("jobExecution.getJobParameters().getParameters() : " + jobExecution.getJobParameters().getParameters());

        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        for (StepExecution stepExecution : stepExecutions) {
            System.out.println("");
            System.out.println("================================= Step name : " + stepExecution.getStepName() + " =====================================================");
            System.out.println("stepExecution.getStatus() : " + stepExecution.getStatus());
            System.out.println("stepExecution.getExitStatus() : " + stepExecution.getExitStatus());
            System.out.println("stepExecution.getCommitCount() : " + stepExecution.getCommitCount());
            System.out.println("stepExecution.getRollbackCount() : " + stepExecution.getRollbackCount());
            System.out.println("stepExecution.getReadCount() : " + stepExecution.getReadCount());
            System.out.println("stepExecution.getReadSkipCount() : " + stepExecution.getReadSkipCount());
            System.out.println("stepExecution.getStartTime() : " + stepExecution.getStartTime());
            System.out.println("stepExecution.getEndTime() : " + stepExecution.getEndTime());
            System.out.println("stepExecution.getWriteCount() : " + stepExecution.getWriteCount());
            System.out.println("stepExecution.getWriteSkipCount() : " + stepExecution.getWriteSkipCount());
            System.out.println("stepExecution.getProcessSkipCount() : " + stepExecution.getProcessSkipCount());
            System.out.println("stepExecution.getSkipCount() : " + stepExecution.getSkipCount());
            System.out.println("stepExecution.getExecutionContext() : " + stepExecution.getExecutionContext());
        }

        System.out.println("");
    }
}
