package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.dtos.RegisterUserRequest;
import com.example.ecommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductDto toProductDto(Product product);

}
