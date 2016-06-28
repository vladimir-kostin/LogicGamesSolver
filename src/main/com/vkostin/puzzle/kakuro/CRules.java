package com.vkostin.puzzle.kakuro;

import com.vkostin.common.ITask;
import com.vkostin.common.IntegerValue;
import com.vkostin.common.TaskCell;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CRules {
  private static List<IntegerValue> _assumptions;
  static {
    _assumptions = IntStream.rangeClosed(Rules.MIN_ALLOWED_VALUE, Rules.MAX_ALLOWED_VALUE)
            .boxed()
            .map(IntegerValue::new)
            .collect(Collectors.toList());
  }

  public static List<IntegerValue> assumptions() {
    return _assumptions;
  }

  public static class KakuroTask implements ITask {
    private final int _sumOfValuesBelow;
    private final int _sumOfValuesOnTheRight;

    public KakuroTask(int sumOfValuesBelow, int sumOfValuesOnTheRight) {
      this._sumOfValuesBelow = sumOfValuesBelow;
      this._sumOfValuesOnTheRight = sumOfValuesOnTheRight;
    }

    public int sumOfValuesBelow() { return _sumOfValuesBelow; }
    public int sumOfValuesOnTheRight() { return _sumOfValuesOnTheRight; }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      KakuroTask that = (KakuroTask) o;
      return _sumOfValuesBelow == that._sumOfValuesBelow &&
              _sumOfValuesOnTheRight == that._sumOfValuesOnTheRight;
    }

    @Override
    public int hashCode() {
      return Objects.hash(_sumOfValuesBelow, _sumOfValuesOnTheRight);
    }

    @Override
    public String toString() {
      return "KakuroTask{" +
              "_sumOfValuesBelow=" + _sumOfValuesBelow +
              ", _sumOfValuesOnTheRight=" + _sumOfValuesOnTheRight +
              '}';
    }
  }

  public static class KakuroTaskCell extends TaskCell<KakuroTask> {

    public KakuroTaskCell(final KakuroTask task) {
      super(task);
    }

    public KakuroTaskCell(int sumOfValuesBelow, int sumOfValuesOnTheRight) {
      this(new KakuroTask(sumOfValuesBelow, sumOfValuesOnTheRight));
    }
  }

}
