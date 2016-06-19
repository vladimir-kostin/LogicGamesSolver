package com.vkostin.common;

public class IntegerValue implements IValue {
  private final int _value;

  public IntegerValue(final int value) {
    this._value = value;
  }

  public int value() { return _value; }

}
