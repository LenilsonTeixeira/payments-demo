package com.lteixeira.apicustomers.event.service;

import com.lteixeira.apicustomers.event.enumeration.CustomerPublishActionEnum;
import com.lteixeira.apicustomers.model.Customer;

@FunctionalInterface
public interface CustomerPublisherService {
    void accept(Customer customer, CustomerPublishActionEnum action);
}
