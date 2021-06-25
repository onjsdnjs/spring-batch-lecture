package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class RetryItemWriter implements ItemWriter<String> {

	private int cnt = 0;

	@Override
	public void write(List<? extends String> items) throws Exception {
		for (String item : items) {
			if(item.equals("-12")) {
				cnt++;
				throw new RetryableException("Write failed. cnt:" + cnt);
			}
			else {
				System.out.println(item);
			}
		}
	}
}
