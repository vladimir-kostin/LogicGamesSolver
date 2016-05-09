package com.vkostin.lineSweeper;

import com.vkostin.Cell;

import java.util.Optional;

public class FluentCell {
  private final FluentPuzzle _puzzle;
  private final Cell _cell;
  private final CellAddress _address;

  public FluentCell(final FluentPuzzle puzzle, final Cell cell, final CellAddress address) {
    this._puzzle = puzzle;
    this._cell = cell;
    this._address = address;
  }

  public FluentPuzzle puzzle() {
    return _puzzle;
  }

  public Cell cell() {
    return _cell;
  }

  public CellAddress address() {
    return _address;
  }

  public FluentCell neighbourTo(Direction direction) {
    return _puzzle.at(_address.neighbourAddressTo(direction));
  }

}
