package io.springbatch.springbatchlecture.batch.job.daemon1;

import io.springbatch.springbatchlecture.batch.chunk.processor.SendApiItemProcessor;
import io.springbatch.springbatchlecture.batch.chunk.writer.SendApiItemWriter;
import io.springbatch.springbatchlecture.batch.domain.ApiRequestVO;
import io.springbatch.springbatchlecture.batch.domain.ProductVO;
import io.springbatch.springbatchlecture.batch.partition.ProductPartitioner;
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
    private final DataSource dataSource;
    private final SendApiItemProcessor sendApiItemProcessor;
    private final SendApiItemWriter sendApiItemWriter;

    private int chunkSize = 10;

    @Bean
    public Step apiMasterStep() throws Exception {

        ProductVO[] itemList = QueryGenerator.getProductList(dataSource);

        return stepBuilderFactory.get("apiMasterStep")
                .partitioner(apiSlaveStep().getName(), partitioner())
                .step(apiSlaveStep())
                .gridSize(itemList.length)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    @Bean
    public Step apiSlaveStep() throws Exception {

        return stepBuilderFactory.get("apiSlaveStep")
                .<ApiRequestVO, ApiRequestVO>chunk(chunkSize)
                .reader(itemReader(null))
                .processor(sendApiItemProcessor)
                .writer(sendApiItemWriter)
                .build();
    }

    @Bean
    public ProductPartitioner partitioner() {
        ProductPartitioner productPartitioner = new ProductPartitioner();
        productPartitioner.setDataSource(dataSource);
        return productPartitioner;
    }

    @Bean
    @StepScope
    public ItemReader<ApiRequestVO> itemReader(@Value("#{stepExecutionContext['product']}") ProductVO productVO) throws Exception {

        JdbcPagingItemReader<ApiRequestVO> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(dataSource);
        reader.setPageSize(chunkSize);
        reader.setRowMapper(new MessagingApiMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, name, price, type");
        queryProvider.setFromClause("from item");
        queryProvider.setWhereClause("where type = :type");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.DESCENDING);
        queryProvider.setSortKeys(sortKeys);

        reader.setParameterValues(QueryGenerator.getParameterForQuery("type", productVO.getType()));
        reader.setQueryProvider(queryProvider);
        reader.afterPropertiesSet();

        return reader;
    }
}
