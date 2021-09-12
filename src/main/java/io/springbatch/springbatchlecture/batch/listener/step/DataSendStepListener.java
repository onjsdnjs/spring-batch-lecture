package io.springbatch.springbatchlecture.batch.listener.step;

import io.springbatch.springbatchlecture.service.SendDataService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * io.anymobi.core.batch.listener.step
 * ㄴ DataSendStepListener.java
 * </pre>
 * * Step 이 실행되면 호출되는 StepExecutionListener
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:39
 * @see :
 **/

@Component
public class DataSendStepListener implements StepExecutionListener {

    private final SendDataService sendDataService;

    public DataSendStepListener(SendDataService sendDataService) {
        this.sendDataService = sendDataService;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

        System.out.println("");
        System.out.println(">> 6.SendDataListener is started");
        System.out.println("");

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
