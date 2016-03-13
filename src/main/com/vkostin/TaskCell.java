package com.vkostin;

public class TaskCell implements Cell {
  final private int sumOfValuesBelow;
  final private int sumOfValueOnTheRight;

  public TaskCell(int sumOfValuesBelow, int sumOfValueOnTheRight) {
    this.sumOfValuesBelow = sumOfValuesBelow;
    this.sumOfValueOnTheRight = sumOfValueOnTheRight;
  }

  public int getSumOfValuesBelow() {
    return sumOfValuesBelow;
  }

  public int getSumOfValueOnTheRight() {
    return sumOfValueOnTheRight;
  }

}
