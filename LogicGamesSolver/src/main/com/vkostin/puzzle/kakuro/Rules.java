package com.vkostin.puzzle.kakuro;

import com.vkostin.common.IntegerValue;
import com.vkostin.common.IntegerValueCell;
import com.vkostin.common.ValueCell;

import java.util.Optional;

class Rules {
  public static final int MIN_ALLOWED_VALUE = 1;
  public static final int MAX_ALLOWED_VALUE = 9;
  public static final int UNSOLVED_VALUE = MIN_ALLOWED_VALUE - 1;

  public static void clearValue(ValueCell valueCell) { valueCell.setValue(UNSOLVED_VALUE); }

  public static boolean hasProperValue(ValueCell valueCell) {
    return MIN_ALLOWED_VALUE <= valueCell.getValue()
            && MAX_ALLOWED_VALUE >= valueCell.getValue();
  }

  public static boolean hasUnsolvedValue(ValueCell valueCell) {
    return !hasProperValue(valueCell);
  }

  public static boolean hasProperValue(IntegerValueCell cell) {
    int value = Optional.of(cell)
            .map(IntegerValueCell::value)
            .map(IntegerValue::value)
            .orElse(UNSOLVED_VALUE);

    return MIN_ALLOWED_VALUE <= value
            && MAX_ALLOWED_VALUE >= value;
  }

}
