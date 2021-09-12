package io.springbatch.springbatchlecture.batch.job.send;/*
package io.anymobi.batch.job.send;

import io.anymobi.batch.chunk.processor.SendApiItemProcessor;
import io.anymobi.batch.chunk.writer.SendApiItemWriter;
import io.anymobi.batch.partition.MessagePartitioner;
import io.anymobi.batch.rowmapper.MessagingApiMapper;
import io.anymobi.domain.vo.ApiRequestVO;
import io.anymobi.domain.vo.MessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * <pre>
 * io.anymobi.core.batch.job.send
 * ㄴ SendDataChildJobConfiguration.java
 * </pre>
 * Api 연동을 위한 Step 객체 생성
 *
 * @author : soowon.jung
 * @version : 1.0.0
 * @date : 2021-07-22 오후 1:34
 * @see :
 **//*


@Configuration
@RequiredArgsConstructor
public class SendApiSteps2 {

    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource primaryDataSource;
    private final SendApiItemProcessor sendApiItemProcessor;
    private final SendApiItemWriter sendApiItemWriter;
    private final StepExecutionListener apiStepListener1;
    private final StepExecutionListener apiStepListener2;
    private final StepExecutionListener apiStepListener3;

    @Value("${batch.chunkSize}")
    private int chunkSize;

    @Value("${batch.gridSize}")
    private int gridSize;

    @Bean
    @JobScope
    public Step apiMasterStep(@Value("#{stepExecutionContext['msg']}") MessageVO messageVO) throws Exception {

        return stepBuilderFactory.get("apiStep1")
                .partitioner(apiSlaveStep().getName(), partitioner())
                .step(apiSlaveStep())
                .gridSize(gridSize)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    @Bean
    @JobScope
    public Step apiSlaveStep() throws Exception {

        return stepBuilderFactory.get("apiStep1")
                .<ApiRequestVO, ApiRequestVO>chunk(chunkSize)
                .reader(sendApiItemReader(null))
                .processor(sendApiItemProcessor)
                .writer(sendApiItemWriter)
                .listener(apiStepListener1)
                .build();
    }

    @Bean
    public MessagePartitioner partitioner() {
        MessagePartitioner columnRangePartitioner = new MessagePartitioner();
        columnRangePartitioner.setDataSource(primaryDataSource);
        return columnRangePartitioner;
    }

   */
/* @Bean
    @JobScope
    public Step apiStep1(@Value("#{jobExecutionContext['0']}") MessageVO messageVO) throws Exception {

        return stepBuilderFactory.get("apiStep1")
                .<ApiRequestVO, ApiRequestVO>chunk(chunkSize)
                .reader(sendApiItemReader(messageVO))
                .processor(sendApiItemProcessor)
                .writer(sendApiItemWriter)
                .listener(apiStepListener1)
                .build();
    }

    @Bean
    @JobScope
    public Step apiStep2(@Value("#{jobExecutionContext['1']}") MessageVO messageVO) throws Exception {

        return stepBuilderFactory.get("apiStep2")
                .<ApiRequestVO, ApiRequestVO>chunk(chunkSize)
                .reader(sendApiItemReader(messageVO))
                .processor(sendApiItemProcessor)
                .writer(sendApiItemWriter)
                .listener(apiStepListener2)
                .build();
    }

    @Bean
    @JobScope
    public Step apiStep3(@Value("#{jobExecutionContext['2']}") MessageVO messageVO) throws Exception {

        return stepBuilderFactory.get("apiStep3")
                .<ApiRequestVO, ApiRequestVO>chunk(chunkSize)
                .reader(sendApiItemReader(messageVO))
                .processor(sendApiItemProcessor)
                .writer(sendApiItemWriter)
                .listener(apiStepListener3)
                .build();
    }*//*


    public ItemReader<ApiRequestVO> sendApiItemReader(MessageVO vo) throws Exception {

        if(vo == null){
           return new ListItemReader(Collections.emptyList());
        }

        JdbcPagingItemReader<ApiRequestVO> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(primaryDataSource);
        reader.setFetchSize(chunkSize);
        reader.setPageSize(chunkSize);
        reader.setRowMapper(new MessagingApiMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause(QueryGenerator.getSelectQueryForTarget());
        queryProvider.setFromClause(QueryGenerator.getFromQueryForTarget());
        queryProvider.setWhereClause(QueryGenerator.getWhereQueryForTarget());

        Map<String, Order> sortKeys = new HashMap<>(1);
//        sortKeys.put("IF_YMD", Order.ASCENDING);
        sortKeys.put("ID", Order.DESCENDING);
//        sortKeys.put("IF_SEQ", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);
//        reader.setQueryProvider(queryProvider);

//        reader.setParameterValues(QueryGenerator.getParameterForQuery("msg_seq", msgSeq));
        reader.setParameterValues(QueryGenerator.getParameterForQuery("str", vo.getMessageTemplateName()));
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();

        return reader;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(gridSize);
        executor.setMaxPoolSize(gridSize*2);
        executor.setThreadNamePrefix("async-thread-");
        return executor;
    }
}
*/
