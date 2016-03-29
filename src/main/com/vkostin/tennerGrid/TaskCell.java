package com.vkostin.tennerGrid;

import java.util.Objects;

public class TaskCell {
  final private int sumOfValuesAbove;

  public TaskCell(int sumOfValuesAbove) {
    this.sumOfValuesAbove = sumOfValuesAbove;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TaskCell)) return false;
    TaskCell taskCell = (TaskCell) o;
    return sumOfValuesAbove == taskCell.sumOfValuesAbove;
  }

  @Override
  public int hashCode() { return Objects.hash(sumOfValuesAbove); }

}
