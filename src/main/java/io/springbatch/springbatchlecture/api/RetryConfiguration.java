package io.springbatch.springbatchlecture.api;

import io.springbatch.springbatchlecture.*;
import io.springbatch.springbatchlecture.template.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RetryConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob1")
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
                .skip(RetryableException.class)
                .skipLimit(3)
                .noRetry(NoRetryException.class)
                .retry(RetryableException.class)
                .retryLimit(2)
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
        RetryItemProcessor processor = new RetryItemProcessor();
        return processor;
    }

    @Bean
    public ItemWriter writer() {
        RetryItemWriter writer = new RetryItemWriter();
        return writer;
    }

    @Bean
    public SimpleRetryPolicy limitCheckingItemSkipPolicy() {

        Map<Class<? extends Throwable>, Boolean> retryableExceptionClasses = new HashMap<>();
        retryableExceptionClasses.put(RetryableException.class, true);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(3, retryableExceptionClasses);

        return simpleRetryPolicy;
    }
}

