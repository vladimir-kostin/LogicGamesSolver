package com.vkostin.common;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class ValueCell implements Cell {

  public static boolean doValueCellsFailToMeetExpectation(
          int expectedValueSum
          , Collection<ValueCell> valueCells
          , Predicate<ValueCell> hasProperValue
          , boolean duplicateProperValuesAreAllowed) {

    int actualSumOfProperValues = valueCells.stream()
            .filter(hasProperValue)
            .mapToInt(ValueCell::getValue)
            .sum();

    if (expectedValueSum < actualSumOfProperValues) return true;

    boolean containsUnresolvedCells = valueCells.stream()
            .anyMatch(hasProperValue.negate());

    if (!containsUnresolvedCells && expectedValueSum != actualSumOfProperValues) return true;
    if(duplicateProperValuesAreAllowed) return false;

    long properValueCount = valueCells.stream()
            .filter(hasProperValue)
            .mapToInt(ValueCell::getValue)
            .count();

    long distinctProperValueCount = valueCells.stream()
            .filter(hasProperValue)
            .mapToInt(ValueCell::getValue)
            .distinct()
            .count();

    return properValueCount != distinctProperValueCount;
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
