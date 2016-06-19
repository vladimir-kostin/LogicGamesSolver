package com.vkostin.puzzle.lineSweeper;

import com.vkostin.common.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluentSolver implements com.vkostin.common.Solver {
  @Override
  public Puzzle solve(final Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  private static class SolverInstance extends AbstractFluentCheckSingleChangeSolverInstance {

    private final List<PathWay> _assumptions;
    private final LoopedPathBuilder _loopBuilder;

    public SolverInstance(final Puzzle puzzle) {
      super(puzzle);
      _assumptions = Arrays.asList(PathWay.values());
      _loopBuilder = new LoopedPathBuilder();
    }

    @Override
    protected Puzzle finalizeSolvedPuzzle(final Puzzle puzzle) {
      _fluentPuzzle.cells().stream()
              .map(FluentCell::cell)
              .map(c -> c.as(PathCell.class))
              .filter(Objects::nonNull)
              .filter(pc -> PathWay.isEmpty(pc.getPath()))
              .forEach(this::clearValue);

      return super.finalizeSolvedPuzzle(puzzle);
    }

    @Override
    protected boolean isValueCellUnsolved(final FluentCell cell) {
      PathCell pathCell = Optional.of(cell.cell())
              .map(c -> c.as(PathCell.class))
              .orElse(null);

      return null != pathCell && null == pathCell.getPath();
    }

    @Override
    protected Iterable<? extends Object> assumptionsToBeMade() {
      return _assumptions;
    }

    @Override
    protected boolean isAnyRuleBroken(final FluentCell cell) {
      if (Arrays.stream(Direction.values())
              .anyMatch(d -> cellViolatesRulesInDirection(cell, d))) return true;

      if (cellsAround(cell)
              .filter(c -> c.cell() instanceof TaskCell)
              .anyMatch(this::isRuleBrokenForTaskCellWithPathsAround)) return true;

      List<FluentCell> path = _loopBuilder.buildLoopedPathStartingFrom(cell);
      if(isThereNonEmptyPathCellsNotIncludedInPathLoop(path)) return true;

      return false;
    }

    private boolean cellViolatesRulesInDirection(final FluentCell cell, Direction direction) {
      PathCell cellBeingChecked = cell.cell().as(PathCell.class);
      if (cellBeingChecked.getPath().connectsToCellIn(direction)) {
        Optional<PathCell> pathCell = Optional.ofNullable(cell.neighbourTo(direction))
                .map(FluentCell::cell)
                .map(c -> c.as(PathCell.class));
        if (!pathCell.isPresent()) return true;
        if (pathCell
                .map(PathCell::getPath)
                .filter(pw -> !pw.connectsToCellIn(direction.opposite()))
                .isPresent()) return true;
      } else {
        if (Optional.ofNullable(cell.neighbourTo(direction))
                .map(FluentCell::cell)
                .map(c -> c.as(PathCell.class))
                .map(PathCell::getPath)
                .filter(pw -> pw.connectsToCellIn(direction.opposite()))
                .isPresent()) return true;
      }
      return false;
    }

    private Stream<FluentCell> cellsAround(FluentCell cell) {
      FluentCell onTop = cell.neighbourTo(Direction.UP);
      FluentCell onBottom = cell.neighbourTo(Direction.DOWN);
      return Stream.of(
              Optional.ofNullable(onTop).map(c -> c.neighbourTo(Direction.LEFT)).orElse(null),
              onTop,
              Optional.ofNullable(onTop).map(c -> c.neighbourTo(Direction.RIGHT)).orElse(null),

              cell.neighbourTo(Direction.LEFT),
              cell.neighbourTo(Direction.RIGHT),

              Optional.ofNullable(onBottom).map(c -> c.neighbourTo(Direction.LEFT)).orElse(null),
              onBottom,
              Optional.ofNullable(onBottom).map(c -> c.neighbourTo(Direction.RIGHT)).orElse(null)
      ).filter(Objects::nonNull);
    }

    private boolean isRuleBrokenForTaskCellWithPathsAround(FluentCell taskCell) {
      final int expectedAmountOfPathCellsWithPaths = taskCell.cell().as(TaskCell.class).numberOfPathCellsAround();

      List<PathCell> pathCellsAround = cellsAround(taskCell)
              .map(FluentCell::cell)
              .map(c -> c.as(PathCell.class))
              .filter(Objects::nonNull)
              .collect(Collectors.toList());

      long amountOfPathCellWithNonNullPaths = pathCellsAround.stream()
              .map(PathCell::getPath)
              .filter(Objects::nonNull)
              .count();


      long amountOfPathCellsWithNonEmptyPaths = pathCellsAround.stream()
              .map(PathCell::getPath)
              .filter(Objects::nonNull)
              .filter(PathWay::isNotEmpty)
              .count();

      if (expectedAmountOfPathCellsWithPaths < amountOfPathCellsWithNonEmptyPaths) return true;
      return expectedAmountOfPathCellsWithPaths != amountOfPathCellsWithNonEmptyPaths
              && pathCellsAround.size() == amountOfPathCellWithNonNullPaths;
    }

    private boolean isThereNonEmptyPathCellsNotIncludedInPathLoop(List<FluentCell> pathLoop) {
      if (null == pathLoop) return false;

      return _fluentPuzzle.cells().stream()
              .filter(c -> Optional.of(c)
                      .map(FluentCell::cell)
                      .map(cell -> cell.as(PathCell.class))
                      .map(PathCell::hasNonEmptyPath)
                      .orElse(false))
              .anyMatch(c -> !pathLoop.contains(c));
    }

    @Override
    protected void setValue(final Object value, final FluentCell cell) {
      cell.cell().as(PathCell.class).setPath((PathWay) value);
    }

    @Override
    protected void clearValue(final FluentCell cell) {
      clearValue((PathCell) cell.cell());
    }

    private void clearValue(PathCell pathCell) { pathCell.clearPath(); }
  }

}
