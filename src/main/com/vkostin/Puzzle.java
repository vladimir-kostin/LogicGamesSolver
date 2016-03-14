package com.vkostin;

import java.util.Arrays;
import java.util.List;

public class Puzzle {
  final private Cell[][] cells;

  public Puzzle(Cell[][] cells) {
    this.cells = cells;
  }

  public Cell[][] getCells() {
    return cells;
  }

  public List<Cell> getRow(int index) {
    return Arrays.asList(cells[index]);
  }

}
