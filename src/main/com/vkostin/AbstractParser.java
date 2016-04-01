package com.vkostin;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractParser implements Parser {

  private final static String NEW_LINE_REGEX = "[\\r\\n]+";
  private final static String WHITE_SPACE_REGEX = "[\\s]+";

  protected final static String UNSOLVED_VALUE = "_";

  protected final PuzzleBuilder builder;

  public AbstractParser(Function<Cell[][], IPuzzle> createPuzzle) {
    builder = PuzzleBuilder.aPuzzle(createPuzzle);
  }

  protected List<String> nonEmptyTextLines(String multiLineText) {
    return Arrays.asList(multiLineText.split(NEW_LINE_REGEX)).stream()
            .map(String::trim)
            .filter(line -> 0 < line.length())
            .collect(Collectors.toList());
  }

  protected List<String> cellStringRepresentations(String oneLineOfText) {
    return Arrays.asList(oneLineOfText.split(WHITE_SPACE_REGEX));
  }

}
