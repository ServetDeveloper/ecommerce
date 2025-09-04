package com.example.ecommerce.validation.validators;

import com.example.ecommerce.validation.annotations.PasswordsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

  String passwordField;
  String confirmPasswordField;
  String message;

  @Override
  public void initialize(PasswordsMatch ann) {
    passwordField = ann.password();
    confirmPasswordField = ann.confirmPassword();
    message = ann.message();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext context) {
    if (o == null) return true;

    try {
      BeanWrapperImpl wrapper = new BeanWrapperImpl(o);
      Object password = wrapper.getPropertyValue(passwordField);
      Object confirm = wrapper.getPropertyValue(confirmPasswordField);

      boolean matches = java.util.Objects.equals(password, confirm);

      if (!matches) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
          .addPropertyNode(confirmPasswordField)
          .addConstraintViolation();
      }

      return matches;

    } catch (Exception ex) {
      return false;
    }

  }
}
