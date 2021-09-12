package io.springbatch.springbatchlecture.batch.listener.job;

import io.anymobi.domain.vo.ApiRequestVO;
import io.anymobi.domain.vo.SharedObject;
import io.anymobi.service.data.SendDataService;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.List;

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

public class DataSendJobListener implements JobExecutionListener {

    private SharedObject sharedObject;
    private SendDataService<List<? extends ApiRequestVO>> dataSenderService;

    public DataSendJobListener(SharedObject sharedObject, SendDataService<List<? extends ApiRequestVO>> dataSenderService) {

        this.sharedObject = sharedObject;
        this.dataSenderService = dataSenderService;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.out.println("");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("*                                                               Spring Batch is started                                                              *");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("");
        System.out.println(">> 2. DataSendJobListener is started");
        System.out.println("");

        sharedObject.setSharedObject("sendService", dataSenderService);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
