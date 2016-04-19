package com.vkostin.lineSweeper;

import com.vkostin.AbstractParser;
import com.vkostin.Cell;
import com.vkostin.Puzzle;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser extends AbstractParser {
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

    return Optional.of(cellRepresentation)
            .map(Integer::parseInt)
            .map(TaskCell::new)
            .orElseThrow(RuntimeException::new);
  }

}
