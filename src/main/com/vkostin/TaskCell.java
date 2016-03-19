package com.vkostin;

import java.util.Objects;

public class TaskCell implements Cell {
  final private int sumOfValuesBelow;
  final private int sumOfValuesOnTheRight;

  public TaskCell(int sumOfValuesBelow, int sumOfValuesOnTheRight) {
    this.sumOfValuesBelow = sumOfValuesBelow;
    this.sumOfValuesOnTheRight = sumOfValuesOnTheRight;
  }

  public int getSumOfValuesBelow() { return sumOfValuesBelow; }
  public int getSumOfValuesOnTheRight() {
    return sumOfValuesOnTheRight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TaskCell)) return false;
    TaskCell taskCell = (TaskCell) o;
    return sumOfValuesBelow == taskCell.sumOfValuesBelow &&
            sumOfValuesOnTheRight == taskCell.sumOfValuesOnTheRight;
  }

  @Override
  public int hashCode() { return Objects.hash(sumOfValuesBelow, sumOfValuesOnTheRight); }
}
