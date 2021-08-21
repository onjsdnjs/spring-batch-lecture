package io.springbatch.springbatchlecture;

import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcess implements ItemProcessor<Customer,Customer2> {

     private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Customer2 process(Customer item) throws Exception {
        Customer2 customer2 = modelMapper.map(item, Customer2.class);

        return customer2;
    }
}
