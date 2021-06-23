package io.springbatch.springbatchlecture;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
	@Override
	public Customer mapRow(ResultSet rs, int i) throws SQLException {
		Customer customer = new Customer();

		customer.setId(rs.getInt("id"));
		customer.setFirstname(rs.getString("firstname"));
		customer.setLastname(rs.getString("lastname"));
		customer.setLastname(rs.getString("birthdate"));

		return customer;
	}
}
