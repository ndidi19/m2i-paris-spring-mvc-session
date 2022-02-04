package com.ndiaye.mvcsession.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String label;
    private String description;
    private BigDecimal price;

    public Product(String label, String description, BigDecimal price) {
        this.label = label;
        this.description = description;
        this.price = price;
    }
}
