package com.lteixeira.apicustomers.service.impl;

import com.lteixeira.apicustomers.dto.CustomerDTO;
import com.lteixeira.apicustomers.event.enumeration.CustomerPublishActionEnum;
import com.lteixeira.apicustomers.event.service.CustomerPublisherService;
import com.lteixeira.apicustomers.exception.CustomerException;
import com.lteixeira.apicustomers.exception.NotFoundException;
import com.lteixeira.apicustomers.mapper.CustomerMapper;
import com.lteixeira.apicustomers.model.Customer;
import com.lteixeira.apicustomers.repository.CustomerRepository;
import com.lteixeira.apicustomers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerPublisherService customerPublisherService;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        try {
            Customer customer = customerMapper.convertToModel(customerDTO);

            final CustomerDTO customerDb =  customerMapper.convertToDTO(this.customerRepository.save(customer));

            customerPublisherService.accept(customerMapper.convertToModel(customerDb), CustomerPublishActionEnum.CREATE);

            return customerDb;

        }catch (Exception e){
            throw new CustomerException("Erro ao salvar cliente");
        }
    }

    @Override
    public List<CustomerDTO> findAll() {
        return this.customerRepository.findAll().stream()
                .map(customerMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Integer id, CustomerDTO customerDTO) {
        this.findById(id);

        final Customer customer = customerRepository.save(customerMapper.convertToModel(customerDTO));

        customerPublisherService.accept(customer,CustomerPublishActionEnum.UPDATE);
    }

    @Override
    public void deleteById(Integer id) {
        this.findById(id);

        customerRepository.deleteById(id);

        customerPublisherService.accept(Customer.builder().id(id).build(),CustomerPublishActionEnum.DELETE);
    }

    @Override
    public Optional<CustomerDTO> findById(Integer id) {
        Optional<Customer> customerDb = customerRepository.findById(id);
        if(customerDb.isPresent()){
            return customerRepository.findById(id)
                    .map(customerMapper::convertToDTO);
        }else{
            throw new NotFoundException("Cliente não encontrado");
        }
    }
}
