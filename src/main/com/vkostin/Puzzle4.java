package com.vkostin;

import java.util.ArrayList;
import java.util.List;

/**
 * would store cells in single List<Cell>
 */
public class Puzzle4 implements IPuzzle {
  private final List<Cell> cells = new ArrayList<>();

  public Puzzle4(Cell[][] cells) {
  }

  @Override
  public ValueCell findFirstUnsolvedValueCellOrNull() {
    return null;
  }

  @Override
  public boolean hasErrors() {
    return false;
  }

  @Override
  public List<ValueCell> valueCellsBelowCellAt(int cellRowIndex, int cellColumnIndex) {
    return null;
  }

  @Override
  public List<ValueCell> valueCellsOnTheRightFromCellAt(int cellRowIndex, int cellColumnIndex) {
    return null;
  }
}
