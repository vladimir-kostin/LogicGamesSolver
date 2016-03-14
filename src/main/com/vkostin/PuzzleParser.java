package com.vkostin;

import java.util.ArrayList;
import java.util.List;

public class PuzzleParser {

  private final static String NEW_LINE_REGEX = "[\\r\\n]+";
  private final static String WHITE_SPACE_REGEX = "\\s";
  private final static String BACKSLASH_REGEX = "\\\\";

  public Puzzle parse(String task) {

    String[] lines = task.split(NEW_LINE_REGEX);

    List<List<Cell>> matrix = new ArrayList<>();
    int maxWidth = -1;
    for (String line : lines) {
      List<Cell> cells = parseLine(line);
      maxWidth = Math.max(maxWidth, cells.size());
      matrix.add(cells);
    }

    Cell[][] cells = new Cell[matrix.size()][maxWidth];

    for (int j=0; j<matrix.size(); j++) {
      List<Cell> line = matrix.get(j);
      for (int k=0; k<line.size(); k++) {
        cells[j][k] = line.get(k);
      }
    }

    return new Puzzle(cells);
  }

  private List<Cell> parseLine(String line) {
    String[] cells = line.split(WHITE_SPACE_REGEX);

    List<Cell> cellsInLine = new ArrayList<>();
    for (String cell : cells) {

      if(cell.isEmpty()) { continue; }

      String [] sums = cell.split(BACKSLASH_REGEX, 3);

      switch(sums.length) {
        case 1:{
          cellsInLine.add(new ValueCell());
        }break;

        case 2:{
          int sumBelow = sums[0].isEmpty() ? 0 : Integer.parseInt(sums[0]);
          int sumRight = sums[1].isEmpty() ? 0 : Integer.parseInt(sums[1]);
          cellsInLine.add(new TaskCell(sumBelow, sumRight));
        }break;

        default:{
          throw new RuntimeException();
        }
      }
    }

    return cellsInLine;
  }

}
