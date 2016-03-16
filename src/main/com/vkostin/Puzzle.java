package com.vkostin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle implements IPuzzle {
  final private Cell[][] cells;

  public Puzzle(Cell[][] cells) {
    this.cells = cells;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Puzzle)) return false;
    Puzzle puzzle = (Puzzle) o;
    return Arrays.deepEquals(cells, puzzle.cells);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(cells);
  }

  @Override
  public String toString() {
    return "Puzzle{" +
            "cells=" + Arrays.deepToString(cells) +
            '}';
  }

  @Override
  public ValueCell findFirstUnsolvedValueCellOrNull() {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        if (cell instanceof ValueCell) {
          ValueCell valueCell = (ValueCell) cell;
          if (valueCell.isUnsolved()) { return valueCell; }
        }
      }
    }

    return null;
  }

  @Override
  public boolean hasErrors() {
    for (int j = 0; j < cells.length; j++) {
      for (int k = 0; k < cells[j].length; k++) {
        if (doesCellHaveAnyErrors(j, k)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean doesCellHaveAnyErrors(int cellRowIndex, int cellColumnIndex) {
    Cell cell = cells[cellRowIndex][cellColumnIndex];
    if(cell instanceof ValueCell) { return false; }
    if(cell instanceof TaskCell) {
      return hasErrorsBelow((TaskCell) cell, cellRowIndex, cellColumnIndex)
              || hasErrorsOnTheRight((TaskCell) cell, cellRowIndex, cellColumnIndex);
    }

    return true;
  }

  private boolean hasErrorsBelow(TaskCell taskCell, int cellRowIndex, int cellColumnIndex) {
    if (0 == taskCell.getSumOfValuesBelow()) { return false; }
    return containsErrors(valueCellsBelowCell(cellRowIndex, cellColumnIndex), taskCell.getSumOfValuesBelow());
  }

  private boolean hasErrorsOnTheRight(TaskCell taskCell, int cellRowIndex, int cellColumnIndex) {
    if (0 == taskCell.getSumOfValueOnTheRight()) { return false; }
    return containsErrors(valueCellsOnTheRightFromCell(cellRowIndex, cellColumnIndex), taskCell.getSumOfValueOnTheRight());
  }

  private static boolean containsErrors(List<ValueCell> valueCells, int expectedSumOfValues) {
    int actualSumOfValues = valueCells.stream()
            .filter(ValueCell::hasProperValue)
            .mapToInt(ValueCell::getValue)
            .sum();

    if(expectedSumOfValues < actualSumOfValues) { return true; }

    boolean containsUnresolvedValueCells = valueCells.stream()
            .anyMatch(ValueCell::isUnsolved);

    if(!containsUnresolvedValueCells && expectedSumOfValues != actualSumOfValues) { return  true; }

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

  private List<ValueCell> valueCellsBelowCell(int cellRowIndex, int cellColumnIndex) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int j = cellRowIndex + 1; j < cells.length; j++) {
      if(cells[j][cellColumnIndex] instanceof ValueCell) {
        valueCells.add((ValueCell) cells[j][cellColumnIndex]);
      } else {
        break;
      }
    }
    return valueCells;
  }

  private List<ValueCell> valueCellsOnTheRightFromCell(int cellRowIndex, int cellColumnIndex) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int j = cellColumnIndex + 1; j < cells[cellRowIndex].length; j++) {
      if (cells[cellRowIndex][j] instanceof ValueCell) {
        valueCells.add((ValueCell) cells[cellRowIndex][j]);
      } else {
        break;
      }
    }
    return valueCells;
  }
}
