
package io.springbatch.springbatchlecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer2")
@Entity
public class Customer {

    @Id
    private long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
}
