package com.vkostin;

public interface Cell {
  /**
   * safely casts to @param clazz (if possible) or returns null
   */
  default <T> T as(Class<T> clazz) {
    return getClass().isAssignableFrom(clazz) ? (T)this : null;
  }

}
