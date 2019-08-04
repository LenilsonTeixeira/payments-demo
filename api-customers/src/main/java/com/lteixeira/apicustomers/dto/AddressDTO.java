package com.lteixeira.apicustomers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {

    @NotBlank(message = "Rua é obrigatória")
    private String street;

    @NotBlank(message = "Cidade é obrigatória")
    private String city;

    @NotBlank(message = "Estato é obrigatório")
    private String state;

    @NotBlank(message = "Cep é obrigatório")
    private String zip;
}
