package com.example.ecommerce.services;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.dtos.RegisterUserRequest;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.mappers.ProductMapper;
import com.example.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public List<ProductDto> getAllProducts(Byte categoryId) {
    List<Product> products;
    if(categoryId != null) {
      products = productRepository.findByCategoryId(categoryId);
    } else {
      products = productRepository.findAll();
    }

    return products
      .stream()
      .map(productMapper::toProductDto)
      .toList();
  }

  public Optional<ProductDto> getProductById(Long id) {
    return productRepository.findById(id)
      .map(productMapper::toProductDto);
  }


}
