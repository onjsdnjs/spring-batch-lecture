package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ItemProcessor;

public class RetryItemProcessor implements ItemProcessor<String, String> {

	private int cnt = 0;

	@Override
	public String process(String item) throws Exception {
		if(item.equals("1")) {

			cnt++;
			if(cnt == 1) {
				System.out.println("item " + item + " failed");
				throw new RetryableException("Process failed. cnt:" + cnt);

			}else if(cnt == 2) {
				System.out.println("item " + item + " failed");
				throw new NoRetryException("Process failed. cnt:" + cnt);

			}else{
				System.out.println("Success!");
				return String.valueOf(Integer.valueOf(item) * -1);

			}
		} else {
			return String.valueOf(Integer.valueOf(item) * -1);
		}
	}
}
