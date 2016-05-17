package com.vkostin.common;

import java.util.Arrays;

/**
 * stores cells in 2D array
 */
public class PuzzleAsArray implements Puzzle {
  final private Cell[][] cells;

  public PuzzleAsArray(Cell[][] cells) {
    this.cells = cells;
  }

  @Override
  public int getRowCount() { return cells.length; }
  @Override
  public int getRowLength() { return cells[0].length; }
  @Override
  public Cell getCellAt(int rowIndex, int columnIndex) { return cells[rowIndex][columnIndex]; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof PuzzleAsArray) { return Arrays.deepEquals(cells, ((PuzzleAsArray) o).cells); }
    if (o instanceof Puzzle) { return isEqualToPuzzle((Puzzle) o); }
    return false;
  }

  @Override
  public int hashCode() { return Arrays.deepHashCode(cells); }

  @Override
  public String toString() {
    return "PuzzleAsArray{" +
            "cells=" + Arrays.deepToString(cells) +
            '}';
  }

}
