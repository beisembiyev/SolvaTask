package com.example.solva.Repos;

import com.example.solva.Models.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer findCustomerById(long id);
}
