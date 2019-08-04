package com.lteixeira.apiproducts.event.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lteixeira.apiproducts.event.enumeration.ProductPublishActionEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductEventPublisher {
    private final ProductEvent payload;
    private final ProductPublishActionEnum action;

    public static ProductEventPublisher with(final ProductEvent payload, final ProductPublishActionEnum action) {
        return new ProductEventPublisher(payload, action);
    }
}
