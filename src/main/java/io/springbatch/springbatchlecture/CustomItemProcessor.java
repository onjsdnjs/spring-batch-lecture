package io.springbatch.springbatchlecture;


import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Customer,Customer> {

    int count = 0;

    @Override
    public Customer process(Customer customer) throws Exception {
        Customer customer1 = null;

        if (count % 2 == 0) {
            customer1 = customer;
            count = count + 1;

        } else if (count %2 == 1) {
            count = count + 1;
            throw new CustomRetryException();
        }
        return customer1;
    }
}
