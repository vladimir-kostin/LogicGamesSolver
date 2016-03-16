package com.vkostin;

import java.util.Objects;

public class TaskCell implements Cell {
  final private int sumOfValuesBelow;
  final private int sumOfValueOnTheRight;

  public TaskCell(int sumOfValuesBelow, int sumOfValueOnTheRight) {
    this.sumOfValuesBelow = sumOfValuesBelow;
    this.sumOfValueOnTheRight = sumOfValueOnTheRight;
  }

  public int getSumOfValuesBelow() { return sumOfValuesBelow; }
  public int getSumOfValueOnTheRight() {
    return sumOfValueOnTheRight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskCell taskCell = (TaskCell) o;
    return sumOfValuesBelow == taskCell.sumOfValuesBelow &&
            sumOfValueOnTheRight == taskCell.sumOfValueOnTheRight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(sumOfValuesBelow, sumOfValueOnTheRight);
  }

  @Override
  public String toString() {
    return "TaskCell{" +
            "sumOfValuesBelow=" + sumOfValuesBelow +
            ", sumOfValueOnTheRight=" + sumOfValueOnTheRight +
            '}';
  }

}
