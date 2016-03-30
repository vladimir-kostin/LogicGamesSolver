package com.vkostin.kakuro;

import com.vkostin.*;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

class BetterPuzzleSolver implements IPuzzleSolver {

  @Override
  public IPuzzle solve(IPuzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  public static class SolverInstance extends AbstractSolverInstance {
    private final List<ValueCell> valueCells = new ArrayList<>();
    private final List<TaskCell> taskCells = new ArrayList<>();

    private final Map<TaskCell, List<ValueCell>> valuesBelow = new IdentityHashMap<>();
    private final Map<TaskCell, List<ValueCell>> valuesOnTheRight = new IdentityHashMap<>();

    public SolverInstance(IPuzzle puzzle) {
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
        if (!doValueCellsMeetExpectations(valuesBelow.get(taskCell), taskCell.getSumOfValuesBelow())) return true;
      }
      if (0 < taskCell.getSumOfValuesOnTheRight()) {
        if (!doValueCellsMeetExpectations(valuesOnTheRight.get(taskCell), taskCell.getSumOfValuesOnTheRight())) return true;
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
