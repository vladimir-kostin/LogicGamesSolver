package com.vkostin;

import java.util.Objects;

public class ValueCell implements Cell, Cloneable {
  private final static int UNSOLVED_VALUE = -1;
  public static final int MIN_ALLOWED_VALUE = 1;
  public static final int MAX_ALLOWED_VALUE = 9;

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
    if(MIN_ALLOWED_VALUE > value || MAX_ALLOWED_VALUE < value) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }
  public void clearValue() { this.value = UNSOLVED_VALUE; }

  public boolean hasProperValue() { return UNSOLVED_VALUE != value; }
  public boolean isUnsolved() { return UNSOLVED_VALUE == value; }

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
