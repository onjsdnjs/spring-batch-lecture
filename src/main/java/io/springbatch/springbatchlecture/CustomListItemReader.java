package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.support.ListItemReader;

import java.util.List;

public class CustomListItemReader<T> extends ListItemReader<T> implements ItemStreamReader<T> {

    public CustomListItemReader(List<T> list) {
        super(list);
    }

    @Override
    public T read() {
        T read = super.read();
        System.out.println("Reader :" + read + " => Thread = " + Thread.currentThread().getName());
        return read;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void close() throws ItemStreamException {

    }
}
