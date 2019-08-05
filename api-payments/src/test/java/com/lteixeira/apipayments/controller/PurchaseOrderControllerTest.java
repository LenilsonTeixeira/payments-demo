package com.lteixeira.apipayments.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lteixeira.apipayments.ApiPaymentsApplicationTests;
import com.lteixeira.apipayments.dto.PurchaseOrderDTO;
import com.lteixeira.apipayments.event.service.PurchaseOrderPublisherService;
import com.lteixeira.apipayments.service.PurchaseOrderService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PurchaseOrderControllerTest extends ApiPaymentsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private PurchaseOrderPublisherService purchaseOrderPublisherService;

    private static final String PURCHASE_ORDERS_URI = "/transactions";

    private static final String PURCHASE_ORDER_URI = "/transactions/{purchaseOrderId}";

    @Test
    public void createPurchaseOrderSucess() throws Exception {

        final MvcResult mvcResult = this.mockMvc
                .perform(createPurchaseOrderRequest(buildPurchaseOrderDTO()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.name", Matchers.is("Lenilson Teixeira")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.ssn", Matchers.is("07012312312")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.city", Matchers.is("Ituiutaba")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.state", Matchers.is("MG")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.zip", Matchers.is("38307086")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.street", Matchers.is("Rua Baru nº217")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", Matchers.is("APPROVED")))
                .andReturn();
    }

    @Test
    public void createPurchaseOrderValidatingOrderStatusEqualsRejected() throws Exception {

        PurchaseOrderDTO purchaseOrderDTO = buildPurchaseOrderDTO();
        purchaseOrderDTO.getCustomer().setCredit(new BigDecimal("10"));

        final MvcResult mvcResult = this.mockMvc
                .perform(createPurchaseOrderRequest(purchaseOrderDTO))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.name", Matchers.is("Lenilson Teixeira")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.ssn", Matchers.is("07012312312")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.city", Matchers.is("Ituiutaba")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.state", Matchers.is("MG")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.zip", Matchers.is("38307086")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.street", Matchers.is("Rua Baru nº217")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", Matchers.is("REJECTED")))
                .andReturn();
    }

    @Test
    public void deletePurchaseOrderSucess() throws Exception {

        final PurchaseOrderDTO purchaseOrderDTO = buildPurchaseOrderDTO();

        PurchaseOrderDTO purchaseOrderCreated = purchaseOrderService.save(purchaseOrderDTO);

        this.mockMvc
                .perform(deletePurchaseOrderRequest(purchaseOrderCreated.getId()))
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));

    }

    private MockHttpServletRequestBuilder deletePurchaseOrderRequest(final Integer purchaseOrderId) {

        return MockMvcRequestBuilders
                .delete(PURCHASE_ORDER_URI, purchaseOrderId)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    private MockHttpServletRequestBuilder createPurchaseOrderRequest(PurchaseOrderDTO purchaseOrderDTO) throws JsonProcessingException {

        final PurchaseOrderDTO purchaseOrderRequest = purchaseOrderDTO != null ? purchaseOrderDTO : buildPurchaseOrderDTO();

        return MockMvcRequestBuilders
                .post(PURCHASE_ORDERS_URI)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(OBJECT_MAPPER.writeValueAsString(purchaseOrderRequest));
    }
}
