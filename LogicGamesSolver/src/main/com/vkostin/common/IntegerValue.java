package com.vkostin.common;

import java.util.Objects;

public class IntegerValue implements IValue {
  private final int _value;

  public IntegerValue(final int value) {
    this._value = value;
  }

  public int value() { return _value; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IntegerValue that = (IntegerValue) o;
    return _value == that._value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(_value);
  }

  @Override
  public String toString() {
    return "IntegerValue{" +
            "_value=" + _value +
            '}';
  }

}
