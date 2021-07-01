package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RetryListenerConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomRetryListener customRetryListener;

    @Bean
    public Job job(){
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(listItemReader())
                .processor(new CustomItemProcessor())
                .writer(items -> {
                    for (Customer item : items) {
                        System.out.println("write : " + item);
                    }
                })
                .faultTolerant()
                .retry(CustomRetryException.class)
                .retryLimit(3)
                .listener(customRetryListener)
                .build();
    }

    @Bean
    public ItemReader<Customer> listItemReader() {
        List<Customer> list = Arrays.asList(new Customer(),new Customer(),new Customer());
        return new LinkedListItemReader<>(list);
    }
}

