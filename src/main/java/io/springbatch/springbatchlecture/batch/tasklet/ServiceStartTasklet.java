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
 * ㄴ ServiceStartTasklet.java
 * </pre>
 * 서비스가 시작 되는 시점에 실행 되어야 할 로직을 담는 클래스
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:49
 * @see :
 **/

@Component
@RequiredArgsConstructor
public class ServiceStartTasklet implements Tasklet {

//    private final BeanObjectFactory serviceBeanFactory;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println("");
        System.out.println(">> 3. BeforeTasklet is started");
        System.out.println("");
//        AbstractDataService service = serviceBeanFactory.getService(Constants.TASK_CD.TASK_DATA_SND);

//        boolean isOk = service.beforeExecute();
//        if (!isOk) {
//            contribution.setExitStatus(ExitStatus.FAILED);
//        }

        return RepeatStatus.FINISHED;
    }
}
