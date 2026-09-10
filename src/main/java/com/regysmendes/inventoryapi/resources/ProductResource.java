package com.regysmendes.inventoryapi.resources;

import com.regysmendes.inventoryapi.dto.ProductResponseDTO;
import com.regysmendes.inventoryapi.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "/products")
public class ProductResource {

    private final ProductService service;


    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
       List<ProductResponseDTO> responseDTO = service.findAll();
       return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        ProductResponseDTO responseDTO = service.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }
    
}
