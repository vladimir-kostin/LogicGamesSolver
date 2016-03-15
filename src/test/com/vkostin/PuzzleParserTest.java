package com.vkostin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PuzzleParserTest {

  PuzzleParser puzzleParser;

  @Before
  public void setUp() throws Exception {
    puzzleParser = new PuzzleParser();
  }

  @Test
  public void shouldParse3x4Puzzle() {
    Puzzle result = puzzleParser.parse(TestData.PUZZLE_3_X_4);

    Puzzle expected = PuzzleBuilder.aPuzzle()
            .addRow(tC(),    tC(3,0), tC(4,0), tC(6,0))
            .addRow(tC(0,6), vC(),    vC(),    vC())
            .addRow(tC(0,7), vC(),    vC(),    vC())
            .build();

    Assert.assertEquals(expected, result);
  }

  private TaskCell tC() { return tC(0,0); }
  private TaskCell tC(int sumBelow, int sumRight) {
    return new TaskCell(sumBelow, sumRight);
  }

  private ValueCell vC() {
    return new ValueCell();
  }
  private ValueCell vC(int value) { return new ValueCell(value); }

  @Test
  public void shouldParse5x5Puzzle() {
    Puzzle result = puzzleParser.parse(TestData.PUZZLE_5_X_5);

    Puzzle expected = PuzzleBuilder.aPuzzle()
            .addRow(tC(),    tC(4,0),   tC(9,0),  tC(),     tC())
            .addRow(tC(0,4), vC(),      vC(),     tC(21,0), tC())
            .addRow(tC(0,7), vC(),      vC(),     vC(),     tC(16,0))
            .addRow(tC(),    tC(0, 23), vC(),     vC(),     vC())
            .addRow(tC(),    tC(),      tC(0,16), vC(),     vC())
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParse3x4Solution() throws Exception {
    Puzzle result = puzzleParser.parse(TestData.SOLUTION_3_X_4);

    Puzzle expected  = PuzzleBuilder.aPuzzle()
            .addRow(tC(),    tC(3,0), tC(4,0), tC(6,0))
            .addRow(tC(0,6), vC(1),   vC(3),   vC(2))
            .addRow(tC(0,7), vC(2),   vC(1),   vC(4))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParse5x5Solution() throws Exception {
    Puzzle result = puzzleParser.parse(TestData.SOLUTION_5_X_5);

    Puzzle expected = PuzzleBuilder.aPuzzle()
            .addRow(tC(),    tC(4,0),   tC(9,0),  tC(),     tC())
            .addRow(tC(0,4), vC(3),     vC(1),     tC(21,0), tC())
            .addRow(tC(0,7), vC(1),     vC(2),     vC(4),     tC(16,0))
            .addRow(tC(),    tC(0, 23), vC(6),     vC(8),     vC(9))
            .addRow(tC(),    tC(),      tC(0,16), vC(9),     vC(7))
            .build();

    Assert.assertEquals(expected, result);
  }

  // TODO test parsing with values in value cells
}
