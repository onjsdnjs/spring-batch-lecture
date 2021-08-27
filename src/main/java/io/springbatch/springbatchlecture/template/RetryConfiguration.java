/*
package io.springbatch.springbatchlecture.template;

import io.springbatch.springbatchlecture.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

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
        return jobBuilderFactory.get("batchJob3")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<String, Customer>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
//                .skip(RetryableException.class)
//                .skipLimit(1)
                .build();
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
    public ItemProcessor processor() {
        RetryItemProcessor2 processor = new RetryItemProcessor2();
        return processor;
    }

    @Bean
    public ItemWriter writer() {
        RetryItemWriter2 writer = new RetryItemWriter2();
        return writer;
    }

    @Bean
    public RetryTemplate retryTemplate() {

        Map<Class<? extends Throwable>, Boolean> retryableExceptionClasses = new HashMap<>();
        retryableExceptionClasses.put(RetryableException.class, true);
        retryableExceptionClasses.put(RuntimeException.class, true);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(2000); //지정한 시간만큼 대기후 재시도 한다.

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(2,retryableExceptionClasses);
        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}

*/
