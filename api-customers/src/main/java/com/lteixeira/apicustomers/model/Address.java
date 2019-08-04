package com.lteixeira.apicustomers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address implements Serializable {

    @Column(name = "TXT_STREET", nullable = false)
    private String street;

    @Column(name = "TXT_CITY", nullable = false)
    private String city;

    @Column(name = "TXT_STATE", nullable = false, length = 2)
    private String state;

    @Column(name = "TXT_ZIP", nullable = false, length = 10)
    private String zip;
}
