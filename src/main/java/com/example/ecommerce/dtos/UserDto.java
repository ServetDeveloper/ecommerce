package com.example.ecommerce.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
public class UserDto {

  @JsonIgnore
  private Long id;
  private String name;
  private String email;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String phoneNumber;

}
