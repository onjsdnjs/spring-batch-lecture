package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ItemWriter;
import java.util.List;

public class CustomItemWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items){
        for (String item : items) {
            System.out.println("write : " + item);
        }
    }
}
