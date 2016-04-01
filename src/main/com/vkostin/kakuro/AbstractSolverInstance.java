package com.vkostin.kakuro;

import com.vkostin.Cell;
import com.vkostin.Puzzle;
import com.vkostin.ValueCell;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractSolverInstance {
  protected final Puzzle puzzle;
  protected AbstractSolverInstance(Puzzle puzzle) {
    this.puzzle = puzzle;
  }

  public Puzzle solve() {
    if (isCurrentAssumptionWrong()) return null;

    ValueCell unsolvedValueCell = findUnsolvedValueCellOfNull();
    if (null == unsolvedValueCell) return puzzle;

    for (int valueToTry = Rules.MIN_ALLOWED_VALUE; valueToTry <= Rules.MAX_ALLOWED_VALUE; valueToTry++) {
      unsolvedValueCell.setValue(valueToTry);
      Puzzle result = solve();
      if (null != result) return result;
    }

    Rules.clearValue(unsolvedValueCell);
    return null;
  }

  protected abstract boolean isCurrentAssumptionWrong();
  protected abstract ValueCell findUnsolvedValueCellOfNull();

  // TODO "failed to meet expectations"
  protected boolean doValueCellsMeetExpectations(List<ValueCell> valueCells, int expectedSumOfValues) {
    int actualSumOfValues = valueCells.stream()
            .filter(Rules::hasProperValue)
            .mapToInt(ValueCell::getValue)
            .sum();

    if(expectedSumOfValues < actualSumOfValues) { return false; }

    boolean containsUnresolvedValueCells = valueCells.stream()
            .anyMatch(Rules::isUnsolved);

    if(!containsUnresolvedValueCells && expectedSumOfValues != actualSumOfValues) { return  false; }

    long properValuesCount = valueCells.stream()
            .filter(Rules::hasProperValue)
            .count();

    long distinctProperValuesCount = valueCells.stream()
            .filter(Rules::hasProperValue)
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
