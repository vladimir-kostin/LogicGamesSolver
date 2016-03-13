package com.vkostin;

public class Puzzle {
  final private Cell[][] cells;

  public Puzzle(Cell[][] cells) {
    this.cells = cells;
  }

  public Cell[][] getCells() {
    return cells;
  }
}
