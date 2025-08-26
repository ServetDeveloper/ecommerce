package com.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
  @JsonIgnore
  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private Byte categoryId;
}
