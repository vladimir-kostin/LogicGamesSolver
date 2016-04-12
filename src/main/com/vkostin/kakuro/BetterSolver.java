package com.vkostin.kakuro;

import com.vkostin.*;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

class BetterSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  public static class SolverInstance extends AbstractSolverInstance {
    private final List<ValueCell> valueCells = new ArrayList<>();
    private final List<TaskCell> taskCells = new ArrayList<>();

    private final Map<TaskCell, List<ValueCell>> valuesBelow = new IdentityHashMap<>();
    private final Map<TaskCell, List<ValueCell>> valuesOnTheRight = new IdentityHashMap<>();

    public SolverInstance(Puzzle puzzle) {
      super(puzzle);

      for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
          Cell aCell = puzzle.getCellAt(rowIndex, columnIndex);
          if (aCell instanceof ValueCell) {
            valueCells.add((ValueCell) aCell);
          }
          if (aCell instanceof TaskCell) {
            TaskCell taskCell = (TaskCell) aCell;
            taskCells.add(taskCell);
            valuesBelow.put(taskCell, getValueCellsBelowThan(rowIndex, columnIndex));
            valuesOnTheRight.put(taskCell, getValueCellsOnTheRightFrom(rowIndex, columnIndex));
          }
        }
      }
    }

    @Override
    protected boolean isCurrentAssumptionWrong() {
      return taskCells.stream()
              .anyMatch(this::isAssumptionWrongForTaskCell);
    }

    private boolean isAssumptionWrongForTaskCell(TaskCell taskCell) {
      if (0 < taskCell.getSumOfValuesBelow()) {
        if (ValueCell.doValueCellsFailToMeetExpectation(taskCell.getSumOfValuesBelow(), valuesBelow.get(taskCell), Rules::hasProperValue, false)) return true;
      }
      if (0 < taskCell.getSumOfValuesOnTheRight()) {
        if (ValueCell.doValueCellsFailToMeetExpectation(taskCell.getSumOfValuesOnTheRight(), valuesOnTheRight.get(taskCell), Rules::hasProperValue, false)) return true;
      }
      return false;
    }

    @Override
    protected ValueCell findUnsolvedValueCellOfNull() {
      return valueCells.stream()
              .filter(Rules::isUnsolved)
              .findAny()
              .orElse(null);
    }
  }

}
