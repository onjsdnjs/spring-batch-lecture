package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class StartNextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("batchJob")
                .start(flowA())
                .next(step3())
                .next(flowB())
                .next(step6())
                .end()
                .build();
    }

    @Bean
    public Flow flowA() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowA");
        flowBuilder.start(step1())
                .next(step2())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Flow flowB() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowB");
        flowBuilder.start(step4())
                .next(step5())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(">> step1 has executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> step2 has executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(">> step3 has executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step4() {
        return stepBuilderFactory.get("step4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(">> step4 has executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step5() {
        return stepBuilderFactory.get("step5")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(">> step5 has executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step step6() {
        return stepBuilderFactory.get("step6")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println(">> step6 has executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

}
