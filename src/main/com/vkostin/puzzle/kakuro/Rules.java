package com.vkostin.puzzle.kakuro;

import com.vkostin.common.ValueCell;

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

}
