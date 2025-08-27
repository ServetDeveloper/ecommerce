package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
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

  @PostMapping
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDtoParam,
                                                  UriComponentsBuilder uriBuilder) {
    ProductDto productDto = productService.createProduct(productDtoParam);

    URI uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
    return ResponseEntity.created(uri).body(productDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                  @RequestBody ProductDto productDtoParam) {
    ProductDto productDto = productService.updateProduct(id, productDtoParam);

    return ResponseEntity.ok(productDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) {
    ProductDto productDto = productService.deleteProduct(id);

    return ResponseEntity.ok(productDto);
  }
}
