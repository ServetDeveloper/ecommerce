package com.example.ecommerce.validation.validators;

import com.example.ecommerce.services.UserService;
import com.example.ecommerce.validation.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final UserService userService;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (userService.existsByEmail(email)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("This " + email + " is already exists")
        .addPropertyNode(email)
        .addConstraintViolation();
      return false;
    }
    return true;
  }
}
