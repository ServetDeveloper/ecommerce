package com.example.ecommerce.services;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.mappers.ProductMapper;
import com.example.ecommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class  ProductService {

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

  public ProductDto createProduct(ProductDto productDto) {
    Product product = productMapper.toProductEntity(productDto);
    productDto.setId(product.getId());

    productRepository.save(product);

    return productMapper.toProductDto(product);
  }

  public ProductDto updateProduct(Long id, ProductDto productDto) {
    Product product = productRepository.findById(id).orElseThrow(
      () -> new EntityNotFoundException("Product not found with this id: " + id));

    productMapper.update(productDto, product);
    productRepository.save(product);

    return productMapper.toProductDto(product);
  }

  public ProductDto deleteProduct(Long id) {
    Product product = productRepository.findById(id).orElseThrow(
      () -> new EntityNotFoundException("Product not found wtih this id: " + id));

    productRepository.deleteById(id);

    return productMapper.toProductDto(product);
  }
}
