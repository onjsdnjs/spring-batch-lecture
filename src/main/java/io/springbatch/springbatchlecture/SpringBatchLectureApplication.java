package io.springbatch.springbatchlecture;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchLectureApplication implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchLectureApplication.class, args);
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

       /* Address address = Address.builder()
                .location("seoul").build();

        Address address2 = Address.builder()
                .location("seoul").build();

        Address address3 = Address.builder()
                .location("seoul").build();


        Customer customer = Customer.builder()
                .firstname("user")
                .lastname("1")
                .birthdate("20000101")
                .address(address)
                .build();

        Customer customer2 = Customer.builder()
                .firstname("user")
                .lastname("1")
                .birthdate("20000101")
                .address(address)
                .build();

        Customer customer3 = Customer.builder()
                .firstname("user")
                .lastname("1")
                .birthdate("20000101")
                .address(address)
                .build();

        address.setCustomer(customer);
        address2.setCustomer(customer2);
        address3.setCustomer(customer3);

        customerRepository.save(customer);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        addressRepository.save(address);
        addressRepository.save(address2);
        addressRepository.save(address3);*/
    }
}
