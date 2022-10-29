package com.ynthm.infra.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Ethan Wang
 */
@Accessors(chain = true)
@Data
public class PlaceholderAndValue {
  private String placeholder;
  private String value;
}
