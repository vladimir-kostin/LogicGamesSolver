package com.vkostin.tennerGrid;

import com.vkostin.Puzzle;
import com.vkostin.Solver;
import com.vkostin.ValueCell;

import java.util.Optional;

public class BetterSolver implements Solver {
  @Override
  public Puzzle solve(Puzzle puzzle) {
    return null;
  }

  static class SolverInstance extends AbstractSolverInstance {
    public SolverInstance(Puzzle puzzle) { super(puzzle); }

    @Override
    public Puzzle solve() {
//      if(isCurrentAssumptionWrong()) return null;

      ValueCellsWithCoordinates unsolvedValueCell = findUnsolvedValueCellOrNull();
      if(null == unsolvedValueCell) return _puzzle;

      for (int valueToTry = Rules.MIN_ALLOWED_VALUE; valueToTry <= Rules.MAX_ALLOWED_VALUE; valueToTry++) {
        unsolvedValueCell.valueCell().setValue(valueToTry);

        if (doesColumnContainErrors(unsolvedValueCell.columnIndex())) continue;
        if (!areAllProperValueUniqueInRow(unsolvedValueCell.rowIndex())) continue;
        if (!valuesInContiguousCellsAreDifferent(unsolvedValueCell)) continue;

        Puzzle result = solve();
        if (null != result) return result;
      }

      Rules.clearValue(unsolvedValueCell.valueCell());
      return null;
    }

    private boolean valuesInContiguousCellsAreDifferent(ValueCellsWithCoordinates valueCell) {
      if (!threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(
              valueCell.valueCell().getValue(), valueCell.rowIndex()-1, valueCell.columnIndex()-1)) {
        return false;
      }

      if (!threeConsecutiveValueCellsInRowDoNotHaveValuesEqualTo(
              valueCell.valueCell().getValue(), valueCell.rowIndex()+1, valueCell.columnIndex()-1)) {
        return false;
      }

      return true;
    }

    private ValueCellsWithCoordinates findUnsolvedValueCellOrNull() {
      for (int rowIndex = 0; rowIndex < _puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < _puzzle.getRowLength(); columnIndex++) {
          Optional<ValueCell> unsolvedValueCell = valueCell(rowIndex, columnIndex)
                  .filter(Rules::hasUnsolvedValue);
          if (unsolvedValueCell.isPresent()) {
            return new ValueCellsWithCoordinates(unsolvedValueCell.get(), rowIndex, columnIndex);
          }
        }
      }
      return null;
    }

    static class ValueCellsWithCoordinates {
      private final int _rowIndex;
      private final int _columnIndex;
      private final ValueCell _valueCell;

      ValueCellsWithCoordinates(ValueCell valueCell, int atRowIndex, int atColumnIndex) {
        _valueCell = valueCell;
        _rowIndex = atRowIndex;
        _columnIndex = atColumnIndex;
      }

      public int rowIndex() { return _rowIndex; }
      public int columnIndex() { return _columnIndex; }
      public ValueCell valueCell() { return _valueCell; }
    }
  }

}
