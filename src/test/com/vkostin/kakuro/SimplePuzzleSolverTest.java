package com.vkostin.kakuro;

import com.vkostin.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class SimplePuzzleSolverTest {

  private Function<Cell[][], Puzzle> createPuzzle = cells -> new PuzzleAsArray(cells);
  private Parser parser = new Parser(createPuzzle);
  private SimplePuzzleSolver simplePuzzleSolver;

  @Before
  public void setUp() {
    simplePuzzleSolver = new SimplePuzzleSolver();
  }

  private void shouldSolve(String puzzle, String solution) {
    Puzzle input = parser.parse(puzzle);

    Puzzle result = simplePuzzleSolver.solve(input);

    Puzzle expected = parser.parse(solution);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldSolve____() { shouldSolve(TestData.PUZZLE____, TestData.SOLUTION____); }

  @Test
  public void shouldSolve___1() { shouldSolve(TestData.PUZZLE___1, TestData.SOLUTION___1); }

  @Test
  public void shouldSolve___8() { shouldSolve(TestData.PUZZLE___8, TestData.SOLUTION___8); }

  @Test
  public void shouldSolve__20() { shouldSolve(TestData.PUZZLE__20, TestData.SOLUTION__20); }

}
