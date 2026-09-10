package com.regysmendes.inventoryapi.resources;

import com.regysmendes.inventoryapi.dto.ProductInsertDTO;
import com.regysmendes.inventoryapi.dto.ProductResponseDTO;
import com.regysmendes.inventoryapi.dto.ProductUpdateDTO;
import com.regysmendes.inventoryapi.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(name = "/products")
public class ProductResource {

    private final ProductService service;


    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> responseDTO = service.findAll();
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        ProductResponseDTO responseDTO = service.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }


    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductInsertDTO dto) {
        ProductResponseDTO responseDTO = service.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductUpdateDTO dto){
        ProductResponseDTO responseDTO = service.update(id, dto);
        return ResponseEntity.ok().body(responseDTO);
    }



}
