package com.vkostin.lineSweeper;

import com.vkostin.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solver implements com.vkostin.Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) { return new SolverInstance(puzzle).solve(); }

  public static class SolverInstance extends AbstractSolverInstanse {

    private final List<PathWay> _assumptions;
    private final FluentPuzzle _fluentPuzzle;

    public SolverInstance(Puzzle puzzle) {
      super(puzzle);
      _assumptions = Arrays.asList(PathWay.values());
      _fluentPuzzle = new FluentPuzzle(puzzle);
    }

    @Override
    protected Puzzle finalizeSolvedPuzzle(final Puzzle puzzle) {
      _withCoords.cells().stream()
              .map(CellWithCoordinates::cell)
              .map(c -> c.as(PathCell.class))
              .filter(Objects::nonNull)
              .filter(pc -> PathWay.isEmpty(pc.getPath()))
              .forEach(this::clearValue);

      return super.finalizeSolvedPuzzle(puzzle);
    }

    @Override
    protected CellWithCoordinates<Cell> findUnsolvedCell() {
      return _withCoords.cells().stream()
              .filter(this::isPathCellUnsolved)
              .findFirst()
              .orElse(null);
    }

    private boolean isPathCellUnsolved(CellWithCoordinates<Cell> cell) {
      PathCell pathCell = Optional.of(cell)
              .map(CellWithCoordinates::cell)
              .map(c -> c.as(PathCell.class))
              .orElse(null);

      return null != pathCell && null == pathCell.getPath();
    }

    @Override
    protected List<?> assumptionsToBeMade() {
      return _assumptions;
    }

    @Override
    protected boolean isAnyRuleBroken(CellWithCoordinates<Cell> cell) {

      PathCell cellBeingChecked = cell.cell().as(PathCell.class);

      if (cellBeingChecked.getPath().connectsToCellAbove()) {
        if(0 == cell.rowIndex()) return true;
        PathCell above = _puzzle.getCellAt(cell.rowIndex()-1, cell.columnIndex()).as(PathCell.class);
        if(null == above) return true;
        if(null != above.getPath() && !above.getPath().connectsToCellBelow()) return true;
      } else if (0 < cell.rowIndex()){
        PathCell above = _puzzle.getCellAt(cell.rowIndex()-1, cell.columnIndex()).as(PathCell.class);
        if (null != above && null != above.getPath() && above.getPath().connectsToCellBelow()) return true;
      }

      if (cellBeingChecked.getPath().connectsToCellBelow()) {
        if (_puzzle.getRowCount() == cell.rowIndex()+1) return true;
        PathCell below = _puzzle.getCellAt(cell.rowIndex()+1, cell.columnIndex()).as(PathCell.class);
        if(null == below) return true;
        if(null != below.getPath() && !below.getPath().connectsToCellAbove()) return true;
      } else if (_puzzle.getRowCount() > cell.rowIndex()+1) {
        PathCell below = _puzzle.getCellAt(cell.rowIndex()+1, cell.columnIndex()).as(PathCell.class);
        if (null != below && null != below.getPath() && below.getPath().connectsToCellAbove()) return true;
      }

      if (cellBeingChecked.getPath().connectsToCellOnTheLeft()) {
        if (0 == cell.columnIndex()) return true;
        PathCell onTheLeft = _puzzle.getCellAt(cell.rowIndex(), cell.columnIndex()-1).as(PathCell.class);
        if (null == onTheLeft) return true;
        if (null != onTheLeft.getPath() && !onTheLeft.getPath().connectsToCellOnTheRight()) return true;
      } else if (0 < cell.columnIndex()) {
        PathCell onTheLeft = _puzzle.getCellAt(cell.rowIndex(), cell.columnIndex()-1).as(PathCell.class);
        if (null != onTheLeft && null != onTheLeft.getPath() && onTheLeft.getPath().connectsToCellOnTheRight()) return true;
      }

      if (cellBeingChecked.getPath().connectsToCellOnTheRight()) {
        if (_puzzle.getRowLength() == cell.columnIndex()+1) return true;
        PathCell onTheRight = _puzzle.getCellAt(cell.rowIndex(), cell.columnIndex()+1).as(PathCell.class);
        if (null == onTheRight) return true;
        if (null != onTheRight.getPath() && !onTheRight.getPath().connectsToCellOnTheLeft()) return true;
      } else if (_puzzle.getRowLength() > cell.columnIndex()+1) {
        PathCell onTheRight = _puzzle.getCellAt(cell.rowIndex(), cell.columnIndex()+1).as(PathCell.class);
        if (null != onTheRight && null != onTheRight.getPath() && onTheRight.getPath().connectsToCellOnTheLeft()) return true;
      }

      if (cellsAround(cell)
              .filter(c -> null != c.cell().as(TaskCell.class))
              .anyMatch(this::isRuleBrokenForTaskCellWithPathsAround)) return true;

      // TODO : there must be only one single loop
      List<CellWithCoordinates> path = buildPathStartingFrom(cell);

      if(isThereNonEmptyPathCellsNotIncludedInPathLoop(path)) return true;

      return false;
    }

    private Stream<CellWithCoordinates> cellsAround(CellWithCoordinates<Cell> cell) {
      return Stream.of(
              cellWithCoordinatesOrNullAt(cell.rowIndex() - 1, cell.columnIndex() - 1),
              cellWithCoordinatesOrNullAt(cell.rowIndex() - 1, cell.columnIndex()),
              cellWithCoordinatesOrNullAt(cell.rowIndex() - 1, cell.columnIndex() + 1),

              cellWithCoordinatesOrNullAt(cell.rowIndex(), cell.columnIndex() - 1),
              cellWithCoordinatesOrNullAt(cell.rowIndex(), cell.columnIndex() + 1),

              cellWithCoordinatesOrNullAt(cell.rowIndex() + 1, cell.columnIndex() - 1),
              cellWithCoordinatesOrNullAt(cell.rowIndex() + 1, cell.columnIndex()),
              cellWithCoordinatesOrNullAt(cell.rowIndex() + 1, cell.columnIndex() + 1)
      ).filter(Objects::nonNull);
    }

    private boolean isRuleBrokenForTaskCellWithPathsAround(CellWithCoordinates<Cell> taskCell) {
      final int expectedAmountOfPathCellsWithPaths = taskCell.cell().as(TaskCell.class).numberOfPathCellsAround();

      List<PathCell> pathCellsAround = cellsAround(taskCell)
              .map(CellWithCoordinates::cell)
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

    private List<CellWithCoordinates> buildPathStartingFrom(CellWithCoordinates<Cell> cell) {
      if(!Optional.ofNullable(cell)
              .map(CellWithCoordinates::cell)
              .map(c -> c.as(PathCell.class))
              .map(PathCell::getPath)
              .map(PathWay::isNotEmpty)
              .orElse(false)) return null;
      //TODO add path building logic here
      return null;
    }

    private boolean isThereNonEmptyPathCellsNotIncludedInPathLoop(List<CellWithCoordinates> pathLoop) {
      if (null == pathLoop) return false;

      return _withCoords.cells().stream()
              .filter(c -> null != c.cell().as(PathCell.class))
              .filter(c -> c.cell().as(PathCell.class).hasNonEmptyPath())
              .anyMatch(c -> !pathLoop.contains(c));
    }

    @Override
    protected void setValue(Object value, CellWithCoordinates<Cell> cell) {
      cell.cell().as(PathCell.class).setPath((PathWay) value);
    }

    @Override
    protected void clearValue(CellWithCoordinates<Cell> cell) {
      clearValue(cell.cell().as(PathCell.class));
    }

    private void clearValue(PathCell pathCell) { pathCell.clearPath(); }

  }

}
