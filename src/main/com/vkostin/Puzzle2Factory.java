package com.vkostin;

public class Puzzle2Factory implements IPuzzleFactory {
  @Override
  public IPuzzle newPuzzle(Cell[][] cells) {
    return new Puzzle2(cells);
  }
}
