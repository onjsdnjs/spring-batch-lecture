package io.springbatch.springbatchlecture.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * io.anymobi.core.batch.tasklet
 * ㄴ ServiceEndTasklet.java
 * </pre>
 * 서비스가 종료 되는 시점에 실행 되어야 할 로직을 담는 클래스
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:48
 * @see :
 **/

@Component
@RequiredArgsConstructor
public class ApiEndTasklet implements Tasklet {

//    private final BeanObjectFactory serviceBeanFactory;

    /**
     * 모든 배치 작업이 완료 된 후 후속 작업을 처리함
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println("");
        System.out.println(">> 11.AfterTasklet is started");
        System.out.println("");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("*                                                               Spring Batch is completed                                                            *");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("");

//        AbstractDataService<List<? extends ApiRequestVO>> service = serviceBeanFactory.getService(Constants.TASK_CD.TASK_DATA_SND);
//        service.afterExecute(service);

        return RepeatStatus.FINISHED;
    }
}
