package com.vkostin;

import java.util.List;

public interface IPuzzle {
  int getRowCount();
  int getRowLength();
  Cell getCellAt(int rowIndex, int columnIndex);

  ValueCell findFirstUnsolvedValueCellOrNull();

  // do NOT like this to be on puzzle
  boolean hasErrors();

  List<ValueCell> valueCellsBelowCellAt(int cellRowIndex, int cellColumnIndex);
  List<ValueCell> valueCellsOnTheRightFromCellAt(int cellRowIndex, int cellColumnIndex);
}
