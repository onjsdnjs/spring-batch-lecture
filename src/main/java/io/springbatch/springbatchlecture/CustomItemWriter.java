package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

import java.util.List;

public class CustomItemWriter implements ItemStreamWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        items.forEach(item -> System.out.println(item));
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("");
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("");
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("");
    }
}
