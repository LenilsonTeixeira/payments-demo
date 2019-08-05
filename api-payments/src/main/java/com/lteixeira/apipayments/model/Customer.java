package com.lteixeira.apipayments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Customer implements Serializable {

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
}
