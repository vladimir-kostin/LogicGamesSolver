package com.vkostin.lineSweeper;

import com.vkostin.AbstractSolverInstanse;
import com.vkostin.Cell;
import com.vkostin.CellWithCoordinates;
import com.vkostin.Puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Solver implements com.vkostin.Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) { return new SolverInstance(puzzle).solve(); }

  public static class SolverInstance extends AbstractSolverInstanse {

    private final List<PathWay> _assumptions;

    public SolverInstance(Puzzle puzzle) {
      super(puzzle);
      _assumptions = Arrays.asList(PathWay.values());
    }

    @Override
    protected CellWithCoordinates<Cell> findUnsolvedCell() {
      return _withCoords.cells().stream()
              .filter(this::isPathCellUnsolved)
              .findFirst()
              .orElse(null);
    }

    private boolean isPathCellUnsolved(CellWithCoordinates<Cell> cell) {
      return Optional.of(cell)
              .map(CellWithCoordinates::cell)
              .map(c -> c.as(PathCell.class))
              .map(PathCell::getPath)
              .map(Objects::isNull)
              .orElse(false);
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
      }

      if (cellBeingChecked.getPath().connectsToCellBelow()) {
        if (_puzzle.getRowCount() == cell.rowIndex()+1) return true;
        PathCell below = _puzzle.getCellAt(cell.rowIndex()+1, cell.columnIndex()).as(PathCell.class);
        if(null == below) return true;
        if(null != below.getPath() && !below.getPath().connectsToCellAbove()) return true;
      }

      if (cellBeingChecked.getPath().connectsToCellOnTheLeft()) {
        if (0 == cell.columnIndex()) return true;
        PathCell onTheLeft = _puzzle.getCellAt(cell.rowIndex(), cell.columnIndex()-1).as(PathCell.class);
        if (null == onTheLeft) return true;
        if (null != onTheLeft.getPath() && !onTheLeft.getPath().connectsToCellOnTheRight()) return true;
      }

      if (cellBeingChecked.getPath().connectsToCellOnTheRight()) {
        if (_puzzle.getRowLength() == cell.columnIndex()+1) return true;
        PathCell onTheRight = _puzzle.getCellAt(cell.rowIndex(), cell.columnIndex()+1).as(PathCell.class);
        if (null == onTheRight) return true;
        if (null != onTheRight.getPath() && !onTheRight.getPath().connectsToCellOnTheLeft()) return true;
      }

      return false;
    }

    @Override
    protected void setValue(Object value, CellWithCoordinates<Cell> cell) {
      cell.cell().as(PathCell.class).setPath((PathWay) value);

    }

    @Override
    protected void clearValue(CellWithCoordinates<Cell> cell) {
      cell.cell().as(PathCell.class).clearPath();
    }

  }

}