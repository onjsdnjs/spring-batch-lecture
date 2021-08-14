package io.springbatch.springbatchlecture;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

    @Override
    public Customer mapFieldSet(FieldSet fs) {

        if(fs == null){
            return null;
        }

        Customer customer = new Customer();
        customer.setName(fs.readString(0));
        customer.setAge(fs.readInt(1));
        customer.setYear(fs.readString(2));

        return customer;
    }
}
