package io.springbatch.springbatchlecture.batch.listener.job;

import io.springbatch.springbatchlecture.batch.domain.Constants;
import io.springbatch.springbatchlecture.batch.domain.MessageVO;
import io.springbatch.springbatchlecture.batch.domain.SharedObject;
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

public class DataSendChildJobListener implements JobExecutionListener {

    private SharedObject sharedObject;
    public DataSendChildJobListener(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.out.println("");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("*                                                               DataSendChildJobListener is started                                                              *");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("");
        System.out.println(">> 2-1. DataSendChildJobListener is started");
        System.out.println("");

        MessageVO[] msgList = (MessageVO[]) this.sharedObject.getSharedObject(Constants.MSG.MSG_CD);
        for (int i = 0; i < msgList.length; i++) {
            jobExecution.getExecutionContext().put(String.valueOf(i), msgList[i]);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
