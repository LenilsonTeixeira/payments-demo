package com.lteixeira.apiproducts.event.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvent implements Serializable {
    private String productCode;
    private String productName;
    private String productPrice;
}
