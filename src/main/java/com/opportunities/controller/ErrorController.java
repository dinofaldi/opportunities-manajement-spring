package com.opportunities.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.opportunities.model.WebResponse;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {
  
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(WebResponse.<String>builder().errors(e.getMessage()).build());
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException e) {
    return ResponseEntity.status(e.getStatusCode())
      .body(WebResponse.<String>builder().errors(e.getReason()).build());
  }
}
