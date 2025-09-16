package com.example.ecommerce.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleNotFount (EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(InvalidPasswordException.class)
  public ResponseEntity<String> handleUnauthorized (InvalidPasswordException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleValidation (MethodArgumentNotValidException ex, HttpServletRequest req) {
    Map<String, String> errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .collect(Collectors.toMap(FieldError::getField,
        (error) -> Optional.ofNullable(error.getDefaultMessage()).orElse("Validation error"),
        (existing, duplicate) -> existing
      ));

    return ApiError.of(400, "Validation failed", req.getRequestURI(), errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError handleConstraint (ConstraintViolationException ex, HttpServletRequest req) {
    Map<String, String> errors = ex.getConstraintViolations().stream()
      .collect(Collectors.toMap(
        v -> v.getPropertyPath().toString(),
        ConstraintViolation::getMessage,
        (existing, duplicate) -> existing
      ));

    return ApiError.of(400, "Validation failed", req.getRequestURI(), errors);
  }

}
