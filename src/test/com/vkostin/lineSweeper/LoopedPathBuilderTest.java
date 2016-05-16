package com.vkostin.lineSweeper;

import com.vkostin.Puzzle;
import com.vkostin.PuzzleAsArray;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LoopedPathBuilderTest {

  private Parser parser = new Parser(PuzzleAsArray::new);

  @Test
  public void simpleLoop() {
    Puzzle puzzle = parser.parse(TestData.SOLUTION___);
    FluentPuzzle fluentPuzzle = new FluentPuzzle(puzzle);
    FluentCell startCell = fluentPuzzle.at(new CellAddress(0, 1));
    assertNotNull("start-cell is NOT expected to be NULL", startCell);

    LoopedPathBuilder sut = new LoopedPathBuilder();

    List<FluentCell> result = sut.buildLoopedPathStartingFrom(startCell);

    assertNotNull(result);
    assertEquals(16, result.size());
  }

}
