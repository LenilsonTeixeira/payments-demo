package com.lteixeira.msorders.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lteixeira.msorders.model.Message;
import com.lteixeira.msorders.model.OrderInfo;
import com.lteixeira.msorders.repository.OrderInfoRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@Service
public class KafkaReceiver {

    private static final String KAFKA_GROUP_ID = "kafka-cluster";
    private static final String KAFKA_TOPIC_CONTRACTOR_DATA = "api-payments-data";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @KafkaListener(topics = KAFKA_TOPIC_CONTRACTOR_DATA, groupId = KAFKA_GROUP_ID)
    public void receiveData(String data) throws IOException {
        log.info("Mensagem: {} - Recebida do Kafka", data);

        if(Optional.ofNullable(data).isPresent()){
            OrderInfo orderInfo = extractPayload(data);
            orderInfoRepository.save(orderInfo);
        }

    }

    private OrderInfo extractPayload(String data) throws IOException {
        Message message = objectMapper.readValue(data, Message.class);
        JSONObject payloadJson = new JSONObject(message.getPayload());
        String json = payloadJson.get("payload").toString();
        OrderInfo orderInfo =  objectMapper.readValue(json, OrderInfo.class);
        orderInfo.setAction(message.getHeaders().get("action"));
        return orderInfo;
    }

}
