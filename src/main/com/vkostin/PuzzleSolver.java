package com.vkostin;

import java.util.ArrayList;
import java.util.List;

public class PuzzleSolver {

  public static IPuzzle s_solve(IPuzzle puzzle) {
    if(hasErrors(puzzle)) {
      return null;
    }

    ValueCell unsolvedValueCell = findUnsolvedValueCellOrNull(puzzle);
    if (null == unsolvedValueCell) {
      return puzzle;
    }

    for (int valueToBeTried = ValueCell.MIN_ALLOWED_VALUE; valueToBeTried <= ValueCell.MAX_ALLOWED_VALUE; valueToBeTried++) {
      unsolvedValueCell.setValue(valueToBeTried);
      IPuzzle result = s_solve(puzzle);
      if(null != result) { return result; }
    }

    unsolvedValueCell.clearValue();
    return null;
  }

  public IPuzzle solve(IPuzzle puzzle) {
    if(puzzle.hasErrors()) {
      return null;
    }

    ValueCell unsolvedValueCell = puzzle.findFirstUnsolvedValueCellOrNull();
    if (null == unsolvedValueCell) {
      return puzzle;
    }

    // assumptions
    for (int valueToBeTried = ValueCell.MIN_ALLOWED_VALUE; valueToBeTried <= ValueCell.MAX_ALLOWED_VALUE; valueToBeTried++) {
      unsolvedValueCell.setValue(valueToBeTried);
      IPuzzle result = solve(puzzle);
      if(null != result) { return result; }
    }

    // no assumptions fit
    unsolvedValueCell.clearValue();
    return null;
  }

  private static boolean hasErrors(IPuzzle puzzle) {
    for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
        Cell aCell = puzzle.getCellAt(rowIndex, columnIndex);
        if (aCell instanceof TaskCell) {
          TaskCell taskCell = (TaskCell) aCell;
          if (0 < taskCell.getSumOfValuesBelow()) {
            if(!doValueCellsMeetExpectations(getValueCellsBelowThan(rowIndex, columnIndex, puzzle), taskCell.getSumOfValuesBelow())) { return true; }
          }
          if (0 < taskCell.getSumOfValuesOnTheRight()) {
            if(!doValueCellsMeetExpectations(getValueCellsOnTheRightFrom(rowIndex, columnIndex, puzzle), taskCell.getSumOfValuesOnTheRight())) { return true; }
          }
        }
      }
    }
    return false;
  }

  private static boolean doValueCellsMeetExpectations(List<ValueCell> valueCells, int expectedSumOfValues) {
    int actualSumOfValues = valueCells.stream()
            .filter(ValueCell::hasProperValue)
            .mapToInt(ValueCell::getValue)
            .sum();

    if(expectedSumOfValues < actualSumOfValues) { return false; }

    boolean containsUnresolvedValueCells = valueCells.stream()
            .anyMatch(ValueCell::isUnsolved);

    if(!containsUnresolvedValueCells && expectedSumOfValues != actualSumOfValues) { return  false; }

    long properValuesCount = valueCells.stream()
            .filter(ValueCell::hasProperValue)
            .count();

    long distinctProperValuesCount = valueCells.stream()
            .filter(ValueCell::hasProperValue)
            .mapToInt(ValueCell::getValue)
            .distinct()
            .count();

    return properValuesCount == distinctProperValuesCount;
  }

  private static List<ValueCell> getValueCellsBelowThan(int cellRowIndex, int cellColumnIndex, IPuzzle puzzle) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int rowIndex = cellRowIndex+1; rowIndex < puzzle.getRowCount(); rowIndex++) {
      Cell aCell = puzzle.getCellAt(rowIndex, cellColumnIndex);
      if(aCell instanceof ValueCell) {
        valueCells.add((ValueCell) aCell);
      } else {
        break;
      }
    }
    return valueCells;
  }

  private static List<ValueCell> getValueCellsOnTheRightFrom(int cellRowIndex, int cellColumnIndex, IPuzzle puzzle) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int columnIndex = cellColumnIndex+1; columnIndex < puzzle.getRowLength(); columnIndex++) {
      Cell aCell = puzzle.getCellAt(cellRowIndex, columnIndex);
      if(aCell instanceof ValueCell) {
        valueCells.add((ValueCell) aCell);
      } else {
        break;
      }
    }
    return valueCells;
  }

  private static ValueCell findUnsolvedValueCellOrNull(IPuzzle puzzle) {
    for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
        Cell aCell = puzzle.getCellAt(rowIndex, columnIndex);
        if (aCell instanceof ValueCell) {
          ValueCell valueCell = (ValueCell) aCell;
          if (valueCell.isUnsolved()) { return valueCell; }
        }
      }
    }
    return null;
  }

}
