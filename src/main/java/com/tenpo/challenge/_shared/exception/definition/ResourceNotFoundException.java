package com.tenpo.challenge._shared.exception.definition;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends Exception {

  public ResourceNotFoundException(String message) {
    super(message);
  }

}
