package com.vkostin.kakuro;

import com.vkostin.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class PuzzleParserTest {

  Function<Cell[][], IPuzzle> createPuzzle = cells -> new PuzzleAsList(cells);
  PuzzleParser puzzleParser;

  @Before
  public void setUp() {
    puzzleParser = new PuzzleParser(createPuzzle);
  }

  private TaskCell tC() { return tC(0, 0); }
  private TaskCell tC(int sumBelow, int sumRight) {
    return new TaskCell(sumBelow, sumRight);
  }

  private ValueCell vC() {
    return new ValueCell(Rules.UNSOLVED_VALUE);
  }
  private ValueCell vC(int value) { return new ValueCell(value); }

  private PuzzleBuilder aPuzzle() { return PuzzleBuilder.aPuzzle(createPuzzle); }

  @Test
  public void shouldParsePuzzle____() {
    IPuzzle result = puzzleParser.parse(TestData.PUZZLE____);

    IPuzzle expected = aPuzzle()
            .addRow(tC(),    tC(3,0), tC(4,0), tC(6,0))
            .addRow(tC(0,6), vC(),    vC(),    vC())
            .addRow(tC(0,7), vC(),    vC(),    vC())
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParsePuzzle___1() {
    IPuzzle result = puzzleParser.parse(TestData.PUZZLE___1);

    IPuzzle expected = aPuzzle()
            .addRow(tC(),    tC(4,0),   tC(9,0),  tC(),     tC())
            .addRow(tC(0,4), vC(),      vC(),     tC(21,0), tC())
            .addRow(tC(0,7), vC(),      vC(),     vC(),     tC(16,0))
            .addRow(tC(),    tC(0, 23), vC(),     vC(),     vC())
            .addRow(tC(),    tC(),      tC(0,16), vC(),     vC())
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParseSolution____() throws Exception {
    IPuzzle result = puzzleParser.parse(TestData.SOLUTION____);

    IPuzzle expected = aPuzzle()
            .addRow(tC(),    tC(3,0), tC(4,0), tC(6,0))
            .addRow(tC(0,6), vC(1),   vC(3),   vC(2))
            .addRow(tC(0,7), vC(2),   vC(1),   vC(4))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParseSolution___1() throws Exception {
    IPuzzle result = puzzleParser.parse(TestData.SOLUTION___1);

    IPuzzle expected = aPuzzle()
            .addRow(tC(),    tC(4,0),   tC(9,0),  tC(),     tC())
            .addRow(tC(0,4), vC(3),     vC(1),     tC(21,0), tC())
            .addRow(tC(0,7), vC(1),     vC(2),     vC(4),     tC(16,0))
            .addRow(tC(),    tC(0, 23), vC(6),     vC(8),     vC(9))
            .addRow(tC(),    tC(),      tC(0,16), vC(9),     vC(7))
            .build();

    Assert.assertEquals(expected, result);
  }

}
