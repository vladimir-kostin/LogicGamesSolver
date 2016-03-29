package com.vkostin.kakuro;

import com.vkostin.Cell;
import com.vkostin.IPuzzle;
import com.vkostin.ValueCell;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractSolverInstance {
  protected final IPuzzle puzzle;
  protected AbstractSolverInstance(IPuzzle puzzle) {
    this.puzzle = puzzle;
  }

  public IPuzzle solve() {
    if (isCurrentAssumptionWrong()) return null;

    ValueCell unsolvedValueCell = findUnsolvedValueCellOfNull();
    if (null == unsolvedValueCell) return puzzle;

    for (int valueToTry = ValueCell.MIN_ALLOWED_VALUE; valueToTry <= ValueCell.MAX_ALLOWED_VALUE; valueToTry++) {
      unsolvedValueCell.setValue(valueToTry);
      IPuzzle result = solve();
      if (null != result) return result;
    }

    unsolvedValueCell.clearValue();
    return null;
  }

  protected abstract boolean isCurrentAssumptionWrong();
  protected abstract ValueCell findUnsolvedValueCellOfNull();

  protected boolean doValueCellsMeetExpectations(List<ValueCell> valueCells, int expectedSumOfValues) {
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

  protected List<ValueCell> getValueCellsBelowThan(int cellRowIndex, int cellColumnIndex) {
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

  protected List<ValueCell> getValueCellsOnTheRightFrom(int cellRowIndex, int cellColumnIndex) {
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

}
