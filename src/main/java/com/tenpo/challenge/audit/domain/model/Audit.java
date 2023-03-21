package com.tenpo.challenge.audit.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Audit {

  private Long id;

  private String method;

  private String path;

  private Object response;

}
