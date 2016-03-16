package com.vkostin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PuzzleSolverTest {

  private PuzzleSolver puzzleSolver;

  private PuzzleParser parser = new PuzzleParser();

  @Before
  public void setUp() throws Exception {
    puzzleSolver = new PuzzleSolver();
  }

  @Test
  public void shouldSolve3x4() {
    Puzzle input = parser.parse(TestData.PUZZLE_3_X_4);

    Puzzle result = puzzleSolver.solve(input);
    Assert.assertFalse(result.hasAnyUnsolvedValueCells());
    Assert.assertFalse(result.hasErrors());

    Puzzle expected = parser.parse(TestData.SOLUTION_3_X_4);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldSolve5x5() {
    Puzzle input = parser.parse(TestData.PUZZLE_5_X_5);

    Puzzle result = puzzleSolver.solve(input);
    Assert.assertFalse(result.hasAnyUnsolvedValueCells());
    Assert.assertFalse(result.hasErrors());

    Puzzle expected = parser.parse(TestData.SOLUTION_5_X_5);
    Assert.assertEquals(expected, result);
  }

}
