package com.vkostin.puzzle.kakuro;

import com.vkostin.common.Puzzle;
import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestParameter;
import org.junit.Test;

import static org.junit.Assert.*;

public class FluentSolverTest {

  private Parser parser = new Parser(PuzzleAsArray::new);

  private FluentSolver fluentSolver = new FluentSolver();

  @Test
  public void solve0() {
    Puzzle input = parser.parse(TestData.PUZZLE____);
    Puzzle expected = parser.parse(TestData.SOLUTION____);

    Puzzle result = fluentSolver.solve(input);

    assertEquals(expected, result);
  }

  @Test
  public void solve1() {
    Puzzle input = parser.parse(TestData.PUZZLE___1);
    Puzzle expected = parser.parse(TestData.SOLUTION___1);

    Puzzle result = fluentSolver.solve(input);

    assertEquals(expected, result);
  }

  @Test
  public void solve8() {
    Puzzle input = parser.parse(TestData.PUZZLE___8);
    Puzzle expected = parser.parse(TestData.SOLUTION___8);

    Puzzle result = fluentSolver.solve(input);

    assertEquals(expected, result);
  }

  @Test
  public void solveAll() {
    for (TestParameter p : TestData.ALL_TEST_DATA) {
      System.out.println("Solving: " + p.number() + "...");
      fluentSolver.solve(parser.parse(p.puzzle()));
      System.out.println("Solved: " + p.number() + ".");
    }
  }

}
