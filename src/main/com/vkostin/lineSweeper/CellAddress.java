package com.vkostin.lineSweeper;

import java.util.Objects;

public class CellAddress {
  private final int _row;
  private final int _col;

  public CellAddress(int row, int col) {
    this._row = row;
    this._col = col;
  }

  public int row() { return _row; }
  public int col() { return _col; }

  public CellAddress neighbourAddressTo(Direction direction) {
    int row = _row;
    int col = _col;
    switch (direction) {
      case DOWN: row++; break;
      case LEFT: col--; break;
      case RIGHT: col++; break;
      case UP: row--; break;
    }
    return new CellAddress(row, col);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof CellAddress)) return false;
    CellAddress that = (CellAddress) o;
    return _row == that._row &&
            _col == that._col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(_row, _col);
  }

}
