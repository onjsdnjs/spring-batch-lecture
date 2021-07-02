package io.springbatch.springbatchlecture;


import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Integer,String> {

    int count = 0;

    @Override
    public String process(Integer item) throws Exception {

        if(count < 2) {
            if (count % 2 == 0) {
                count = count + 1;

            } else if (count % 2 == 1) {
                count = count + 1;
//                throw new CustomRetryException();
            }
        }
        return String.valueOf(item);
    }
}
