package com.vkostin.common;

import java.util.*;

public class FluentPuzzle {
  private final Puzzle _puzzle;
  private List<FluentCell> _cells;

  public FluentPuzzle(Puzzle puzzle) {
    this._puzzle = puzzle;
    this._cells = Collections.unmodifiableList(buildFluentCells(puzzle));
  }

  private List<FluentCell> buildFluentCells(Puzzle puzzle) {
    List<FluentCell> fluentCells = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
      for (int colIndex = 0; colIndex < puzzle.getRowLength(); colIndex++) {
        fluentCells.add(new FluentCell(this, puzzle.getCellAt(rowIndex, colIndex), new CellAddress(rowIndex, colIndex)));
      }
    }
    return fluentCells;
  }

  public FluentCell at(CellAddress address) {
    if (0 > address.row() || _puzzle.getRowCount() <= address.row()
            || 0 > address.col() || _puzzle.getRowLength() <= address.col()) return null;

    int index = address.row() * _puzzle.getRowLength() + address.col();
    return _cells.get(index);
  }

  public Collection<FluentCell> cells() { return _cells; }
}
