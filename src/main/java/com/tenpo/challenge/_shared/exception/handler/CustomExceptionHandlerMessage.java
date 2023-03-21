package com.tenpo.challenge._shared.exception.handler;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class CustomExceptionHandlerMessage {

  private String description;
  private String message;
  private Instant timestamp;
  private HttpStatus httpStatus;

}
