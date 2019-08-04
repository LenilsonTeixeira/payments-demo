package com.lteixeira.apicustomers.repository;

import com.lteixeira.apicustomers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> { }
