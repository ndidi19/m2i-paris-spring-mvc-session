package com.ndiaye.mvcsession.service;

import com.ndiaye.mvcsession.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);
}
