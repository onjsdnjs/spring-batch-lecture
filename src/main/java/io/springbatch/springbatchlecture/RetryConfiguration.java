package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.LimitCheckingItemSkipPolicy;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class RetryConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
//                .noSkip(SkippableException.class) // 아래 설정이 위의 설정을 덮어씀, skip() 설정이 우선
                .skipPolicy(limitCheckingItemSkipPolicy())
//                .skip(SkippableException.class)
//                .skipLimit(2)
//                .noRollback(SkippableException.class)
                .build();
    }

    @Bean
    public LimitCheckingItemSkipPolicy limitCheckingItemSkipPolicy(){

        Map<Class<? extends Throwable>, Boolean> skippableExceptionClasses = new HashMap<>();
        skippableExceptionClasses.put(RetryableException.class, true);

        LimitCheckingItemSkipPolicy limitCheckingItemSkipPolicy = new LimitCheckingItemSkipPolicy(2, skippableExceptionClasses);

        return limitCheckingItemSkipPolicy;
    }

    @Bean
    public ListItemReader<String> reader() {

        List<String> items = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            items.add(String.valueOf(i));
        }

        return new ListItemReader<>(items);
    }

    @Bean
    @StepScope
    public RetryItemProcessor processor() {
        RetryItemProcessor processor = new RetryItemProcessor();
        return processor;
    }

    @Bean
    @StepScope
    public RetryItemWriter writer() {
        RetryItemWriter writer = new RetryItemWriter();
        return writer;
    }
}

