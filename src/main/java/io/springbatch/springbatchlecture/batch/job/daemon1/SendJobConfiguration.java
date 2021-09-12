package io.springbatch.springbatchlecture.batch.job.daemon1;

import io.springbatch.springbatchlecture.batch.tasklet.ApiEndTasklet;
import io.springbatch.springbatchlecture.batch.tasklet.ApiStartTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SendJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ApiStartTasklet apiStartTasklet;
    private final ApiEndTasklet apiEndTasklet;
    private final Step jobStep;

    @Bean
    public Job apiJob() throws Exception {

        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .on("FAILED").end()
                .from(step1()).on("*").to(jobStep)
                .from(jobStep).on("FAILED").end()
                .next(step2())
                .end()
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .tasklet(apiStartTasklet)
                .build();
    }

    @Bean
    public Step step2() throws Exception {
        return stepBuilderFactory.get("step2")
                .tasklet(apiEndTasklet)
                .build();
    }
}
