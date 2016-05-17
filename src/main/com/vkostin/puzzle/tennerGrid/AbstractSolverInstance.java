package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.Puzzle;
import com.vkostin.common.ValueCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract class AbstractSolverInstance {
  protected final Puzzle _puzzle;

  protected AbstractSolverInstance(Puzzle puzzle) { _puzzle = puzzle; }

  public Puzzle solve() {
    if(isCurrentAssumptionWrong()) return null;

    ValueCell unsolvedValueCell = findUnsolvedValueCellOrNull();
    if(null == unsolvedValueCell) return _puzzle;

    for (int valueToTry = Rules.MIN_ALLOWED_VALUE; valueToTry <= Rules.MAX_ALLOWED_VALUE; valueToTry++) {
      unsolvedValueCell.setValue(valueToTry);
      Puzzle result = solve();
      if (null != result) return result;
    }

    Rules.clearValue(unsolvedValueCell);
    return null;
  }

  protected boolean isCurrentAssumptionWrong() {
    for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
      if (doesColumnContainErrors(columnIndex)) return true;
    }

    for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
      if (!areAllProperValueUniqueInRow(rowIndex)) return true;
    }

    for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
        Optional<ValueCell> valueCell = valueCell(rowIndex, columnIndex);
        if (!valueCell.isPresent()) continue;

        if (valueCell.filter(Rules::hasUnsolvedValue)
                .isPresent()) continue;

        final int atRow = rowIndex;
        final int atCol = columnIndex;
        if (!valueCell.map(ValueCell::getValue)
                .filter(value -> threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(value, atRow-1, atCol-1))
                .filter(value -> threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(value, atRow+1, atCol-1))
                .isPresent()) return true;
      }
    }

    return false;
  }

  protected boolean doesColumnContainErrors(int columnIndex) {
    List<ValueCell> valueCells = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
      valueCell(rowIndex, columnIndex)
              .ifPresent(valueCells::add);
    }

    int expectedSumOfValues = expectedSumOfValuesInColumn(columnIndex);
    return ValueCell.doValueCellsFailToMeetExpectation(expectedSumOfValues, valueCells, Rules::hasProperValue, true);
  }

  protected boolean areAllProperValueUniqueInRow(int rowIndex) {
    List<ValueCell> valueCellsWithProperValues = new ArrayList<>();
    for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
      valueCell(rowIndex, columnIndex)
              .filter(Rules::hasProperValue)
              .ifPresent(valueCellsWithProperValues::add);
    }

    long countOfDistinctValues = valueCellsWithProperValues.stream()
            .mapToInt(ValueCell::getValue)
            .distinct()
            .count();

    return valueCellsWithProperValues.size() == countOfDistinctValues;
  }

  protected Optional<ValueCell> valueCell(int rowIndex, int columnIndex) {
    return Optional.of(_puzzle.getCellAt(rowIndex, columnIndex))
            .map(cell -> cell.as(ValueCell.class));
  }

  private int expectedSumOfValuesInColumn(int columnIndex) {
    return ((TaskCell)_puzzle.getCellAt(_puzzle.getRowCount() - 1, columnIndex)).getSumOfValuesAbove();
  }

  protected boolean threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(final int valueToCheck, int rowIndex, int lowestColumnIndex) {
    if (0 > rowIndex) return true;
    if (_puzzle.getRowCount() - 1 <= rowIndex) return true;

    for (int columnIndex = Math.max(lowestColumnIndex, 0); columnIndex <= Math.min(lowestColumnIndex+2, _puzzle.getRowLength()-1); columnIndex++) {
      if(valueCell(rowIndex, columnIndex)
              .filter(Rules::hasProperValue)
              .map(ValueCell::getValue)
              .filter(value -> valueToCheck == value)
              .isPresent()) return false;
    }
    return true;
  }

  // TODO may be it would be possible to return Optional ?
  private ValueCell findUnsolvedValueCellOrNull() {
    for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
        Optional<ValueCell> unsolvedValueCell = valueCell(rowIndex, columnIndex)
                .filter(Rules::hasUnsolvedValue);
        if (unsolvedValueCell.isPresent()) return unsolvedValueCell.get();
      }
    }
    return null;
  }

}
