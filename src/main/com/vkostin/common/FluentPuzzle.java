package com.vkostin.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FluentPuzzle {
  private final Puzzle _puzzle;
  private final Map<CellAddress, FluentCell> _cells;

  private final FluentCell[][] _fluentCells;

  public FluentPuzzle(Puzzle puzzle) {
    this._puzzle = puzzle;

    _cells = new HashMap<>();
    _fluentCells = new FluentCell[puzzle.getRowCount()][puzzle.getRowLength()];
    for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
      for (int colIndex = 0; colIndex < puzzle.getRowLength(); colIndex++) {
        CellAddress address = new CellAddress(rowIndex, colIndex);
        _fluentCells[rowIndex][colIndex] = new FluentCell(this, puzzle.getCellAt(rowIndex, colIndex), address);
        _cells.put(address, _fluentCells[rowIndex][colIndex]);

      }
    }


  }

  public FluentCell at(CellAddress address) {
    if (0 > address.row() || _puzzle.getRowCount() <= address.row()
            || 0 > address.col() || _puzzle.getRowLength() <= address.col()) return null;
    return _fluentCells[address.row()][address.col()];
//    return _cells.get(address);
  }

  public Collection<FluentCell> cells() { return _cells.values(); }
}
