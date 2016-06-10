package com.vkostin.puzzle.kakuro;

import com.vkostin.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProperSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  static class SolverInstance extends AbstractCheckSingleChangeSolverInstanse {
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
      TaskCellWithCoordinates taskCellAbove = findTaskCellAbove(cell);
      if (ValueCell.doValueCellsFailToMeetExpectation(
              taskCellAbove.cell().getSumOfValuesBelow()
              , getValueCellsBelowThan(taskCellAbove.rowIndex(), taskCellAbove.columnIndex())
              , Rules::hasProperValue
              , false)) return true;
      TaskCellWithCoordinates taskCellOnTheLeft = findTaskCellOnTheLeft(cell);
      if (ValueCell.doValueCellsFailToMeetExpectation(
              taskCellOnTheLeft.cell().getSumOfValuesOnTheRight()
              , getValueCellsOnTheRightFrom(taskCellOnTheLeft.rowIndex(), taskCellOnTheLeft.columnIndex())
              , Rules::hasProperValue
              , false )) return true;

      return false;
    }

    private TaskCellWithCoordinates findTaskCellAbove(CellWithCoordinates<Cell> cell) {
      TaskCellWithCoordinates taskCell = null;
      for (int rowIndex = cell.rowIndex() - 1; rowIndex >= 0 && null == taskCell; rowIndex--) {
        final int atRow = rowIndex;
        taskCell = Optional.of(_puzzle.getCellAt(atRow, cell.columnIndex()))
                .map(c -> c.as(TaskCell.class))
                .map(tc -> new TaskCellWithCoordinates(tc, atRow, cell.columnIndex()))
                .orElse(null);
      }
      return taskCell;
    }

    private TaskCellWithCoordinates findTaskCellOnTheLeft(CellWithCoordinates<Cell> cell) {
      TaskCellWithCoordinates taskCell = null;
      for (int columnIndex = cell.columnIndex()-1; columnIndex >=0 && null == taskCell ; columnIndex--) {
        final int atCol = columnIndex;
        taskCell = Optional.of(_puzzle.getCellAt(cell.rowIndex(), atCol))
                .map(c -> c.as(TaskCell.class))
                .map(tc -> new TaskCellWithCoordinates(tc, cell.rowIndex(), atCol))
                .orElse(null);
      }
      return taskCell;
    }

    protected List<ValueCell> getValueCellsBelowThan(int cellRowIndex, int cellColumnIndex) {
      List<ValueCell> valueCells = new ArrayList<>();
      for (int rowIndex = cellRowIndex+1; rowIndex < _puzzle.getRowCount(); rowIndex++) {
        Cell aCell = _puzzle.getCellAt(rowIndex, cellColumnIndex);
        if(aCell instanceof ValueCell) {
          valueCells.add((ValueCell) aCell);
        } else {
          break;
        }
      }
      return valueCells;
    }

    protected List<ValueCell> getValueCellsOnTheRightFrom(int cellRowIndex, int cellColumnIndex) {
      List<ValueCell> valueCells = new ArrayList<>();
      for (int columnIndex = cellColumnIndex+1; columnIndex < _puzzle.getRowLength(); columnIndex++) {
        Cell aCell = _puzzle.getCellAt(cellRowIndex, columnIndex);
        if(aCell instanceof ValueCell) {
          valueCells.add((ValueCell) aCell);
        } else {
          break;
        }
      }
      return valueCells;
    }

    @Override
    protected void setValue(Object value, CellWithCoordinates<Cell> cell) {
      ((ValueCell)cell.cell()).setValue((Integer) value);
    }

    @Override
    protected void clearValue(CellWithCoordinates<Cell> cell) {
      Rules.clearValue((ValueCell) cell.cell());
    }

    static class TaskCellWithCoordinates extends CellWithCoordinates<TaskCell> {
      public TaskCellWithCoordinates(TaskCell cell, int rowIndex, int columnIndex) { super(cell, rowIndex, columnIndex); }
    }

  }

}
