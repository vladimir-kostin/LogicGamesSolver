package com.vkostin.common;

/**
 * Cell which needed to be solved.
 */
public abstract class AValueCell<V extends IValue> implements Cell {
  private V _value;

  public V value() { return _value; }
  public void setValue(V value) { this._value = value; }

  public void clearValue() { setValue(null); }

}
