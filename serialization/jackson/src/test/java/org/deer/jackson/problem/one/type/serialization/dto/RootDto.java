package org.deer.jackson.problem.one.type.serialization.dto;

import java.io.Serializable;

public class RootDto implements Serializable {

  private final String name;
  private final ArrayType[] array;
  private final ComplexType complex;


  public RootDto(String name, ArrayType[] array, ComplexType complex) {
    this.name = name;
    this.array = array;
    this.complex = complex;
  }

  public String getName() {
    return name;
  }

  public ArrayType[] getArray() {
    return array;
  }

  public ComplexType getComplex() {
    return complex;
  }
}
