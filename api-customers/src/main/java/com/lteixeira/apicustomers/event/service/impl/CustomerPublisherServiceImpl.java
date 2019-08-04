package com.lteixeira.apicustomers.event.service.impl;

import com.lteixeira.apicustomers.event.enumeration.CustomerPublishActionEnum;
import com.lteixeira.apicustomers.event.mapper.CustomerPublisherEventMapper;
import com.lteixeira.apicustomers.event.model.CustomerEvent;
import com.lteixeira.apicustomers.event.model.CustomerEventPublisher;
import com.lteixeira.apicustomers.event.service.CustomerPublisherService;
import com.lteixeira.apicustomers.model.Customer;
import com.lteixeira.apicustomers.util.JsonUtils;
import com.lteixeira.apicustomers.util.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class CustomerPublisherServiceImpl implements CustomerPublisherService {

    private static final String KAFKA_TOPIC = "api-customer-data";
    private static final String ACTION_HEADER = "action";

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private CustomerPublisherEventMapper customerPublisherEventMapper;

    private Message buildMessage(final CustomerEventPublisher customerEventPublisher) {

        final String customerEventString = JsonUtils.toJson(customerEventPublisher);

        log.info("Contruindo mensagem para publicar no kafka: {}", customerEventString);

        Message message = new Message();
        message.setHeaders(buildHeaders(customerEventPublisher));
        message.setPayload(customerEventString);
        return message;
    }

    private static Map<String, String> buildHeaders(final CustomerEventPublisher eventPublisher) {
        final Map<String, String> headers = new HashMap<>();
        headers.put(ACTION_HEADER, eventPublisher.getAction().name());
        return headers;
    }

    private CustomerEvent customerEventBuild(Customer customer, CustomerPublishActionEnum action) {
        return action.DELETE.equals(action) ? CustomerEvent.builder().customerId(customer.getId().toString()).build() : customerPublisherEventMapper.map(customer);
    }

    @Override
    public void accept(Customer customer, CustomerPublishActionEnum action) {
        try {
            log.info("Publicando mensagem no kafka");
            final CustomerEventPublisher customerEventPublisher = CustomerEventPublisher.with(customerEventBuild(customer, action), action);
            this.kafkaTemplate.send(KAFKA_TOPIC, buildMessage(customerEventPublisher));
        } catch (Exception e) {
            log.error("Falha ao publicar mensagem", e);
        }
    }
}
