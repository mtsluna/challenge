package com.tenpo.challenge._shared.exception.handler;

import java.time.Instant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<CustomExceptionHandlerMessage> handleAllException(Exception e, WebRequest request) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            CustomExceptionHandlerMessage
              .builder()
              .message(e.getMessage())
              .description("Something wen wrong!")
              .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
              .path(request.getContextPath())
              .build()
        );
  }

}
