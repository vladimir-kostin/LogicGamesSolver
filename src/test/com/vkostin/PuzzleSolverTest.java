package com.vkostin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class PuzzleSolverTest {

  private Function<Cell[][], IPuzzle> createPuzzle = cells -> new Puzzle5(cells);
  private PuzzleParser parser = new PuzzleParser(createPuzzle);
  private PuzzleSolver puzzleSolver;

  @Before
  public void setUp() {
    puzzleSolver = new PuzzleSolver();
  }

  private void shouldSolve(String puzlle, String solution) {
    IPuzzle input = parser.parse(puzlle);

    IPuzzle result = PuzzleSolver.s_solve(input);
            //puzzleSolver.solve(input);
//    Assert.assertNull(result.findFirstUnsolvedValueCellOrNull());
//    Assert.assertFalse(result.hasErrors());

    IPuzzle expected = parser.parse(solution);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldSolve3x4() {
    shouldSolve(TestData.PUZZLE_3_X_4, TestData.SOLUTION_3_X_4);
  }

  @Test
  public void shouldSolve5x5() {
    shouldSolve(TestData.PUZZLE_5_X_5, TestData.SOLUTION_5_X_5);
  }

  @Test
  public void shouldSolve6x6() throws Exception {
    shouldSolve(TestData.PUZZLE_6_X_6, TestData.SOLUTION_6_X_6);
  }

  @Test
  public void shouldSolve7x7() throws Exception {
    shouldSolve(TestData.PUZZLE_7_X_7, TestData.SOLUTION_7_X_7);
  }

  @Test
  public void shouldSolve8x8() throws Exception {
    shouldSolve(TestData.PUZZLE_8_X_8, TestData.SOLUTION_8_X_8);
  }

  @Test
  public void shouldSolve9x9() throws Exception {
    shouldSolve(TestData.PUZZLE_9_X_9, TestData.SOLUTION_9_X_9);
  }

  @Test
  public void shouldSolve10x10() throws Exception {
    shouldSolve(TestData.PUZZLE_10_X_10, TestData.SOLUTION_10_X_10);
  }

  @Test
  public void shouldSolve11x11() throws Exception {
    shouldSolve(TestData.PUZZLE_11_X_11, TestData.SOLUTION_11_X_11);
  }

}
