package com.vkostin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle2 implements IPuzzle {

  private List<List<Cell>> cells = new ArrayList<>();

  public Puzzle2(Cell[][] cells) {
    for (Cell[] row : cells) {
      this.cells.add(Arrays.asList(row));
    }
  }

  @Override
  public ValueCell findFirstUnsolvedValueCellOrNull() {
    return cells.stream()
            .flatMap(List::stream)
            .filter(cell -> cell instanceof ValueCell)
            .map(cell -> (ValueCell)cell)
            .filter(ValueCell::isUnsolved)
            .findFirst()
            .orElse(null);
  }

  @Override
  public boolean hasErrors() {
    return cells.stream()
            .flatMap(List::stream)
            .filter(cell -> cell instanceof TaskCell)
            .map(cell -> (TaskCell) cell)
            .anyMatch(this::doesTaskCellHaveAnyErrors);
  }

  private boolean doesTaskCellHaveAnyErrors(TaskCell taskCell) {
    if(0 < taskCell.getSumOfValuesBelow()) {
      List<ValueCell> valueCellsBelow = getValueCellsBelow(taskCell);
      if(containsError(valueCellsBelow, taskCell.getSumOfValuesBelow())) { return true; }
    }
    if(0 < taskCell.getSumOfValueOnTheRight()) {
      List<ValueCell> valueCellsOnTheRight = getValueCellsOnTheRight(taskCell);
      if(containsError(valueCellsOnTheRight, taskCell.getSumOfValueOnTheRight())) { return true; }
    }
    return false;
  }

  private static boolean containsError(List<ValueCell> valueCells, int expectedSumOfValues) {
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

  private List<ValueCell> getValueCellsOnTheRight(TaskCell taskCell) {
    for (List<Cell> row : cells) {
      int ndx = indexOfCellInRow(taskCell, row);
      if (-1 == ndx) continue;
    }
  }

  private int indexOfCellInRow(Cell cell, List<Cell> row) {
    for (Cell c : row) {
      if (c == cell) { return row.indexOf(c); }
    }
    return -1;
  }

  private List<ValueCell> getValueCellsBelow(TaskCell taskCell) {
  }

}
