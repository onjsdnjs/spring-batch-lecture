package io.springbatch.springbatchlecture;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;

import java.util.ArrayList;
import java.util.List;

public class CustomItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        customer.setName(customer.getName().toUpperCase());
        return null;
    }
}
