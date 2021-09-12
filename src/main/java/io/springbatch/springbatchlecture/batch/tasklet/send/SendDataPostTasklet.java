package io.springbatch.springbatchlecture.batch.tasklet.send;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * io.anymobi.core.batch.tasklet.send
 * ㄴ SendDataPostTasklet.java
 * </pre>
 * Data 를 전송하는 역할을 맡은 SendDataTasklet 실행 전에 호출되는 Tasklet 클래스
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:42
 * @see :
 **/

@Component
@RequiredArgsConstructor
public class SendDataPostTasklet implements Tasklet {

//    private final BeanObjectFactory serviceBeanFactory;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println("");
        System.out.println(">> 10.SendDataPostTasklet is started");
        System.out.println("");

//        AbstractDataService<List<? extends ApiRequestVO>> service = serviceBeanFactory.getService(Constants.TASK_CD.TASK_DATA_SND);

//        service.postExecute(service.getServiceVO());

        return RepeatStatus.FINISHED;
    }
}
