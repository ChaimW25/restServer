

package com.example.restserver.repo;

import java.util.List;

import com.example.restserver.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    @Query(value = "SELECT * FROM CUSTOMER",nativeQuery = true)
    Customer findByLastNameAndFirstNameLike(String lastName, String firstname);
    Customer findById(long id);
}