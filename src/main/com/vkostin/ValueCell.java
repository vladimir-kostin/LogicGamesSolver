package com.vkostin;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValueCell implements Cell {

  public static boolean doValueCellsFailToMeetExpectation(
          int expectedValueSum
          , Collection<ValueCell> valueCells
          , Predicate<ValueCell> hasProperValue) {

    List<ValueCell> properValueCells = valueCells.stream()
            .filter(hasProperValue)
            .collect(Collectors.toList());

    int acturalSumOfProperValues = properValueCells.stream()
            .mapToInt(ValueCell::getValue)
            .sum();

    if (expectedValueSum < acturalSumOfProperValues) return true;

    if (valueCells.size() == properValueCells.size()
            && expectedValueSum != acturalSumOfProperValues) return true;

    long distinctProperValueCount = properValueCells.stream()
            .mapToInt(ValueCell::getValue)
            .distinct()
            .count();

    return properValueCells.size() != distinctProperValueCount;
  }

  private int value;

  public ValueCell(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
  public void setValue(int value) { this.value = value; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ValueCell)) return false;
    ValueCell valueCell = (ValueCell) o;
    return value == valueCell.value;
  }

  @Override
  public int hashCode() { return Objects.hash(value); }

}
