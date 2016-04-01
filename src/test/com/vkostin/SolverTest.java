package com.vkostin;

import org.junit.Assert;

public abstract class SolverTest {
  protected Parser parser;
  protected Solver solver;

  protected void shouldSolve(String puzzleAsMultilineText, String solutionAsMultilineText) {
    Puzzle input = parser.parse(puzzleAsMultilineText);

    Puzzle result = solver.solve(input);

    Puzzle expected = parser.parse(solutionAsMultilineText);
    Assert.assertEquals(expected, result);
  }

}
