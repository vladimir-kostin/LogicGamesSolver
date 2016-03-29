package com.vkostin.kakuro;

import com.vkostin.*;
import com.vkostin.kakuro.SimplePuzzleSolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class SimplePuzzleSolverTest {

  private Function<Cell[][], IPuzzle> createPuzzle = cells -> new Puzzle(cells);
  private PuzzleParser parser = new PuzzleParser(createPuzzle);
  private SimplePuzzleSolver simplePuzzleSolver;

  @Before
  public void setUp() {
    simplePuzzleSolver = new SimplePuzzleSolver();
  }

  private void shouldSolve(String puzzle, String solution) {
    IPuzzle input = parser.parse(puzzle);

    IPuzzle result = simplePuzzleSolver.solve(input);

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
  public void shouldSolve6x6() { shouldSolve(TestData.PUZZLE_6_X_6, TestData.SOLUTION_6_X_6); }

  @Test
  public void shouldSolve7x7() { shouldSolve(TestData.PUZZLE_7_X_7, TestData.SOLUTION_7_X_7); }

}
