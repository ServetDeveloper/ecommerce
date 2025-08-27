package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  ProductDto toProductDto(Product product);

  @Mapping(target = "id", ignore = true)
  Product toProductEntity(ProductDto productDto);

  @Mapping(target = "id", ignore = true)
  void update(ProductDto productDto, @MappingTarget Product product);
}
