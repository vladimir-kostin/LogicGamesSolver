package com.vkostin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleBuilder {
  public static PuzzleBuilder aPuzzle() {
    return new PuzzleBuilder();
  }

  private List<List<Cell>> rows = new ArrayList<>();

  private PuzzleBuilder(){}

  public Puzzle build() {
    final int maxWidth = maxWidth();
    Cell[][] cells = new Cell[rows.size()][maxWidth];

    for (int j = 0; j < rows.size(); j++) {
      for (int k = 0; k < maxWidth; k++) {
        cells[j][k] = rows.get(j).get(k);
      }
    }

    return new Puzzle(cells);
  }

  private int maxWidth() {
    return rows.stream()
            .mapToInt(List::size)
            .max()
            .orElse(0);
  }

  public PuzzleBuilder addRow(Cell... row) {
    return addRow(Arrays.asList(row));
  }
  public PuzzleBuilder addRow(List<Cell> row) {
    rows.add(row);
    return this;
  }
}
