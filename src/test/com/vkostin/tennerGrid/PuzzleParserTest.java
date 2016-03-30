package com.vkostin.tennerGrid;

import com.vkostin.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class PuzzleParserTest {

  Function<Cell[][], IPuzzle> createPuzzle = cells -> new Puzzle(cells);
  PuzzleParser puzzleParser;

  @Before
  public void setUp() throws Exception {
    puzzleParser = new PuzzleParser(createPuzzle);
  }

  private ValueCell vC() {
    return new ValueCell(Rules.UNSOLVED_VALUE);
  }
  private ValueCell vC(int value) { return new ValueCell(value); }

  private TaskCell tC(int sumAbove) { return new TaskCell(sumAbove); }

  @Test
  public void shouldParsePuzzle1() {
    IPuzzle result = puzzleParser.parse(TestData.PUZZLE_1);

    IPuzzle expected = PuzzleBuilder.aPuzzle(createPuzzle)
            .addRow(vC(0), vC(1), vC(2), vC(3), vC(4), vC(5), vC(6), vC(7), vC(8), vC())
            .addRow(tC(0), tC(1), tC(2), tC(3), tC(4), tC(5), tC(6), tC(7), tC(8), tC(9))
            .build();

    Assert.assertEquals(expected, result);
  }

}
