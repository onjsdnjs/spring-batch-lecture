package io.springbatch.springbatchlecture;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {

    private long sum = 0;
    private Object lock = new Object();

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

//        Thread.sleep(1000);

        synchronized (lock) {
            for (int i = 0; i < 1000000000; i++) {
                sum++;
            }
            System.out.println(String.format("%s has been executed on thread %s", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName()));
            System.out.println(String.format("sum : %d", sum));
        }
        return RepeatStatus.FINISHED;
    }
}
