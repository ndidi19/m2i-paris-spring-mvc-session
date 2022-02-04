package com.ndiaye.mvcsession.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductForm {

    private String label;
    private String description;
    private BigDecimal price;
}
