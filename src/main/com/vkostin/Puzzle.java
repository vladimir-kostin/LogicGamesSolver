package com.vkostin;

import java.util.Arrays;

/**
 * stores cells in 2D array
 */
public class Puzzle implements IPuzzle {
  final private Cell[][] cells;

  public Puzzle(Cell[][] cells) {
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
    if (o instanceof Puzzle) { return Arrays.deepEquals(cells, ((Puzzle) o).cells); }
    if (o instanceof IPuzzle) { return isEqualToPuzzle((IPuzzle) o); }
    return false;
  }

  @Override
  public int hashCode() { return Arrays.deepHashCode(cells); }

}
