package io.springbatch.springbatchlecture;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

    @Override
    public Customer mapFieldSet(FieldSet fieldSet) {
        Customer customer = new Customer();

        customer.setName(fieldSet.readString(0));
        customer.setYear(fieldSet.readString(1));
        customer.setAge(fieldSet.readInt(2));

        return customer;
    }
}
