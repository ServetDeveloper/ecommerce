package com.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
  @JsonIgnore
  private Long id;

  @NotBlank
  @Size(min = 2, max = 15)
  private String name;

  @Size(max = 100)
  private String description;

  private BigDecimal price;
  private Byte categoryId;
}
