package com.vkostin;

import java.util.Arrays;
import java.util.List;

public class Puzzle5 implements IPuzzle {
  final private Cell[][] cells;

  public Puzzle5(Cell[][] cells) {
    this.cells = cells;
  }

  @Override
  public int getRowCount() { return cells.length; }

  @Override
  public int getRowLength() { return cells[0].length; }

  @Override
  public Cell getCellAt(int rowIndex, int columnIndex) { return cells[rowIndex][columnIndex]; }

  @Override
  public ValueCell findFirstUnsolvedValueCellOrNull() {
    throw new RuntimeException();
  }

  @Override
  public boolean hasErrors() {
    throw new RuntimeException();
  }

  @Override
  public List<ValueCell> valueCellsBelowCellAt(int cellRowIndex, int cellColumnIndex) {
    throw new RuntimeException();
  }

  @Override
  public List<ValueCell> valueCellsOnTheRightFromCellAt(int cellRowIndex, int cellColumnIndex) {
    throw new RuntimeException();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Puzzle5)) return false;
    Puzzle5 puzzle5 = (Puzzle5) o;
    return Arrays.deepEquals(cells, puzzle5.cells);
  }

  @Override
  public int hashCode() { return Arrays.deepHashCode(cells); }
}
