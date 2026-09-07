package com.regysmendes.inventoryapi.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductResponseDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal price;

    public ProductResponseDTO(Long id, String name, Integer quantity, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseDTO productResponseDTO = (ProductResponseDTO) o;
        return Objects.equals(id, productResponseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

