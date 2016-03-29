package com.vkostin.kakuro;

import com.vkostin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class PuzzleParser {

  private final static String NEW_LINE_REGEX = "[\\r\\n]+";
  private final static String WHITE_SPACE_REGEX = "[\\s]+";
  private final static String BACKSLASH_REGEX = "\\\\";

  private final static String UNSOLVED_VALUE = "_";

  private final Function<Cell[][], IPuzzle> createPuzzle;
  public PuzzleParser(Function<Cell[][], IPuzzle> createPuzzle) {
    this.createPuzzle = createPuzzle;
  }

  public IPuzzle parse(String task) {

    String[] lines = task.split(NEW_LINE_REGEX);

    PuzzleBuilder builder = PuzzleBuilder.aPuzzle(createPuzzle);
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
          Optional.of(sums[0])
                  .map(this::parseValueCell)
                  .ifPresent(cellsInLine::add);
        } break;

        case 2: {
          cellsInLine.add(mapStringsToTaskCell(sums[0], sums[1]));
        } break;

        default: {
          throw new RuntimeException();
        }
      }
    }

    return cellsInLine;
  }

  private ValueCell parseValueCell(String input) {
    if (UNSOLVED_VALUE.equals(input)) { return new ValueCell(Rules.UNSOLVED_VALUE); }

    return Optional.of(input)
            .map(Integer::parseInt)
            .map(ValueCell::new)
            .orElseThrow(RuntimeException::new);
  }

  private TaskCell mapStringsToTaskCell(String below, String right) {
    int sumBelow = below.isEmpty() ? 0 : Integer.parseInt(below);
    int sumRight = right.isEmpty() ? 0 : Integer.parseInt(right);
    return new TaskCell(sumBelow, sumRight);
  }

}
