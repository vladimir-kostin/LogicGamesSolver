package com.vkostin;

public class CellWithCoordinates<C extends Cell> {

  private final C _cell;
  private final int _rowIndex;
  private final int _columnIndex;

  public CellWithCoordinates(C cell, int rowIndex, int columnIndex) {
    _cell = cell;
    _rowIndex = rowIndex;
    _columnIndex = columnIndex;
  }

  public C cell() { return _cell; }
  public int rowIndex() { return _rowIndex; }
  public int columnIndex() { return _columnIndex; }

  @Override
  public String toString() {
    return "" + _cell + "[" + _rowIndex + "," + _columnIndex + ']';
  }

}
