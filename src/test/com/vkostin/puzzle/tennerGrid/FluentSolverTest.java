package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.Puzzle;
import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestParameter;
import org.junit.Test;

import static org.junit.Assert.*;

public class FluentSolverTest {

  private Parser parser = new Parser(PuzzleAsArray::new);
  private FluentSolver fluentSolver = new FluentSolver();

  private void testSingleCase(final TestParameter parameter) {
    Puzzle input = parser.parse(parameter.puzzle());
    Puzzle expected = parser.parse(parameter.solution());

    Puzzle result = fluentSolver.solve(input);

    assertEquals("Error solving: kakuro " + parameter.number(), expected, result);
  }

  @Test(timeout = 60000)
  public void solveAll() {

    for (TestParameter p : TestData.ALL_TEST_DATA) {
      System.out.println("Solving: " + p.number() + "...");
      long before = System.currentTimeMillis();
      testSingleCase(p);
      System.out.println("Solved: " + p.number() + " in " + (System.currentTimeMillis() - before) + " ms.");
    }

  }

}
