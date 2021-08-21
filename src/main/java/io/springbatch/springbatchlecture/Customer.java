
package io.springbatch.springbatchlecture;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private String birthdate;
}
