package com.lteixeira.apipayments.model;

import com.lteixeira.apipayments.enumeration.PurchaseOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_PURCHASE_ORDER")
public class PurchaseOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Customer customer;

    @Embedded
    private Address address;

    @Embedded
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "txt_order_status", nullable = false)
    private PurchaseOrderStatus orderStatus;

}
