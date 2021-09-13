package io.springbatch.springbatchlecture.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ApiEndTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        System.out.println("");
        System.out.println(">> ApiEndTasklet is started");
        System.out.println("");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("*                                                               Spring Batch is completed                                                            *");
        System.out.println("******************************************************************************************************************************************************");
        System.out.println("");

        return RepeatStatus.FINISHED;
    }
}
