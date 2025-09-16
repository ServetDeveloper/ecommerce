package com.example.ecommerce.dtos;

import com.example.ecommerce.validation.annotations.PasswordsMatch;
import com.example.ecommerce.validation.groups.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@PasswordsMatch(password = "newPassword", confirmPassword = "confirmNewPassword")
public class ChangePasswordRequest {
  @NotBlank(groups = OnUpdate.class)
  private String oldPassword;

  @NotBlank(groups = OnUpdate.class)
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
    message = "Password must contain upper, lower, and digit")
  private String newPassword;

  @NotBlank(groups = OnUpdate.class)
  private String confirmNewPassword;
}
