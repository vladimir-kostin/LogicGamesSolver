package com.vkostin.lineSweeper;

import com.vkostin.AbstractParser;
import com.vkostin.Cell;
import com.vkostin.Puzzle;

import java.util.function.Function;

public class Parser extends AbstractParser {
  public Parser(Function<Cell[][], Puzzle> createPuzzle) { super(createPuzzle); }

  @Override
  public Puzzle parse(String multiLineText) {
    return null;
  }

}
