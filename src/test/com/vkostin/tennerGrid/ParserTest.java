package com.vkostin.tennerGrid;

import com.vkostin.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class ParserTest {

  private Function<Cell[][], Puzzle> createPuzzle = PuzzleAsList::new;
  private Parser parser;

  @Before
  public void setUp() {
    parser = new Parser(createPuzzle);
  }

  private ValueCell vC() {
    return new ValueCell(Rules.UNSOLVED_VALUE);
  }
  private ValueCell vC(int value) { return new ValueCell(value); }

  private TaskCell tC(int sumAbove) { return new TaskCell(sumAbove); }

  private PuzzleBuilder aPuzzle() { return PuzzleBuilder.aPuzzle(createPuzzle); }

  @Test
  public void shouldParsePuzzle___() {
    Puzzle result = parser.parse(TestData.PUZZLE___);

    Puzzle expected = aPuzzle()
            .addRow(vC(0), vC(1), vC(2), vC(3), vC(4), vC(5), vC(6), vC(7), vC(8), vC( ))
            .addRow(tC(0), tC(1), tC(2), tC(3), tC(4), tC(5), tC(6), tC(7), tC(8), tC(9))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParseSolution___() throws Exception {
    Puzzle result = parser.parse(TestData.SOLUTION___);

    Puzzle expected = aPuzzle()
            .addRow(vC(0), vC(1), vC(2), vC(3), vC(4), vC(5), vC(6), vC(7), vC(8), vC(9))
            .addRow(tC(0), tC(1), tC(2), tC(3), tC(4), tC(5), tC(6), tC(7), tC(8), tC(9))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParsePuzzle__1() {
    Puzzle result = parser.parse(TestData.PUZZLE__1);

    Puzzle expected = aPuzzle()
            .addRow(vC(  ), vC(  ), vC(  ), vC( 3), vC( 7), vC( 0), vC( 4), vC( 5), vC( 1), vC( 2))
            .addRow(vC( 3), vC( 1), vC(  ), vC( 2), vC(  ), vC( 8), vC( 9), vC( 7), vC( 4), vC( 5))
            .addRow(vC( 6), vC( 7), vC(  ), vC(  ), vC( 9), vC( 2), vC( 1), vC( 3), vC(  ), vC(  ))
            .addRow(tC(17), tC(17), tC(10), tC(10), tC(22), tC(10), tC(14), tC(15), tC(13), tC( 7))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParseSolution__1() {
    Puzzle result = parser.parse(TestData.SOLUTION__1);

    Puzzle expected = aPuzzle()
            .addRow(vC( 8), vC( 9), vC( 6), vC( 3), vC( 7), vC( 0), vC( 4), vC( 5), vC( 1), vC( 2))
            .addRow(vC( 3), vC( 1), vC( 0), vC( 2), vC( 6), vC( 8), vC( 9), vC( 7), vC( 4), vC( 5))
            .addRow(vC( 6), vC( 7), vC( 4), vC( 5), vC( 9), vC( 2), vC( 1), vC( 3), vC( 8), vC( 0))
            .addRow(tC(17), tC(17), tC(10), tC(10), tC(22), tC(10), tC(14), tC(15), tC(13), tC( 7))
            .build();

    Assert.assertEquals(expected, result);
  }

}
