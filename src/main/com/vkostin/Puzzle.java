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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Puzzle)) return false;
    Puzzle puzzle = (Puzzle) o;
    return Arrays.deepEquals(cells, puzzle.cells);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(cells);
  }

  @Override
  public String toString() {
    return "Puzzle{" +
            "cells=" + Arrays.deepToString(cells) +
            '}';
  }
}
