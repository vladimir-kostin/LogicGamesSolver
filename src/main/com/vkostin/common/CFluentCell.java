package com.vkostin.common;

import java.util.Optional;

public class CFluentCell<C extends Cell> {
  private final CFluentPuzzle _puzzle;
  private final C _cell;
  private final CellAddress _address;

  public CFluentCell(final CFluentPuzzle puzzle, final C cell, final CellAddress address) {
    this._puzzle = puzzle;
    this._cell = cell;
    this._address = address;
  }

  public C cell() {
    return _cell;
  }

  public CellAddress address() {
    return _address;
  }

  public Optional<CFluentCell<? extends Cell>> neighbourTo(Direction direction) {
    return _puzzle.at(_address.neighbourAddressTo(direction));
  }

}
