package com.vkostin;

public interface Puzzle {
  int getRowCount();
  int getRowLength();
  Cell getCellAt(int rowIndex, int columnIndex);

  default boolean isEqualToPuzzle(Puzzle other) {
    if (getRowCount() != other.getRowCount()) return false;
    if (getRowLength() != other.getRowLength()) return false;

    for (int rowIndex = 0; rowIndex < getRowCount(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getRowLength(); columnIndex++) {
        if (!getCellAt(rowIndex, columnIndex).equals(
                other.getCellAt(rowIndex, columnIndex))) {
          return false;
        }
      }
    }
    return true;
  }

}
