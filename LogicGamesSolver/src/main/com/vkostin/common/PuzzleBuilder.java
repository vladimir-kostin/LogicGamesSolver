package com.vkostin.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PuzzleBuilder {
  public static PuzzleBuilder aPuzzle(Function<Cell[][], Puzzle> createPuzzle) {
    return new PuzzleBuilder(createPuzzle);
  }

  private List<List<Cell>> rows = new ArrayList<>();

  private final Function<Cell[][], Puzzle> createPuzzle;
  private PuzzleBuilder(Function<Cell[][], Puzzle> createPuzzle){
    this.createPuzzle = createPuzzle;
  }

  public Puzzle build() {
    final int maxWidth = maxWidth();
    Cell[][] cells = new Cell[rows.size()][maxWidth];

    for (int j = 0; j < rows.size(); j++) {
      for (int k = 0; k < maxWidth; k++) {
        cells[j][k] = rows.get(j).get(k);
      }
    }

    return createPuzzle.apply(cells);
  }

  public void clear() { rows.clear(); }

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
