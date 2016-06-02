package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnotherSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  static class SolverInstance extends AbstractSolverInstanse {
    final private List<Integer> _assumptions;
    public SolverInstance(Puzzle puzzle) {
      super(puzzle);
      _assumptions = IntStream.rangeClosed(Rules.MIN_ALLOWED_VALUE, Rules.MAX_ALLOWED_VALUE)
              .boxed()
              .collect(Collectors.toList());
    }

    @Override
    protected CellWithCoordinates<Cell> findUnsolvedCell() {
      return _withCoords.cells().stream()
              .filter(this::isValueCellUnsolved)
              .findFirst()
              .orElse(null);
    }

    private boolean isValueCellUnsolved(CellWithCoordinates<Cell> cell) {
      return Optional.of(cell)
              .map(CellWithCoordinates::cell)
              .map(c -> c.as(ValueCell.class))
              .map(Rules::hasUnsolvedValue)
              .orElse(false);
    }

    @Override
    protected List<?> assumptionsToBeMade() { return _assumptions; }

    @Override
    protected boolean isAnyRuleBroken(CellWithCoordinates<Cell> cell) {
      if (doesColumnContainError(cell.columnIndex())) return true;
      if (!areAllProperValueUniqueInRow(cell.rowIndex())) return true;
      if (!valuesInContiguousCellsAreDifferent(cell)) return true;

      return false;
    }

    private boolean doesColumnContainError(int columnIndex) {
      List<ValueCell> valueCells = new ArrayList<>();
      for (int rowIndex = 0; rowIndex < _puzzle.getRowCount() - 1; rowIndex++) {
        valueCells.add(valueCellAt(rowIndex, columnIndex));
      }

//      int expectedSumOfValues = _puzzle.getCellAt(_puzzle.getRowCount()-1, columnIndex).as(TaskCell.class).getSumOfValuesAbove();
      int expectedSumOfValues = ((TaskCell)_puzzle.getCellAt(_puzzle.getRowCount()-1, columnIndex)).getSumOfValuesAbove();
      return ValueCell.doValueCellsFailToMeetExpectation(expectedSumOfValues, valueCells, Rules::hasProperValue, true);
    }

    private ValueCell valueCellAt(int rowIndex, int colIndex) {
//      return _puzzle.getCellAt(rowIndex, colIndex).as(ValueCell.class);
      return (ValueCell)_puzzle.getCellAt(rowIndex, colIndex);
    }

    private boolean areAllProperValueUniqueInRow(int rowIndex) {
      List<ValueCell> valueCellsWithProperValues = new ArrayList<>();
      for (int colIndex = 0; colIndex < _puzzle.getRowLength(); colIndex++) {
        Optional.of(valueCellAt(rowIndex, colIndex))
                .filter(Rules::hasProperValue)
                .ifPresent(valueCellsWithProperValues::add);
      }

      long countOfDistinctValues = valueCellsWithProperValues.stream()
              .mapToInt(ValueCell::getValue)
              .distinct()
              .count();

      return valueCellsWithProperValues.size() == countOfDistinctValues;
    }

    private boolean valuesInContiguousCellsAreDifferent(CellWithCoordinates<Cell> cell) {
//      int value = cell.cell().as(ValueCell.class).getValue();
      int value = ((ValueCell)cell.cell()).getValue();
      if (!threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(value, cell.rowIndex()-1, cell.columnIndex()-1)) return false;
      if (!threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(value, cell.rowIndex()+1, cell.columnIndex()-1)) return false;

      return true;
    }

    private boolean threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(int valueToCheck, int rowIndex, int lowestColumnIndex) {
      if (0 > rowIndex || _puzzle.getRowCount() - 1 <= rowIndex) return true;

      for (int colIndex = Math.max(lowestColumnIndex, 0); colIndex <= Math.min(lowestColumnIndex + 2, _puzzle.getRowLength() - 1); colIndex++) {
        if (Optional.of(valueCellAt(rowIndex, colIndex))
                .filter(Rules::hasProperValue)
                .map(ValueCell::getValue)
                .filter(value -> valueToCheck == value)
                .isPresent()) return false;
      }
      return true;
    }

    @Override
    protected void setValue(Object value, CellWithCoordinates<Cell> cell) {
      ((ValueCell)cell.cell()).setValue((Integer) value);
    }

    @Override
    protected void clearValue(CellWithCoordinates<Cell> cell) {
      Rules.clearValue((ValueCell) cell.cell());
    }

  }

}
