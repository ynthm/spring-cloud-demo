package com.ynthm.infra.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Ethan Wang
 */
@Data
@Accessors(chain = true)
public class PhoneNumber {

  @Min(value = 0)
  private String areaCode;

  @Min(value = 0)
  @NotNull
  private String number;
}
