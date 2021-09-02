package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.LimitCheckingItemSkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.retry.policy.SimpleRetryPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class ThreadConfiguration {

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
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String item) throws Exception {
                        System.out.println("Processor => Thread = " + Thread.currentThread().getName() + "item = " + item);
                        return item;
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        System.out.println("Writer => Thread = " + Thread.currentThread().getName() + "item = " + items);
                    }
                })
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .throttleLimit(4)
                .build();
    }

    @Bean
    public SynchronizedItemStreamReader<String> reader() {

        List<String> items = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            items.add(String.valueOf(i));
        }
        CustomListItemReader<String> customListItemReader = new CustomListItemReader(items);
        SynchronizedItemStreamReader synchronizedItemStreamReader =  new SynchronizedItemStreamReader();
        synchronizedItemStreamReader.setDelegate(customListItemReader);

        return synchronizedItemStreamReader;
    }

    @Bean
    public ListItemReader<String> reader2() {

        List<String> items = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            items.add(String.valueOf(i));
        }
        CustomListItemReader<String> customListItemReader = new CustomListItemReader(items);

        return customListItemReader;
    }
}

