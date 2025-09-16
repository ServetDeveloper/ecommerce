package com.example.ecommerce.mappers;

import com.example.ecommerce.dtos.RegisterUserRequest;
import com.example.ecommerce.dtos.UpdateUserRequest;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toUserDto(User user);

  User toUserEntity(RegisterUserRequest request);

  void update(UpdateUserRequest request, @MappingTarget User user);

}
