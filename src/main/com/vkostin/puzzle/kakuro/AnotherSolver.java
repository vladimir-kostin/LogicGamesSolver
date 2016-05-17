package com.vkostin.puzzle.kakuro;

import com.vkostin.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * idea is to check after each step only related cells, NOT whole puzzle for validity (non-presence of errors)
 */
public class AnotherSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) { return new SolverInstance(puzzle).solve(); }

  static class SolverInstance extends AbstractSolverInstance {
    private final List<ValueCellWithCoordinates> _valueCells = new ArrayList<>();

    public SolverInstance(Puzzle puzzle) {
      super(puzzle);

      for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
          final int atRow = rowIndex;
          final int atCol = columnIndex;
          Optional.ofNullable(puzzle.getCellAt(atRow, atCol))
                  .map(c -> c.as(ValueCell.class))
                  .map(vc -> new ValueCellWithCoordinates(vc, atRow, atCol))
                  .ifPresent(_valueCells::add);
        }
      }
    }

    // TODO deal with comments
    public Puzzle solve() {
//      if (isCurrentAssumptionWrong()) return null;

      ValueCellWithCoordinates unsolvedValueCell = findUnsolvedValueCellWithCoordinatesOrNull();
      if (null == unsolvedValueCell) return puzzle;

      for (int valueToTry = Rules.MIN_ALLOWED_VALUE; valueToTry <= Rules.MAX_ALLOWED_VALUE; valueToTry++) {
        unsolvedValueCell.cell().setValue(valueToTry);

        // check here for task cell(s) for that value cell;
        TaskCellWithCoordinates taskCellAbove = findTaskCellAbove(unsolvedValueCell);
        if (ValueCell.doValueCellsFailToMeetExpectation(
                taskCellAbove.cell().getSumOfValuesBelow()
                , getValueCellsBelowThan(taskCellAbove.rowIndex(), taskCellAbove.columnIndex())
                , Rules::hasProperValue
                , false)) continue;
        TaskCellWithCoordinates taskCellOnTheLeft = findTaskCellOnTheLeft(unsolvedValueCell);
        if (ValueCell.doValueCellsFailToMeetExpectation(
                taskCellOnTheLeft.cell().getSumOfValuesOnTheRight()
                , getValueCellsOnTheRightFrom(taskCellOnTheLeft.rowIndex(), taskCellOnTheLeft.columnIndex())
                , Rules::hasProperValue
                , false )) continue;

        Puzzle result = solve();
        if (null != result) return result;
      }

      Rules.clearValue(unsolvedValueCell.cell());
      return null;
    }

    @Override
    protected boolean isCurrentAssumptionWrong() {
      return false;
    }

    private ValueCellWithCoordinates findUnsolvedValueCellWithCoordinatesOrNull() {
      return _valueCells.stream()
              .filter(vc -> !Rules.hasProperValue(vc.cell()))
              .findAny()
              .orElse(null);
    }

    // TODO deal with comments
    @Override
    protected ValueCell findUnsolvedValueCellOfNull() {
      return null;
    }

    private TaskCellWithCoordinates findTaskCellAbove(CellWithCoordinates<?> cell) {
      TaskCellWithCoordinates taskCell = null;
      for (int rowIndex = cell.rowIndex() - 1; rowIndex >= 0 && null == taskCell; rowIndex--) {
        final int atRow = rowIndex;
        taskCell = Optional.of(puzzle.getCellAt(atRow, cell.columnIndex()))
                .map(c -> c.as(TaskCell.class))
                .map(tc -> new TaskCellWithCoordinates(tc, atRow, cell.columnIndex()))
                .orElse(null);
      }
      return taskCell;
    }

    private TaskCellWithCoordinates findTaskCellOnTheLeft(CellWithCoordinates<?> cell) {
      TaskCellWithCoordinates taskCell = null;
      for (int columnIndex = cell.columnIndex()-1; columnIndex >=0 && null == taskCell ; columnIndex--) {
        final int atCol = columnIndex;
      taskCell = Optional.of(puzzle.getCellAt(cell.rowIndex(), atCol))
              .map(c -> c.as(TaskCell.class))
              .map(tc -> new TaskCellWithCoordinates(tc, cell.rowIndex(), atCol))
              .orElse(null);
      }
      return taskCell;
    }

    static class TaskCellWithCoordinates extends CellWithCoordinates<TaskCell> {
      public TaskCellWithCoordinates(TaskCell cell, int rowIndex, int columnIndex) { super(cell, rowIndex, columnIndex); }
    }

  }

}
