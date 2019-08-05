package com.lteixeira.apipayments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lteixeira.apipayments.dto.AddressDTO;
import com.lteixeira.apipayments.dto.CustomerDTO;
import com.lteixeira.apipayments.dto.ProductDTO;
import com.lteixeira.apipayments.dto.PurchaseOrderDTO;
import com.lteixeira.apipayments.model.Address;
import com.lteixeira.apipayments.model.Customer;
import com.lteixeira.apipayments.model.Product;
import com.lteixeira.apipayments.model.PurchaseOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka
public class ApiPaymentsApplicationTests {

	protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Test
	public void contextLoads() {
	}

	protected static PurchaseOrderDTO buildPurchaseOrderDTO(){
		PurchaseOrderDTO purchaseOrder = new PurchaseOrderDTO();

		CustomerDTO customer = prepareCustomerDTO("Lenilson Teixeira", "07012312312", new Date("1991/04/26"), new BigDecimal("3000.00"));

		AddressDTO address = prepareAddressDTO("Ituiutaba", "MG", "38307086","Rua Baru nº217");

		ProductDTO product = prepareProductDTO("Geladeira", new BigDecimal("2000"));

		purchaseOrder.setCustomer(customer);
		purchaseOrder.setAddress(address);
		purchaseOrder.setProduct(product);

		return purchaseOrder;
	}

	private static Product prepareProduct(String name, BigDecimal price) {
		return Product.builder()
				.name(name)
				.price(price)
				.build();
	}

	private static Address prepareAddress(String city, String state, String zip, String street) {
		return Address.builder()
				.city(city)
				.state(state)
				.zip(zip)
				.street(street)
				.build();
	}

	private static CustomerDTO prepareCustomerDTO(String name, String ssn, Date birthDate, BigDecimal credit) {
		return CustomerDTO.builder()
				.name(name)
				.ssn(ssn)
				.birthDate(birthDate)
				.credit(credit)
				.build();
	}

	private static ProductDTO prepareProductDTO(String name, BigDecimal price) {
		return ProductDTO.builder()
				.name(name)
				.price(price)
				.build();
	}

	private static AddressDTO prepareAddressDTO(String city, String state, String zip, String street) {
		return AddressDTO.builder()
				.city(city)
				.state(state)
				.zip(zip)
				.street(street)
				.build();
	}

	private static Customer prepareCustomer(String name, String ssn, Date birthDate, BigDecimal credit) {
		return Customer.builder()
				.name(name)
				.ssn(ssn)
				.birthDate(birthDate)
				.credit(credit)
				.build();
	}

}
