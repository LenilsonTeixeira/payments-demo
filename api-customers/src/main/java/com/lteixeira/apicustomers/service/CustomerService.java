package com.lteixeira.apicustomers.service;

import com.lteixeira.apicustomers.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDTO);

    List<CustomerDTO> findAll();

    void update (Integer id, CustomerDTO customerDTO);

    void deleteById (Integer id);

    Optional<CustomerDTO> findById(Integer id);
}
