package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@RequiredArgsConstructor
@Configuration
public class ParallelStepConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {

        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("split");

        Flow flow = flowBuilder.split(taskExecutor())
                .add(flow1(), flow2())
                .end();

        return jobBuilderFactory.get("batchJob")
                .start(customStep1())
                .next(customStep2())
                .on("COMPLETED").to(flow)
                .end()
                .build();
    }

    @Bean
    public Step customStep1() {
        return stepBuilderFactory.get("customStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("customStep1 was executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step customStep2() {
        return stepBuilderFactory.get("customStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("customStep2 was executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Flow flow1() {
        return new FlowBuilder<Flow>("flow1")
                .start(stepBuilderFactory.get("step1")
                        .tasklet(tasklet()).build())
                .build();
    }

    @Bean
    public Flow flow2() {
        return new FlowBuilder<Flow>("flow2")
                .start(stepBuilderFactory.get("step2")
                        .tasklet(tasklet()).build())
                .next(stepBuilderFactory.get("step3")
                        .tasklet(tasklet()).build())
                .build();
    }

    @Bean
    public Tasklet tasklet() {
        return new CustomTasklet();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setThreadNamePrefix("parallel-thread-");
        return executor;
    }
}

