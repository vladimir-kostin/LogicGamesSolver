package com.vkostin;

public class Puzzle1Factory implements IPuzzleFactory {
  @Override
  public IPuzzle newPuzzle(Cell[][] cells) {
    return new Puzzle(cells);
  }
}
