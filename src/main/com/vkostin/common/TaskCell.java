package com.vkostin.common;

public abstract class TaskCell<T extends ITask> implements Cell {
  private final T _task;

  public TaskCell(T task) {
    this._task = task;
  }

  public T task() { return _task; }

}
