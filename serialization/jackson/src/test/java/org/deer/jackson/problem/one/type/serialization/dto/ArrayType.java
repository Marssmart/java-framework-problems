package org.deer.jackson.problem.one.type.serialization.dto;

import java.io.Serializable;

public class ArrayType implements Serializable {

  private final String value;

  public ArrayType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
