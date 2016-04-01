package com.vkostin.tennerGrid;

import com.vkostin.Puzzle;
import com.vkostin.ValueCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if(!areAllProperValueUniqueInRow(rowIndex)) return true;
      }

      return false;
    }

    private boolean doesColumnContainErrors(int columnIndex) {
      List<ValueCell> valueCells = new ArrayList<>();
      for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
        valueCell(rowIndex, columnIndex)
                .ifPresent(valueCells::add);
      }

      int expectedSumOfValues = expectedSumOfValuesInColumn(columnIndex);

      int actualSumOfValues = valueCells.stream()
              .filter(Rules::hasProperValue)
              .mapToInt(ValueCell::getValue)
              .sum();

      if (expectedSumOfValues < actualSumOfValues) return true;

      boolean containsUnresolvedCells = valueCells.stream()
              .anyMatch(Rules::hasUnsolvedValue);

      return !containsUnresolvedCells && expectedSumOfValues != actualSumOfValues;
    }

    private boolean areAllProperValueUniqueInRow(int rowIndex) {
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

    private Optional<ValueCell> valueCell(int rowIndex, int columnIndex) {
      return Optional.of(_puzzle.getCellAt(rowIndex, columnIndex))
              .map(cell -> cell.as(ValueCell.class));
    }

    private int expectedSumOfValuesInColumn(int columnIndex) {
      return ((TaskCell)_puzzle.getCellAt(_puzzle.getRowCount() - 1, columnIndex)).getSumOfValuesAbove();
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

}
