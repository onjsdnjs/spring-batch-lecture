package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RequiredArgsConstructor
@Configuration
public class AsyncConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
//                .start(asyncStep1())
                .listener(new StopWatchJobListener())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<Customer> pagingItemReader() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(300);
        reader.setRowMapper(new CustomerRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, firstName, lastName, birthdate");
        queryProvider.setFromClause("from customer");

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(queryProvider);

        return reader;
    }

    @Bean
    public ItemProcessor customItemProcessor() {
        return new ItemProcessor<Customer, Customer>() {
            @Override
            public Customer process(Customer item) throws Exception {

                Thread.sleep(10);

                return new Customer(item.getId(),
                        item.getFirstName().toUpperCase(),
                        item.getLastName().toUpperCase(),
                        item.getBirthdate());
            }
        };
    }

    @Bean
    public AsyncItemProcessor asyncItemProcessor() throws Exception {
        AsyncItemProcessor<Customer, Customer> asyncItemProcessor = new AsyncItemProcessor();

        asyncItemProcessor.setDelegate(customItemProcessor());
        asyncItemProcessor.setTaskExecutor(new SimpleAsyncTaskExecutor());
        asyncItemProcessor.afterPropertiesSet();

        return asyncItemProcessor;
    }

    @Bean
    public JdbcBatchItemWriter customItemWriter() {
        JdbcBatchItemWriter<Customer> itemWriter = new JdbcBatchItemWriter<>();

        itemWriter.setDataSource(this.dataSource);
        itemWriter.setSql("insert into customer2 values (:id, :firstName, :lastName, :birthdate)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public AsyncItemWriter asyncItemWriter() throws Exception {
        AsyncItemWriter<Customer> asyncItemWriter = new AsyncItemWriter<>();

        asyncItemWriter.setDelegate(customItemWriter());
        asyncItemWriter.afterPropertiesSet();

        return asyncItemWriter;
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .chunk(300)
                .reader(pagingItemReader())
                .processor(customItemProcessor())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public Step asyncStep1() throws Exception {
        return stepBuilderFactory.get("asyncStep1")
                .chunk(300)
                .reader(pagingItemReader())
                .processor(asyncItemProcessor())
                .writer(asyncItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setThreadNamePrefix("async-thread-");
        return executor;
    }
}

