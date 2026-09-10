package com.regysmendes.inventoryapi.services;

import com.regysmendes.inventoryapi.dto.ProductInsertDTO;
import com.regysmendes.inventoryapi.dto.ProductResponseDTO;
import com.regysmendes.inventoryapi.dto.ProductUpdateDTO;
import com.regysmendes.inventoryapi.entities.Product;
import com.regysmendes.inventoryapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;


    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponseDTO> findAll() {
        List<Product> list = repository.findAll();
        List<ProductResponseDTO> result = new ArrayList<>();

        for (Product obj : list) {

            ProductResponseDTO responseDTO = new ProductResponseDTO(obj.getId(), obj.getName(), obj.getQuantity(), obj.getPrice());
            result.add(responseDTO);
        }
        return result;
    }

    public Product findByIdentity(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(() -> new IllegalArgumentException("Id not found " + id));
    }

    public ProductResponseDTO findById(Long id) {
        Product product = findByIdentity(id);
        return new ProductResponseDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice());
    }

    public ProductResponseDTO insert(ProductInsertDTO dto) {
        Product product = new Product(null, dto.getName(), dto.getQuantity(), dto.getPrice());
        repository.save(product);
        ProductResponseDTO responseDTO = new ProductResponseDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice());
        return responseDTO;
    }

    public ProductResponseDTO update(Long id, ProductUpdateDTO updateDTO) {
        Product product = findByIdentity(id);
        repository.save(product);
        updateHandler(product, updateDTO);
        return new ProductResponseDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice());
    }

    public void updateHandler(Product product, ProductUpdateDTO dto) {
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
    }

    public BigDecimal getStockTotalValue() {

        List<ProductResponseDTO> listProducts = findAll();

        BigDecimal totalValue = listProducts.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalValue;
    }

    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

}
