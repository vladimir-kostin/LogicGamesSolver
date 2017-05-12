package com.vkostin.puzzle.lineSweeper;

import com.vkostin.common.AbstractParser;
import com.vkostin.common.Cell;
import com.vkostin.common.Puzzle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser extends AbstractParser {

  private final static Map<String, PathWay> mappings = new HashMap() {{
    put("T", PathWay.DOWN_LEFT);
    put("F", PathWay.DOWN_RIGHT);
    put("-", PathWay.LEFT_RIGHT);
    put("|", PathWay.UP_DOWN);
    put("J", PathWay.UP_LEFT);
    put("L", PathWay.UP_RIGHT);
  }};

  public Parser(Function<Cell[][], Puzzle> createPuzzle) { super(createPuzzle); }

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
    if (UNSOLVED_VALUE.equals(cellRepresentation)) { return new PathCell(); }

    return parsePathCell(cellRepresentation)
            .orElseGet(() -> parseTaskCell(cellRepresentation));
  }

  private Optional<Cell> parsePathCell(String cellRepresentation) {
    return Optional.of(cellRepresentation)
            .map(mappings::get)
            .map(PathCell::new);
  }

  private TaskCell parseTaskCell(String cellRepresentation) {
    return Optional.of(cellRepresentation)
            .map(Integer::parseInt)
            .map(TaskCell::new)
            .orElseThrow(RuntimeException::new);
  }

}
