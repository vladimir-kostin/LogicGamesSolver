package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.AbstractParser;
import com.vkostin.common.Cell;
import com.vkostin.common.Puzzle;
import com.vkostin.common.ValueCell;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser extends AbstractParser {

  public Parser(Function<Cell[][], Puzzle> createPuzzle) { super(createPuzzle); }

  @Override
  public Puzzle parse(String multiLineText) {
    builder.clear();
    List<String> lines = nonEmptyTextLines(multiLineText);
    int lastRowIndex = lines.size() - 1;

    for (int rowIndex = 0; rowIndex < lastRowIndex; rowIndex++) {
      builder.addRow(parseValueCellsLine(lines.get(rowIndex)));
    }
    if (lastRowIndex >= 0) {
      builder.addRow(parseTaskCellsLine(lines.get(lastRowIndex)));
    }

    return builder.build();
  }

  private List<Cell> parseValueCellsLine(String oneLineText) {
    return cellStringRepresentations(oneLineText).stream()
            .map(this::parseValueCell)
            .collect(Collectors.toList());
  }

  private ValueCell parseValueCell(String input) {
    if (UNSOLVED_VALUE.equals(input)) { return new ValueCell(Rules.UNSOLVED_VALUE); }

    return Optional.of(input)
            .map(Integer::parseInt)
            .map(ValueCell::new)
            .orElseThrow(RuntimeException::new);
  }

  private List<Cell> parseTaskCellsLine(String oneLineText) {
    return cellStringRepresentations(oneLineText).stream()
            .map(this::parseTaskCell)
            .collect(Collectors.toList());
  }

  private TaskCell parseTaskCell(String input) {
    return Optional.of(input)
            .map(Integer::parseInt)
            .map(TaskCell::new)
            .orElseThrow(RuntimeException::new);
  }

}
