package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Data
public class Product {
  @Id()
  @Column(name = "product_id", nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private String description;

  @Column(nullable = false)
  private BigDecimal price;
  private Byte categoryId;
}
