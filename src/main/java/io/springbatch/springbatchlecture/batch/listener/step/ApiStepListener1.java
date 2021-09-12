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

@Component("apiStepListener1")
@RequiredArgsConstructor
public class ApiStepListener1 implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

        System.out.println("");
        System.out.println("################################################################## 1번 서식 데이터 처리 시작 ##################################################################");
        System.out.println("");

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        System.out.println("");
        System.out.println("################################################################## 1번 서식 데이터 처리 종료 ##################################################################");
        System.out.println("");

        return null;
    }
}
