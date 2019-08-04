package com.lteixeira.apicustomers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CUSTOMER")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TXT_NAME", nullable = false)
    private String name;

    @Column(name = "TXT_SSN", nullable = false)
    private String ssn;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

    @DecimalMin(value = "0.00")
    @Column(name = "NUM_CREDIT", nullable = false)
    private BigDecimal credit;

    @Embedded
    private Address address;

}
