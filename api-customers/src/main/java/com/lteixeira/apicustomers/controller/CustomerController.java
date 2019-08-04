package com.lteixeira.apicustomers.controller;

import com.lteixeira.apicustomers.dto.CustomerDTO;
import com.lteixeira.apicustomers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    ResponseEntity<List<CustomerDTO>> findAll(){ return ResponseEntity.ok(this.customerService.findAll());}

    @PostMapping
    ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO){

        final CustomerDTO customerDb = this.customerService.save(customerDTO);

        customerDTO.setId(customerDb.getId());

        final URI uri = UriComponentsBuilder
                .fromPath("customers/{customerId}")
                .buildAndExpand(customerDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(customerDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(this.customerService.findById(id).get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        this.customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody CustomerDTO customerDTO ){
        this.customerService.update(id, customerDTO);
        return ResponseEntity.ok().build();
    }
}
