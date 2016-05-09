package com.vkostin.lineSweeper;

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
}
