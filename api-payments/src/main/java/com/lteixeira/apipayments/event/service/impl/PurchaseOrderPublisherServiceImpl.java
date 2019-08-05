package com.lteixeira.apipayments.event.service.impl;

import com.lteixeira.apipayments.event.enumeration.PurchaseOrderPublishActionEnum;
import com.lteixeira.apipayments.event.mapper.PurchaseOrderPublisherEventMapper;
import com.lteixeira.apipayments.event.model.Message;
import com.lteixeira.apipayments.event.model.PurchaseOrderEvent;
import com.lteixeira.apipayments.event.model.PurchaseOrderEventPublisher;
import com.lteixeira.apipayments.event.service.PurchaseOrderPublisherService;
import com.lteixeira.apipayments.model.PurchaseOrder;
import com.lteixeira.apipayments.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class PurchaseOrderPublisherServiceImpl implements PurchaseOrderPublisherService {

    private static final String KAFKA_TOPIC = "api-payments-data";
    private static final String ACTION_HEADER = "action";

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private PurchaseOrderPublisherEventMapper purchaseOrderPublisherEventMapper;

    private Message buildMessage(final PurchaseOrderEventPublisher purchaseOrderEventPublisher) {

        final String productEventString = JsonUtils.toJson(purchaseOrderEventPublisher);

        log.info("Contruindo mensagem para publicar no kafka: {}", productEventString);

        Message message = new Message();
        message.setHeaders(buildHeaders(purchaseOrderEventPublisher));
        message.setPayload(productEventString);
        return message;
    }

    private static Map<String, String> buildHeaders(final PurchaseOrderEventPublisher eventPublisher) {
        final Map<String, String> headers = new HashMap<>();
        headers.put(ACTION_HEADER, eventPublisher.getAction().name());
        return headers;
    }

    private PurchaseOrderEvent purchaseOrderEventBuild(PurchaseOrder purchaseOrder, PurchaseOrderPublishActionEnum action) {
        return action.DELETE.equals(action) ? PurchaseOrderEvent.builder().purchaseOrderId(purchaseOrder.getId().toString()).build() : purchaseOrderPublisherEventMapper.map(purchaseOrder);
    }

    @Override
    public void accept(PurchaseOrder purchaseOrder, PurchaseOrderPublishActionEnum action) {
        try {
            log.info("Publicando mensagem no kafka");
            final PurchaseOrderEventPublisher purchaseOrderEventPublisher = PurchaseOrderEventPublisher.with(purchaseOrderEventBuild(purchaseOrder, action), action);
            this.kafkaTemplate.send(KAFKA_TOPIC, buildMessage(purchaseOrderEventPublisher));
        } catch (Exception e) {
            log.error("Falha ao publicar mensagem", e);
        }
    }
}
