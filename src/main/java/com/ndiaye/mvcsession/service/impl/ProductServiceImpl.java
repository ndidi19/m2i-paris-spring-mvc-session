package com.ndiaye.mvcsession.service.impl;

import com.ndiaye.mvcsession.dto.ProductDto;
import com.ndiaye.mvcsession.entity.Product;
import com.ndiaye.mvcsession.repository.ProductRepository;
import com.ndiaye.mvcsession.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product p : productList) {
            productDtoList.add(new ProductDto(
                    p.getId(),
                    p.getLabel(),
                    p.getDescription(),
                    p.getPrice()
            ));
        }
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product p = productRepository.getById(id);
        return new ProductDto(
                p.getId(),
                p.getLabel(),
                p.getDescription(),
                p.getPrice()
        );
    }
}
