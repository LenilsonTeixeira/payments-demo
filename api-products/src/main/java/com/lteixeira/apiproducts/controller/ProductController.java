package com.lteixeira.apiproducts.controller;

import com.lteixeira.apiproducts.dto.ProductDTO;
import com.lteixeira.apiproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    ResponseEntity<List<ProductDTO>> findAll(){ return ResponseEntity.ok(this.productService.findAll());}

    @PostMapping
    ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO productDTO){
        final String productId = UUID.randomUUID().toString();

        productDTO.setId(productId);

        this.productService.save(productDTO);

        final URI uri = UriComponentsBuilder
                .fromPath("products/{productId}")
                .buildAndExpand(productDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(productDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.productService.findById(id).get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id){
        this.productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @Valid @RequestBody ProductDTO productDTO ){
        this.productService.update(id, productDTO);
        return ResponseEntity.ok().build();
    }
}