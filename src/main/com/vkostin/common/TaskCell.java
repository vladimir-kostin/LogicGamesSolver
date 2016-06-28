package com.vkostin.common;

import java.util.Objects;

public abstract class TaskCell<T extends ITask> implements Cell {
  private final T _task;

  public TaskCell(T task) {
    this._task = task;
  }

  public T task() { return _task; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaskCell<?> taskCell = (TaskCell<?>) o;
    return Objects.equals(_task, taskCell._task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_task);
  }

  @Override
  public String toString() {
    return "TaskCell{" +
            "_task=" + _task +
            '}';
  }

}
