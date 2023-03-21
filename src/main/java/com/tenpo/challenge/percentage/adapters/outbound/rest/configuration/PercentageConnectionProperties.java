package com.tenpo.challenge.percentage.adapters.outbound.rest.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercentageConnectionProperties {

  private String url;
  private String path;

}
