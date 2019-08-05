package com.lteixeira.msorders.service;

import com.lteixeira.msorders.handler.KafkaConsumerHandlerService;
import com.lteixeira.msorders.model.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import javax.annotation.PostConstruct;

@Log4j2
@Service
public class KafkaReceiverService {

    @Autowired
    private ReceiverOptions<String, Message> receiverOptions;

    @Autowired
    private KafkaConsumerHandlerService kafkaConsumerHandlerService;

    @PostConstruct
    public void postConstruct() {
        initializeReceiver();
    }

    private void initializeReceiver() {

        log.info("Initializing Kafka Receiver");

        KafkaReceiver.create(receiverOptions)
                .receive()
                .doOnError(error -> log.error("Error consuming message from kafka\n", error))
                .subscribe(kafkaConsumerHandlerService::handle);

    }
}
