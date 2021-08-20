package io.springbatch.springbatchlecture;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long Id;
	private String location;

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}