package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ItemReader;

import java.util.ArrayList;
import java.util.List;

public class CustomItemReader implements ItemReader<Customer> {

    private List<Customer> list;

    public CustomItemReader(List<Customer> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public Customer read() {
        if (!list.isEmpty()) {
            return list.remove(0);
        }
        return null;
    }
}
