package io.springbatch.springbatchlecture;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class StopWatchJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("==========================================");
        System.out.println("총 소요된 시간 : " + time);
        System.out.println("==========================================");
    }
}
