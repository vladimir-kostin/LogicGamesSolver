package com.vkostin;

import java.util.Objects;

public class ValueCell implements Cell {
  private final static int UNSOLVED_VALUE = -1;

  public static ValueCell newValueCell(int value) { return new ValueCell(value); }

  private int value;

  public ValueCell() { this(UNSOLVED_VALUE); }
  public ValueCell(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ValueCell valueCell = (ValueCell) o;
    return value == valueCell.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "ValueCell{" +
            "value=" + value +
            '}';
  }
}
