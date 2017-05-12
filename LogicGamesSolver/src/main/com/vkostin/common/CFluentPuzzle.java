package com.vkostin.common;

import java.util.*;

public class CFluentPuzzle {
  private final Puzzle _puzzle;
  private final List<CFluentCell<? extends Cell>> _cells;

  public CFluentPuzzle(final Puzzle puzzle) {
    this._puzzle = puzzle;
    this._cells = Collections.unmodifiableList(buildFluentValueCells(puzzle));
  }

  private List<CFluentCell<? extends Cell>> buildFluentValueCells(final Puzzle puzzle) {
    List<CFluentCell<? extends Cell>> cells = new ArrayList<>();
    for (int row = 0; row < _puzzle.rowCount(); row++) {
      for (int col = 0; col < _puzzle.rowLength(); col++) {
        cells.add(new CFluentCell<>(this, puzzle.at(row, col), new CellAddress(row, col)));
      }
    }
    return cells;
  }

  public Optional<CFluentCell<? extends Cell>> at(CellAddress address) {
    if (0 > address.row() || _puzzle.rowCount() <= address.row()
            || 0 > address.col() || _puzzle.rowLength() <= address.col()) return Optional.empty();

    int index = address.row() * _puzzle.rowLength() + address.col();
    return Optional.of(_cells.get(index));
  }

  public Collection<CFluentCell<? extends Cell>> cells() { return _cells; }

}
