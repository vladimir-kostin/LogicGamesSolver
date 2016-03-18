package com.vkostin;

import java.util.List;

public interface IPuzzle {
  ValueCell findFirstUnsolvedValueCellOrNull();

  // do NOT like this to be on puzzle
  boolean hasErrors();

  List<ValueCell> valueCellsBelowCellAt(int cellRowIndex, int cellColumnIndex);
  List<ValueCell> valueCellsOnTheRightFromCellAt(int cellRowIndex, int cellColumnIndex);
}
