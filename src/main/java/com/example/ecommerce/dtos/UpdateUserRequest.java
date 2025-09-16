package com.example.ecommerce.dtos;

import com.example.ecommerce.validation.annotations.UniqueEmail;
import com.example.ecommerce.validation.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
  @NotBlank(groups = OnUpdate.class)
  @Size(min = 2, max = 20)
  private String name;

  @NotBlank(groups = OnUpdate.class)
  @Email
//  @UniqueEmail
  private String email;
}
