package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping()
  public List<ProductDto> getAllProducts(
    @RequestParam(required = false, defaultValue = "") Byte categoryId) {
    return productService.getAllProducts(categoryId);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
    return productService.getProductById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

//  @PostMapping
//  public ResponseEntity<ProductDto> createProduct(@RequestParam ProductDto productDto) {
//    if(productDto == null) {
//      ResponseEntity.notFound().build();
//    }
//
//    ResponseEntity.ok(ProductDto);
//  }
}
