package com.vkostin.lineSweeper;

import com.vkostin.AbstractParser;
import com.vkostin.Cell;
import com.vkostin.Puzzle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Parser2 extends AbstractParser {

  private final static Map<String, Way> mappings = new HashMap() {{
    put("T", Way.DOWN_LEFT);
    put("F", Way.DOWN_RIGHT);
    put("-", Way.LEFT_RIGHT);
    put("|", Way.UP_DOWN);
    put("J", Way.UP_LEFT);
    put("L", Way.UP_RIGHT);
  }};

  public Parser2(Function<Cell[][], Puzzle> createPuzzle) { super(createPuzzle); }

}
