package com.vkostin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class PuzzleSolverTest {

  private Function<Cell[][], IPuzzle> createPuzzle = cells -> new Puzzle3(cells);
  private PuzzleParser parser = new PuzzleParser(createPuzzle);
  private PuzzleSolver puzzleSolver;

  @Before
  public void setUp() throws Exception {
    puzzleSolver = new PuzzleSolver();
  }

  @Test
  public void shouldSolve3x4() {
    IPuzzle input = parser.parse(TestData.PUZZLE_3_X_4);

    IPuzzle result = puzzleSolver.solve(input);
    Assert.assertNull(result.findFirstUnsolvedValueCellOrNull());
    Assert.assertFalse(result.hasErrors());

    IPuzzle expected = parser.parse(TestData.SOLUTION_3_X_4);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldSolve5x5() {
    IPuzzle input = parser.parse(TestData.PUZZLE_5_X_5);

    IPuzzle result = puzzleSolver.solve(input);
    Assert.assertNull(result.findFirstUnsolvedValueCellOrNull());
    Assert.assertFalse(result.hasErrors());

    IPuzzle expected = parser.parse(TestData.SOLUTION_5_X_5);
    Assert.assertEquals(expected, result);
  }

}
