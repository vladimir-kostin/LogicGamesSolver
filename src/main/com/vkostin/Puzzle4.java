package com.vkostin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * would store cells in single List<Cell>
 */
public class Puzzle4 implements IPuzzle {
  private final int rowCount;
  private final int rowLength;
  private final List<Cell> cells = new ArrayList<>();

  public Puzzle4(Cell[][] cells) {
    rowCount = cells.length;
    rowLength = cells[0].length;
    for (Cell[] row : cells) {
      this.cells.addAll(Arrays.asList(row));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Puzzle4)) return false;
    Puzzle4 puzzle4 = (Puzzle4) o;
    return rowCount == puzzle4.rowCount &&
            rowLength == puzzle4.rowLength &&
            Objects.equals(cells, puzzle4.cells);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowCount, rowLength, cells);
  }

  @Override
  public int getRowCount() { return rowCount; }

  @Override
  public int getRowLength() { return rowLength; }

  @Override
  public Cell getCellAt(int rowIndex, int columnIndex) { return cells.get(rowIndex * rowLength + columnIndex); }

  @Override
  public ValueCell findFirstUnsolvedValueCellOrNull() {
    return cells.stream()
            .filter(cell -> cell instanceof ValueCell)
            .map(cell -> (ValueCell) cell)
            .filter(ValueCell::isUnsolved)
            .findFirst()
            .orElse(null);
  }

  @Override
  public boolean hasErrors() {
    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < rowLength; columnIndex++) {
        if (doesCellHaveAnyErrors(rowIndex, columnIndex)) return true;
      }
    }
    return false;
  }

  private boolean doesCellHaveAnyErrors(int cellRowIndex, int cellColumnIndex) {
    Cell cell = cells.get(cellRowIndex * rowLength + cellColumnIndex);
    if (cell instanceof ValueCell) return false;
    if (cell instanceof TaskCell) {
      return hasErrorsBelow((TaskCell) cell, cellRowIndex, cellColumnIndex)
              || hasErrorsOnTheRight((TaskCell) cell, cellRowIndex, cellColumnIndex);
    }
    return true;
  }

  private boolean hasErrorsBelow(TaskCell taskCell, int cellRowIndex, int cellColumnIndex) {
    if (0 == taskCell.getSumOfValuesBelow()) return false;
    return containsErrors(valueCellsBelowCellAt(cellRowIndex, cellColumnIndex), taskCell.getSumOfValuesBelow());
  }

  private boolean hasErrorsOnTheRight(TaskCell taskCell, int cellRowIndex, int cellColumnIndex) {
    if (0 == taskCell.getSumOfValuesOnTheRight()) return false;
    return containsErrors(valueCellsOnTheRightFromCellAt(cellRowIndex, cellColumnIndex), taskCell.getSumOfValuesOnTheRight());
  }

  private static boolean containsErrors(List<ValueCell> valueCells, int expectedSumOfValues) {
    int actualSumOfValues = valueCells.stream()
            .filter(ValueCell::hasProperValue)
            .mapToInt(ValueCell::getValue)
            .sum();

    if (expectedSumOfValues < actualSumOfValues) return true;

    boolean containsUnresolvedValueCells = valueCells.stream()
            .anyMatch(ValueCell::isUnsolved);

    if (!containsUnresolvedValueCells && expectedSumOfValues != actualSumOfValues) return true;

    long properValuesCount = valueCells.stream()
            .filter(ValueCell::hasProperValue)
            .count();

    long distinctProperValuesCount = valueCells.stream()
            .filter(ValueCell::hasProperValue)
            .mapToInt(ValueCell::getValue)
            .distinct()
            .count();

    return (properValuesCount != distinctProperValuesCount);
  }

  @Override
  public List<ValueCell> valueCellsBelowCellAt(int cellRowIndex, int cellColumnIndex) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int cellIndex = (cellRowIndex+1)*rowLength+cellColumnIndex; cellIndex < cells.size(); cellIndex+=rowLength) {
      Cell xyz = cells.get(cellIndex);
      if (cells.get(cellIndex) instanceof ValueCell) {
        valueCells.add((ValueCell) cells.get(cellIndex));
      } else {
        break;
      }
    }
    return valueCells;
  }

  @Override
  public List<ValueCell> valueCellsOnTheRightFromCellAt(int cellRowIndex, int cellColumnIndex) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int cellIndex = cellRowIndex * rowLength + cellColumnIndex + 1; cellIndex < (cellRowIndex + 1) * rowLength; cellIndex++) {
      if (cells.get(cellIndex) instanceof ValueCell) {
        valueCells.add((ValueCell) cells.get(cellIndex));
      } else {
        break;
      }
    }
    return valueCells;
  }
}
