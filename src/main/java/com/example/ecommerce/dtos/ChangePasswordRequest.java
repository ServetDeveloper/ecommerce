package com.example.ecommerce.dtos;

import com.example.ecommerce.validation.annotations.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@PasswordsMatch(password = "newPassword", confirmPassword = "confirmNewPassword")
public class ChangePasswordRequest {
  @NotBlank
  private String oldPassword;

  @NotBlank
  private String newPassword;

  @NotBlank
  private String confirmNewPassword;
}
