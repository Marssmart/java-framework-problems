package org.deer.jackson.problem.one.type.serialization.dto;

import java.io.Serializable;

public class ComplexType implements Serializable {

  private final int valueOne;
  private final long valueTwo;


  public ComplexType(int valueOne, long valueTwo) {
    this.valueOne = valueOne;
    this.valueTwo = valueTwo;
  }

  public int getValueOne() {
    return valueOne;
  }

  public long getValueTwo() {
    return valueTwo;
  }
}
