package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnotherSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  static class SolverInstance extends AbstractSolverInstanse {
    public SolverInstance(Puzzle puzzle) { super(puzzle); }

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
    protected List<?> assumptionsToBeMade() {
      return IntStream.rangeClosed(Rules.MIN_ALLOWED_VALUE, Rules.MAX_ALLOWED_VALUE)
              .boxed()
              .collect(Collectors.toList());
    }

    @Override
    protected boolean isAnyRuleBroken(CellWithCoordinates<Cell> cell) {
      if (doesColumnContainError(cell.columnIndex())) return true;
      if (!areAllProperValueUniqueInRow(cell.rowIndex())) return true;
      if (!valuesInContiguousCellsAreDifferent(cell)) return true;

      return false;
    }

    private boolean doesColumnContainError(int columnIndex) {
      List<ValueCell> valueCells = IntStream.range(0, _puzzle.getRowCount()-1)
              .boxed()
              .map(rowIndex -> _puzzle.getCellAt(rowIndex, columnIndex))
              .map(c -> c.as(ValueCell.class))
              .filter(Objects::nonNull)
              .collect(Collectors.toList());

      int expectedSumOfValues = _puzzle.getCellAt(_puzzle.getRowCount()-1, columnIndex).as(TaskCell.class).getSumOfValuesAbove();
      return ValueCell.doValueCellsFailToMeetExpectation(expectedSumOfValues, valueCells, Rules::hasProperValue, true);
    }

    private boolean areAllProperValueUniqueInRow(int rowIndex) {
      List<ValueCell> valueCellsWithProperValues =
      IntStream.range(0, _puzzle.getRowLength())
              .boxed()
              .map(colIndex -> _puzzle.getCellAt(rowIndex, colIndex))
              .map(c -> c.as(ValueCell.class))
              .filter(Rules::hasProperValue)
              .collect(Collectors.toList());

      long countOfDistinctValues = valueCellsWithProperValues.stream()
              .mapToInt(ValueCell::getValue)
              .distinct()
              .count();

      return valueCellsWithProperValues.size() == countOfDistinctValues;
    }

    private boolean valuesInContiguousCellsAreDifferent(CellWithCoordinates<Cell> cell) {
      int value = cell.cell().as(ValueCell.class).getValue();
      if (!threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(value, cell.rowIndex()-1, cell.columnIndex()-1)) return false;
      if (!threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(value, cell.rowIndex()+1, cell.columnIndex()-1)) return false;

      return true;
    }

    private boolean threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(int valueToCheck, int rowIndex, int lowestColumnIndex) {
      if (0 > rowIndex || _puzzle.getRowCount() - 1 <= rowIndex) return true;

      return IntStream.rangeClosed(Math.max(lowestColumnIndex, 0), Math.min(lowestColumnIndex + 2, _puzzle.getRowLength()-1))
              .boxed()
              .map(colIndex -> _puzzle.getCellAt(rowIndex, colIndex))
              .map(cell -> cell.as(ValueCell.class))
              .filter(Objects::nonNull)
              .filter(Rules::hasProperValue)
              .map(ValueCell::getValue)
              .noneMatch(value -> valueToCheck == value);
    }

    @Override
    protected void setValue(Object value, CellWithCoordinates<Cell> cell) {
      cell.cell().as(ValueCell.class).setValue((Integer) value);
    }

    @Override
    protected void clearValue(CellWithCoordinates<Cell> cell) {
      Rules.clearValue(cell.cell().as(ValueCell.class));
    }

  }

}
