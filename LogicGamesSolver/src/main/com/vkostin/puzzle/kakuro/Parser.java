package com.vkostin.puzzle.kakuro;

import com.vkostin.common.AbstractParser;
import com.vkostin.common.Cell;
import com.vkostin.common.Puzzle;
import com.vkostin.common.ValueCell;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser extends AbstractParser {

  private final static String BACKSLASH_REGEX = "\\\\";

  public Parser(Function<Cell[][], Puzzle> createPuzzle) { super(createPuzzle); }

  public Puzzle parse(String multiLineText) {
    builder.clear();

    nonEmptyTextLines(multiLineText).stream()
            .map(this::parseLine)
            .forEach(builder::addRow);

    return builder.build();
  }

  private List<Cell> parseLine(String oneLineText) {
    return cellStringRepresentations(oneLineText).stream()
            .map(this::parseCell)
            .collect(Collectors.toList());
  }

  private Cell parseCell(String cellRepresentation) {
    String[] splits = cellRepresentation.split(BACKSLASH_REGEX, 3);

    switch (splits.length) {
      case 1: return parseValueCell(cellRepresentation);
      case 2: return mapStringsToTaskCell(splits[0], splits[1]);

      default: throw new RuntimeException();
    }
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
