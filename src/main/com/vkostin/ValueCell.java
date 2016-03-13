package com.vkostin;

public class ValueCell implements Cell {
  private int value = -1;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
