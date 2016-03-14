package com.vkostin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleParser {

  private final static String NEW_LINE_REGEX = "[\\r\\n]+";
  private final static String WHITE_SPACE_REGEX = "[\\s]+";
  private final static String BACKSLASH_REGEX = "\\\\";

  public Puzzle parse(String task) {

    String[] lines = task.split(NEW_LINE_REGEX);

    PuzzleBuilder builder = PuzzleBuilder.aPuzzle();
    for (String line : lines) {
      builder.addRow(parseLine(line));
    }
    return builder.build();
  }

  private List<Cell> parseLine(String line) {
    String[] cells = line.split(WHITE_SPACE_REGEX);

    List<Cell> cellsInLine = new ArrayList<>();
    for (String cell : cells) {

      String[] sums = cell.split(BACKSLASH_REGEX, 3);

      switch (sums.length) {
        case 1: {
          cellsInLine.add(new ValueCell());
        } break;

        case 2: {
          int sumBelow = sums[0].isEmpty() ? 0 : Integer.parseInt(sums[0]);
          int sumRight = sums[1].isEmpty() ? 0 : Integer.parseInt(sums[1]);
          cellsInLine.add(new TaskCell(sumBelow, sumRight));
        } break;

        default: {
          throw new RuntimeException();
        }
      }
    }

    return cellsInLine;
  }

}
