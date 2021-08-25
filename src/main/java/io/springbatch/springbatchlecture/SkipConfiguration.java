package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.LimitCheckingItemSkipPolicy;
import org.springframework.batch.core.step.skip.NeverSkipItemSkipPolicy;
import org.springframework.batch.core.step.skip.SkipException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class SkipConfiguration {

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
                .reader(new ItemReader<String>() {
                    int i = 0;
                    @Override
                    public String read() throws SkippableException {
                        i++;
                        if(i == 3) {
                            throw new SkippableException("skip");
                        }
                        System.out.println("ItemReader : " + i);
                        return i > 20 ? null : String.valueOf(i);
                    }
                })
                .processor(processor())
                .writer(writer())
                .faultTolerant()
//                .noSkip(SkippableException.class) // 아래 설정이 위의 설정을 덮어씀, skip() 설정이 우선
//                .skipPolicy(limitCheckingItemSkipPolicy())
//                .retry(SkippableException.class)
//                .retryLimit(2)
                .skip(SkippableException.class)
                .skipLimit(2)
//                .noRollback(SkippableException.class)
                .build();
    }

    @Bean
    public LimitCheckingItemSkipPolicy limitCheckingItemSkipPolicy(){

        Map<Class<? extends Throwable>, Boolean> skippableExceptionClasses = new HashMap<>();
        skippableExceptionClasses.put(SkippableException.class, true);

        LimitCheckingItemSkipPolicy limitCheckingItemSkipPolicy = new LimitCheckingItemSkipPolicy(3, skippableExceptionClasses);

        return limitCheckingItemSkipPolicy;
    }

    @Bean
    public SkipItemProcessor processor() {
        SkipItemProcessor processor = new SkipItemProcessor();
        return processor;
    }

    @Bean
    public SkipItemWriter writer() {
        SkipItemWriter writer = new SkipItemWriter();
        return writer;
    }
}

