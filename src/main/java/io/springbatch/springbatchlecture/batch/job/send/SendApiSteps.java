package io.springbatch.springbatchlecture.batch.job.send;

import io.springbatch.springbatchlecture.batch.chunk.processor.SendApiItemProcessor;
import io.springbatch.springbatchlecture.batch.chunk.writer.SendApiItemWriter;
import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.MessageVO;
import io.springbatch.springbatchlecture.batch.partition.MessagePartitioner;
import io.springbatch.springbatchlecture.batch.rowmapper.MessagingApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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
 **/

@Configuration
@RequiredArgsConstructor
public class SendApiSteps {

    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource primaryDataSource;
    private final SendApiItemProcessor sendApiItemProcessor;
    private final SendApiItemWriter sendApiItemWriter;

    @Value("${batch.chunkSize}")
    private int chunkSize;

    @Bean
    public Step apiMasterStep() throws Exception {

        MessageVO[] msgList = QueryGenerator.getMsgList(primaryDataSource);

        return stepBuilderFactory.get("apiMasterStep")
                .partitioner(apiSlaveStep().getName(), partitioner())
                .step(apiSlaveStep())
                .gridSize(msgList.length)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    @Bean
    public Step apiSlaveStep() throws Exception {

        return stepBuilderFactory.get("apiSlaveStep")
                .<ApiRequestVO, ApiRequestVO>chunk(chunkSize)
                .reader(sendApiItemReader(null))
                .processor(sendApiItemProcessor)
                .writer(sendApiItemWriter)
                .build();
    }

    @Bean
    public MessagePartitioner partitioner() {
        MessagePartitioner messagePartitioner = new MessagePartitioner();
        messagePartitioner.setDataSource(primaryDataSource);
        return messagePartitioner;
    }

    @Bean
    @StepScope
    public ItemReader<ApiRequestVO> sendApiItemReader(@Value("#{stepExecutionContext['msg']}") MessageVO messageVO) throws Exception {

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
        reader.setParameterValues(QueryGenerator.getParameterForQuery("str", messageVO.getMessageTemplateName()));
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();

        return reader;
    }
}
