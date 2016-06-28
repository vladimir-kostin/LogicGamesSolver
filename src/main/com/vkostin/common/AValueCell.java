package com.vkostin.common;

import java.util.Objects;

/**
 * Cell which needed to be solved.
 */
public abstract class AValueCell<V extends IValue> implements Cell {
  private V _value;

  public V value() { return _value; }
  public void setValue(V value) { this._value = value; }

  public void clearValue() { setValue(null); }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AValueCell<?> that = (AValueCell<?>) o;
    return Objects.equals(_value, that._value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_value);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + " {" +
            "_value=" + _value +
            '}';
  }

}
