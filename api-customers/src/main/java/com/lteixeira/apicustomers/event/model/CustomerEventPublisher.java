package com.lteixeira.apicustomers.event.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lteixeira.apicustomers.event.enumeration.CustomerPublishActionEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerEventPublisher {
    private final CustomerEvent payload;
    private final CustomerPublishActionEnum action;

    public static CustomerEventPublisher with(final CustomerEvent payload, final CustomerPublishActionEnum action) {
        return new CustomerEventPublisher(payload, action);
    }
}
