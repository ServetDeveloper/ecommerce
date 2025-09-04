package com.example.ecommerce.exception;

import java.time.Instant;
import java.util.Map;

public record ApiError(
  int status,
  String message,
  String path,
  Map<String, String> errors,
  Instant timeStamp
) {

  public static ApiError of(int status, String message, String path, Map<String, String> errors) {
    return new ApiError(status, message, path, errors, Instant.now());
  }
}
