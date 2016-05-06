package com.vkostin;

public class CellCoordinates {
  private final int _row;
  private final int _col;

  public CellCoordinates(int row, int col) {
    this._row = row;
    this._col = col;
  }

  public int row() { return _row; }
  public int col() { return _col; }

}
