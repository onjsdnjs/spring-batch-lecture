package io.springbatch.springbatchlecture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private Long Id;
	private String firstName;
	private String lastName;
	private String birthdate;

}