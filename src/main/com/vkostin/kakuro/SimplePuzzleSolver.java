package com.vkostin.kakuro;

import com.vkostin.*;

class SimplePuzzleSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  public static class SolverInstance extends AbstractSolverInstance {
    public SolverInstance(Puzzle puzzle) { super(puzzle); }

    @Override
    protected boolean isCurrentAssumptionWrong() {
      for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
          Cell aCell = puzzle.getCellAt(rowIndex, columnIndex);
          if (aCell instanceof TaskCell) {
            TaskCell taskCell = (TaskCell) aCell;
            if (0 < taskCell.getSumOfValuesBelow()) {
              if(ValueCell.doValueCellsFailToMeetExpectation(taskCell.getSumOfValuesBelow(), getValueCellsBelowThan(rowIndex, columnIndex), Rules::hasProperValue, false)) return true;
            }
            if (0 < taskCell.getSumOfValuesOnTheRight()) {
              if (ValueCell.doValueCellsFailToMeetExpectation(taskCell.getSumOfValuesOnTheRight(), getValueCellsOnTheRightFrom(rowIndex, columnIndex), Rules::hasProperValue, false)) return true;
            }
          }
        }
      }
      return false;
    }

    @Override
    protected ValueCell findUnsolvedValueCellOfNull() {
      for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
          Cell aCell = puzzle.getCellAt(rowIndex, columnIndex);
          if (aCell instanceof ValueCell) {
            ValueCell valueCell = (ValueCell) aCell;
            if(Rules.isUnsolved(valueCell)) { return valueCell; }
          }
        }
      }
      return null;
    }
  }

}
