package com.lteixeira.apipayments.controller;

import com.lteixeira.apipayments.dto.PurchaseOrderDTO;
import com.lteixeira.apipayments.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    ResponseEntity<List<PurchaseOrderDTO>> findAll(){ return ResponseEntity.ok(this.purchaseOrderService.findAll());}

    @PostMapping
    ResponseEntity<PurchaseOrderDTO> save(@RequestBody PurchaseOrderDTO purchaseOrderDTO){

        final PurchaseOrderDTO purchaseOrderDb = this.purchaseOrderService.save(purchaseOrderDTO);

        purchaseOrderDTO.setId(purchaseOrderDb.getId());
        purchaseOrderDTO.setOrderStatus(purchaseOrderDb.getOrderStatus());

        final URI uri = UriComponentsBuilder
                .fromPath("transactions/{purchaseId}")
                .buildAndExpand(purchaseOrderDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(purchaseOrderDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PurchaseOrderDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(this.purchaseOrderService.findById(id).get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        this.purchaseOrderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO ){
        this.purchaseOrderService.update(id, purchaseOrderDTO);
        return ResponseEntity.ok().build();
    }
}
