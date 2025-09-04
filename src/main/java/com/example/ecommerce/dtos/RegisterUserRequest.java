package com.example.ecommerce.dtos;

import com.example.ecommerce.validation.annotations.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
  @NotBlank
  @Size(min = 2, max = 20)
  private String name;

  @Email
  @NotBlank
  @UniqueEmail
  private String email;

  @NotBlank
  @Size(min = 8, max = 20)
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
    message = "Password must contain upper, lower, and digit")
  private String password;
}
