package com.lteixeira.apipayments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Product implements Serializable {

    @Column(name = "TXT_PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "NUM_PRODUCT_PRICE", nullable = false)
    private BigDecimal price;

}
