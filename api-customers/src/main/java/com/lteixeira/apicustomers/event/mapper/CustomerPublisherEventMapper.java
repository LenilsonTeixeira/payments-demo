package com.lteixeira.apicustomers.event.mapper;

import com.lteixeira.apicustomers.event.model.CustomerEvent;
import com.lteixeira.apicustomers.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerPublisherEventMapper {

    public CustomerEvent map(Customer customer){
        return CustomerEvent.builder()
                .customerId(customer.getId().toString())
                .customerName(customer.getName())
                .customerSSn(customer.getSsn())
                .customerCredit(customer.getCredit().toString())
                .build();

    }
}
