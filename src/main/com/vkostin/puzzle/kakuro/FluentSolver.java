package com.vkostin.puzzle.kakuro;

import com.vkostin.common.*;
import com.vkostin.common.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FluentSolver implements Solver {
  @Override
  public Puzzle solve(final Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  static class SolverInstance extends AbstractFluentCheckSingleChangeSolverInstance {

    final private List<Integer> _assumptions;
    final private List<FluentCell> _valueCells;

    public SolverInstance(final Puzzle puzzle) {
      super(puzzle);
      _assumptions = IntStream.rangeClosed(Rules.MIN_ALLOWED_VALUE, Rules.MAX_ALLOWED_VALUE)
              .boxed()
              .collect(Collectors.toList());
      _valueCells = _fluentPuzzle.cells().stream()
              .filter(cell -> cell.cell() instanceof ValueCell)
              .collect(Collectors.toList());
    }

    @Override
    protected FluentCell findUnsolvedCell() {
      return _valueCells.stream()
              .filter(this::isValueCellUnsolved)
              .findFirst()
              .orElse(null);
    }

    @Override
    protected boolean isValueCellUnsolved(final FluentCell cell) {
      return Optional.of(cell.cell())
              .map(this::asValueCell)
              .map(Rules::hasUnsolvedValue)
              .orElse(false);
    }

    private ValueCell asValueCell(Cell cell) {
      return cell instanceof ValueCell ? (ValueCell) cell : null;
    }

    @Override
    protected Iterable<? extends Object> assumptionsToBeMade() { return _assumptions; }

    @Override
    protected boolean isAnyRuleBroken(final FluentCell cell) {
      if (isAnyRuleBrokenToAbove(cell)) return true;
      if (isAnyRuleBrokenToTheLeft(cell)) return true;

      return false;
    }

    private boolean isAnyRuleBrokenToAbove(final FluentCell cell) {
      FluentCell taskCellAbove = cell;
      do {
        taskCellAbove = taskCellAbove.neighbourTo(Direction.UP);
      } while(taskCellAbove.cell() instanceof ValueCell);

      List<ValueCell> valueCellsBelow = new ArrayList<>();
      for (FluentCell below = taskCellAbove.neighbourTo(Direction.DOWN);
           below != null && below.cell() instanceof ValueCell;
           below = below.neighbourTo(Direction.DOWN)) {
        valueCellsBelow.add((ValueCell) below.cell());
      }

      return ValueCell.doValueCellsFailToMeetExpectation(
              taskCellAbove.cell().as(TaskCell.class).getSumOfValuesBelow()
              , valueCellsBelow
              , Rules::hasProperValue
              , false);
    }

    private boolean isAnyRuleBrokenToTheLeft(final FluentCell cell) {
      FluentCell taskCellToTheLeft = cell;
      do {
        taskCellToTheLeft = taskCellToTheLeft.neighbourTo(Direction.LEFT);
      } while (taskCellToTheLeft.cell() instanceof ValueCell);

      List<ValueCell> valueCellToTheRight = new ArrayList<>();
      for (FluentCell toTheRight = taskCellToTheLeft.neighbourTo(Direction.RIGHT);
           toTheRight != null && toTheRight.cell() instanceof ValueCell;
           toTheRight = toTheRight.neighbourTo(Direction.RIGHT)) {
        valueCellToTheRight.add((ValueCell) toTheRight.cell());
      }

      return ValueCell.doValueCellsFailToMeetExpectation(
        taskCellToTheLeft.cell().as(TaskCell.class).getSumOfValuesOnTheRight()
              , valueCellToTheRight
              , Rules::hasProperValue
              , false);
    }

    @Override
    protected void setValue(final Object value, final FluentCell cell) {
      asValueCell(cell.cell()).setValue((Integer) value);
    }

    @Override
    protected void clearValue(final FluentCell cell) {
      Rules.clearValue(asValueCell(cell.cell()));
    }

  }

}
