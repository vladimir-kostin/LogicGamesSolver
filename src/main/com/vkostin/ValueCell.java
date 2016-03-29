package com.vkostin;

import java.util.Objects;

public class ValueCell implements Cell {

  private int value;

  public ValueCell(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
  public void setValue(int value) { this.value = value; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ValueCell)) return false;
    ValueCell valueCell = (ValueCell) o;
    return value == valueCell.value;
  }

  @Override
  public int hashCode() { return Objects.hash(value); }

}
