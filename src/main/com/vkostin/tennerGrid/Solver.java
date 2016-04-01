package com.vkostin.tennerGrid;

import com.vkostin.Cell;
import com.vkostin.Puzzle;
import com.vkostin.ValueCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solver implements com.vkostin.Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) { return new SolverInstance(puzzle).solve(); }

  public static class SolverInstance {
    private final Puzzle _puzzle;

    public SolverInstance(Puzzle puzzle) { _puzzle = puzzle; }

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

    private boolean isCurrentAssumptionWrong() {
      for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
        if(doesColumnContainErrors(columnIndex)) return true;
      }

      for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
        if(doesRowContainNonUniqueProperValues(rowIndex)) return true;
      }

      return false;
    }

    private boolean doesColumnContainErrors(int columnIndex) {
      List<ValueCell> valueCells = new ArrayList<>();
      for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
        valueCells.add(getValueCellOrNull(rowIndex, columnIndex));
      }

      int expectedSumOfValues = expectedSumOfValuesInColumn(columnIndex);

      int actualSumOfValues = valueCells.stream()
              .filter(Objects::nonNull)
              .filter(Rules::hasProperValue)
              .mapToInt(ValueCell::getValue)
              .sum();

      if (expectedSumOfValues < actualSumOfValues) return true;

      boolean containsUnresolvedCells = valueCells.stream()
              .anyMatch(Rules::hasUnsolvedValue);

      return containsUnresolvedCells || expectedSumOfValues != actualSumOfValues;
    }

    private boolean doesRowContainNonUniqueProperValues(int rowIndex) {
      List<ValueCell> valueCells = new ArrayList<>();
      for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
        valueCells.add(getValueCellOrNull(rowIndex, columnIndex));
      }

      long countOfProperValueCells = valueCells.stream()
              .filter(Objects::nonNull)
              .filter(Rules::hasProperValue)
              .count();

      long countOfDistinctValues = valueCells.stream()
              .filter(Objects::nonNull)
              .filter(Rules::hasProperValue)
              .mapToInt(ValueCell::getValue)
              .distinct()
              .count();

      return countOfProperValueCells == countOfDistinctValues;
    }

    private ValueCell getValueCellOrNull(int rowIndex, int columnIndex) {
      Cell aCell = _puzzle.getCellAt(rowIndex, columnIndex);
      return (aCell instanceof ValueCell) ? (ValueCell) aCell : null;
    }

    private int expectedSumOfValuesInColumn(int columnIndex) {
      return ((TaskCell)_puzzle.getCellAt(_puzzle.getRowCount() - 2, columnIndex)).getSumOfValuesAbove();
    }

    private ValueCell findUnsolvedValueCellOrNull() {
      for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
          Cell aCell = _puzzle.getCellAt(rowIndex, columnIndex);
          if (aCell instanceof ValueCell) {
            ValueCell valueCell = (ValueCell) aCell;
            if (Rules.hasUnsolvedValue(valueCell)) return valueCell;
          }
        }
      }
      return null;
    }

  }

}
