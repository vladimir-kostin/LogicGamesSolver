package com.vkostin.lineSweeper;

import com.vkostin.Cell;

import java.util.Objects;

class TaskCell implements Cell {
  private final int _numberOfPathCellsAround;

  public TaskCell(int numberOfPathCellsAround) {
    this._numberOfPathCellsAround = numberOfPathCellsAround;
  }

  public int numberOfPathCellsAround() { return _numberOfPathCellsAround; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskCell taskCell = (TaskCell) o;
    return _numberOfPathCellsAround == taskCell._numberOfPathCellsAround;
  }

  @Override
  public int hashCode() {
    return Objects.hash(_numberOfPathCellsAround);
  }

  @Override
  public String toString() {
    return "" + _numberOfPathCellsAround;
  }

}
