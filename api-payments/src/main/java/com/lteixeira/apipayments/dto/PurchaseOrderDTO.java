package com.lteixeira.apipayments.dto;

import com.lteixeira.apipayments.enumeration.PurchaseOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Cliente é obrigatório")
    private CustomerDTO customer;

    @NotNull(message = "Endereço é obrigatório")
    private AddressDTO address;

    @NotNull(message = "Produto é obrigatório")
    private ProductDTO product;

    private PurchaseOrderStatus orderStatus;
}
