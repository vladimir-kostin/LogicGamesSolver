package com.vkostin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Puzzle3 implements IPuzzle {

  private List<List<Cell>> cells = new ArrayList<>();

  public Puzzle3(Cell[][] cells) {
    for (Cell[] row : cells) {
      this.cells.add(Arrays.asList(row));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Puzzle3)) return false;
    Puzzle3 puzzle3 = (Puzzle3) o;
    return Objects.equals(cells, puzzle3.cells);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cells);
  }

  @Override
  public ValueCell findFirstUnsolvedValueCellOrNull() {
    return cells.stream()
            .flatMap(List::stream)
            .filter(cell -> cell instanceof ValueCell)
            .map(cell -> (ValueCell) cell)
            .filter(ValueCell::isUnsolved)
            .findFirst()
            .orElse(null);
  }

  @Override
  public boolean hasErrors() {
    for (int j = 0; j < cells.size(); j++) {
      for (int k = 0; k < cells.get(0).size(); k++) {
        if (doesCellHaveAnyErrors(j, k)) return true;
      }
    }
    return false;
  }

  private boolean doesCellHaveAnyErrors(int cellRowIndex, int cellColumnIndex) {
    Cell cell = cells.get(cellRowIndex).get(cellColumnIndex);
    if (cell instanceof ValueCell) return false;
    if (cell instanceof TaskCell) {
      return hasErrorsBelow((TaskCell) cell, cellRowIndex, cellColumnIndex)
              || hasErrorsOnTheRight((TaskCell) cell, cellRowIndex, cellColumnIndex);
    }

    return true;
  }

  private boolean hasErrorsBelow(TaskCell taskCell, int cellRowIndex, int cellColumnIndex) {
    if (0 == taskCell.getSumOfValuesBelow()) return false;
    return containsErrors(valueCellsBelowCell(cellRowIndex, cellColumnIndex), taskCell.getSumOfValuesBelow());
  }

  private boolean hasErrorsOnTheRight(TaskCell taskCell, int cellRowIndex, int cellColumnIndex) {
    if (0 == taskCell.getSumOfValueOnTheRight()) return false;
    return containsErrors(valueCellsOnTheRightFromCell(cellRowIndex, cellColumnIndex), taskCell.getSumOfValueOnTheRight());
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

  private List<ValueCell> valueCellsBelowCell(int cellRowIndex, int cellColumnIndex) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int rowIndex = cellRowIndex + 1; rowIndex < cells.size(); rowIndex++) {
      if (cells.get(rowIndex).get(cellColumnIndex) instanceof ValueCell) {
        valueCells.add((ValueCell) cells.get(rowIndex).get(cellColumnIndex));
      } else {
        break;
      }
    }
    return valueCells;
  }

  private List<ValueCell> valueCellsOnTheRightFromCell(int cellRowIndex, int cellColumnIndex) {
    List<ValueCell> valueCells = new ArrayList<>();

    for (int columnIndex = cellColumnIndex + 1; columnIndex < cells.get(cellRowIndex).size(); columnIndex++) {
      if (cells.get(cellRowIndex).get(columnIndex) instanceof ValueCell) {
        valueCells.add((ValueCell) cells.get(cellRowIndex).get(columnIndex));
      } else {
        break;
      }
    }
    return valueCells;
  }

}
