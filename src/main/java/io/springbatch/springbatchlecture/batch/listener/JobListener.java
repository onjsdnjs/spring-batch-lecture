package io.springbatch.springbatchlecture.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * <pre>
 * io.anymobi.core.batch.listener.job
 * ㄴ DataSendJobListener.java
 * </pre>
 * 배치 Job 이 실행되면 호출되는 JobExecutionListener
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:36
 * @see :
 **/

public class JobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.out.println("");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("*                                                               DataSendChildJobListener is started                                                              *");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("");
        System.out.println(">> 2-1. DataSendChildJobListener is started");
        System.out.println("");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
