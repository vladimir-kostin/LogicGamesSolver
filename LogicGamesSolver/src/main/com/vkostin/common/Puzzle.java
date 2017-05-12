package com.vkostin.common;

public interface Puzzle {
  int rowCount();
  int rowLength();
  Cell at(int rowIndex, int columnIndex);

  default boolean isEqualToPuzzle(Puzzle other) {
    if (rowCount() != other.rowCount()) return false;
    if (rowLength() != other.rowLength()) return false;

    for (int rowIndex = 0; rowIndex < rowCount(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < rowLength(); columnIndex++) {
        if (!at(rowIndex, columnIndex).equals(
                other.at(rowIndex, columnIndex))) {
          return false;
        }
      }
    }
    return true;
  }

}
