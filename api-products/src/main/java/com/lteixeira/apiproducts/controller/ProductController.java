package com.lteixeira.apiproducts.controller;

import com.lteixeira.apiproducts.dto.ProductDTO;
import com.lteixeira.apiproducts.model.Product;
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

        final ProductDTO productDb = this.productService.save(productDTO);

        productDTO.setId(productDb.getId());

        final URI uri = UriComponentsBuilder
                .fromPath("products/{productId}")
                .buildAndExpand(productDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(productDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.productService.findById(id).get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        this.productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO productDTO ){
        this.productService.update(id, productDTO);
        return ResponseEntity.ok().build();
    }
}
