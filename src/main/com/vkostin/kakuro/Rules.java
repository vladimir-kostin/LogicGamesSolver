package com.vkostin.kakuro;

import com.vkostin.ValueCell;

public class Rules {
  public static final int MIN_ALLOWED_VALUE = 1;
  public static final int MAX_ALLOWED_VALUE = 9;
  public static final int UNSOLVED_VALUE = MIN_ALLOWED_VALUE - 1;

  public static boolean isUnsolved(ValueCell valueCell) {
    return !hasProperValue(valueCell);
  }

  public static boolean hasProperValue(ValueCell valueCell) {
    return MIN_ALLOWED_VALUE <= valueCell.getValue()
            && MAX_ALLOWED_VALUE >= valueCell.getValue();
  }

  public static void clearValue(ValueCell valueCell) {
    valueCell.setValue(MIN_ALLOWED_VALUE - 1);
  }

}
