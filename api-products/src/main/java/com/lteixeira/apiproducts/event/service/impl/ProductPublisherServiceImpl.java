package com.lteixeira.apiproducts.event.service.impl;

import com.lteixeira.apiproducts.event.enumeration.ProductPublishActionEnum;
import com.lteixeira.apiproducts.event.mapper.ProductPublisherEventMapper;
import com.lteixeira.apiproducts.event.model.Message;
import com.lteixeira.apiproducts.event.model.ProductEvent;
import com.lteixeira.apiproducts.event.model.ProductEventPublisher;
import com.lteixeira.apiproducts.event.service.ProductPublisherService;
import com.lteixeira.apiproducts.model.Product;
import com.lteixeira.apiproducts.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ProductPublisherServiceImpl implements ProductPublisherService {

    private static final String KAFKA_TOPIC = "api-products-data";
    private static final String ACTION_HEADER = "action";

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private ProductPublisherEventMapper productPublisherEventMapper;

    private Message buildMessage(final ProductEventPublisher productEventPublisher) {

        final String productEventString = JsonUtils.toJson(productEventPublisher);

        log.info("Contruindo mensagem para publicar no kafka: {}", productEventString);

        Message message = new Message();
        message.setHeaders(buildHeaders(productEventPublisher));
        message.setPayload(productEventString);
        return message;
    }

    private static Map<String, String> buildHeaders(final ProductEventPublisher eventPublisher) {
        final Map<String, String> headers = new HashMap<>();
        headers.put(ACTION_HEADER, eventPublisher.getAction().name());
        return headers;
    }

    private ProductEvent productEventBuild(Product product, ProductPublishActionEnum action) {
        return action.DELETE.equals(action) ? ProductEvent.builder().productCode(product.getCode().toString()).build() : productPublisherEventMapper.map(product);
    }

    @Override
    public void accept(Product product, ProductPublishActionEnum action) {
        try {
            log.info("Publicando mensagem no kafka");
            final ProductEventPublisher productEventPublisher = ProductEventPublisher.with(productEventBuild(product, action), action);
            this.kafkaTemplate.send(KAFKA_TOPIC, buildMessage(productEventPublisher));
        } catch (Exception e) {
            log.error("Falha ao publicar mensagem", e);
        }
    }
}
