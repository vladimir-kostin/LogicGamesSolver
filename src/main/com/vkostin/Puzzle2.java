package com.vkostin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle2 implements IPuzzle {

  private List<List<Cell>> cells = new ArrayList<>();

  public Puzzle2(Cell[][] cells) {
    for (Cell[] row : cells) {
      this.cells.add(Arrays.asList(row));
    }
  }

  @Override
  public ValueCell findFirstUnsolvedValueCellOrNull() {
    return cells.stream()
            .flatMap(List::stream)
            .filter(cell -> cell instanceof ValueCell)
            .map(cell -> (ValueCell)cell)
            .filter(ValueCell::isUnsolved)
            .findFirst()
            .orElse(null);
  }

  @Override
  public boolean hasErrors() {
    return cells.stream()
            .flatMap(List::stream)
            .filter(cell -> cell instanceof TaskCell)
            .map(cell -> (TaskCell) cell)
            .anyMatch(this::doesTaskCellHaveAnyErrors);
  }

  private boolean doesTaskCellHaveAnyErrors(TaskCell taskCell) {
    return false;
  }

}
