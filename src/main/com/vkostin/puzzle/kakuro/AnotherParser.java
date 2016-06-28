package com.vkostin.puzzle.kakuro;

import com.vkostin.common.AbstractParser;
import com.vkostin.common.Cell;
import com.vkostin.common.IntegerValueCell;
import com.vkostin.common.Puzzle;
import com.vkostin.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnotherParser extends AbstractParser {

  private final static String BACKSLASH_REGEX = "\\\\";

  public AnotherParser(Function<Cell[][], Puzzle> createPuzzle) {
    super(createPuzzle);
  }

  @Override
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

  private IntegerValueCell parseValueCell(String input) {
    if (UNSOLVED_VALUE.equals(input)) return new IntegerValueCell();

    return Optional.of(input)
            .map(Integer::parseInt)
            .map(IntegerValueCell::new)
            .orElseThrow(RuntimeException::new);
  }

  private CRules.KakuroTaskCell mapStringsToTaskCell(String below, String right) {

    return new CRules.KakuroTaskCell(
            Optional.of(below)
                    .filter(StringUtils::isNotBlank)
                    .map(Integer::parseInt)
                    .orElse(0)
            ,
            Optional.of(right)
                    .filter(StringUtils::isNotBlank)
                    .map(Integer::parseInt)
                    .orElse(0)
    );

  }

}
