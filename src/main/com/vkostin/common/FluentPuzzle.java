package com.vkostin.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FluentPuzzle {
  private final Puzzle puzzle;
  private final Map<CellAddress, FluentCell> _cells;

  public FluentPuzzle(Puzzle puzzle) {
    this.puzzle = puzzle;

    _cells = new HashMap<>();
    for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
      for (int colIndex = 0; colIndex < puzzle.getRowLength(); colIndex++) {
        CellAddress address = new CellAddress(rowIndex, colIndex);
        _cells.put(address, new FluentCell(this, puzzle.getCellAt(rowIndex, colIndex), address));
      }
    }
  }

  public FluentCell at(CellAddress address) { return _cells.get(address); }

  public Collection<FluentCell> cells() { return _cells.values(); }
}
