
package io.springbatch.springbatchlecture.batch.partition;

import io.springbatch.springbatchlecture.batch.domain.MessageVO;
import io.springbatch.springbatchlecture.batch.job.send.QueryGenerator;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class MessagePartitioner implements Partitioner {

	private DataSource primaryDataSource;

	public void setDataSource(DataSource dataSource) {
		this.primaryDataSource = dataSource;
	}

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {

		MessageVO[] msgList = QueryGenerator.getMsgList(primaryDataSource);
		Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
		int number = 0;

		for (int i = 0; i < msgList.length; i++) {

			ExecutionContext value = new ExecutionContext();

			result.put("partition" + number, value);
			value.put("msg", msgList[i]);

			number++;
		}

		return result;
	}}
