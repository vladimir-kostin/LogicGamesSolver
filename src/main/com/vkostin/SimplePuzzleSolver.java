package com.vkostin;

public class SimplePuzzleSolver implements IPuzzleSolver {

  @Override
  public IPuzzle solve(IPuzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  public static class SolverInstance extends AbstractSolverInstance {
    public SolverInstance(IPuzzle puzzle) { super(puzzle); }

    @Override
    protected boolean isCurrentAssumptionWrong() {
      for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
          Cell aCell = puzzle.getCellAt(rowIndex, columnIndex);
          if (aCell instanceof TaskCell) {
            TaskCell taskCell = (TaskCell) aCell;
            if (0 < taskCell.getSumOfValuesBelow()) {
              if(!doValueCellsMeetExpectations(getValueCellsBelowThan(rowIndex, columnIndex), taskCell.getSumOfValuesBelow())) { return true; }
            }
            if (0 < taskCell.getSumOfValuesOnTheRight()) {
              if(!doValueCellsMeetExpectations(getValueCellsOnTheRightFrom(rowIndex, columnIndex), taskCell.getSumOfValuesOnTheRight())) { return true; }
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
            if (valueCell.isUnsolved()) { return valueCell; }
          }
        }
      }
      return null;
    }
  }

}
