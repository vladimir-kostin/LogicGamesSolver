package com.vkostin.common;

import java.util.Collection;
import java.util.function.Predicate;

public class IntegerValueCell extends AValueCell<IntegerValue> {

  public IntegerValueCell() {
  }

  public IntegerValueCell(int value) {
    setValue(new IntegerValue(value));
  }

  public static boolean doCellsFailToMeetExpectation(
          int expectedValueSum
          , Collection<IntegerValueCell> cells
          , Predicate<IntegerValueCell> hasProperValue
          , boolean duplicateProperValuesAreAllowed) {

    int actualSumOfProperValues = cells.stream()
            .filter(hasProperValue)
            .map(IntegerValueCell::value)
            .mapToInt(IntegerValue::value)
            .sum();

    if (expectedValueSum < actualSumOfProperValues) return true;

    boolean containsUnresolvedCells = cells.stream()
            .anyMatch(hasProperValue.negate());

    if (!containsUnresolvedCells && expectedValueSum != actualSumOfProperValues) return true;
    if (duplicateProperValuesAreAllowed) return false;

    long properValueCount = cells.stream()
            .filter(hasProperValue)
            .count();

    long distinctProperValueCount = cells.stream()
            .filter(hasProperValue)
            .map(IntegerValueCell::value)
            .mapToInt(IntegerValue::value)
            .distinct()
            .count();

    return properValueCount != distinctProperValueCount;
  }

}
