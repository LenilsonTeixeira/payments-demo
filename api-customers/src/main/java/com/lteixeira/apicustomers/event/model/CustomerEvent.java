package com.lteixeira.apicustomers.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent implements Serializable {
    private String customerId;
    private String customerName;
    private String customerSSn;
    private String customerCredit;
}
