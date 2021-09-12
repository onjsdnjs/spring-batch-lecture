package io.springbatch.springbatchlecture.batch.listener.step;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DataSendStepListener implements StepExecutionListener {

//    private final BeanObjectFactory serviceBeanFactory;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        System.out.println("");
        System.out.println(">> 6.SendDataListener is started");
        System.out.println("");

//        AbstractDataService<List<? extends ApiRequestVO>> service = serviceBeanFactory.getService(Constants.TASK_CD.TASK_DATA_SND);

//        boolean checkRun = service.beforeExecute();
//        if (!checkRun) throw new IllegalStateException("SendData can not run");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
