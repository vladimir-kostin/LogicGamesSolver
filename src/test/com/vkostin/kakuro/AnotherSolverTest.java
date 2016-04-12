package com.vkostin.kakuro;

import com.vkostin.PuzzleAsArray;
import com.vkostin.Solver;
import com.vkostin.TestUtils;
import org.junit.Test;

public class AnotherSolverTest {

  private Parser parser = new Parser(PuzzleAsArray::new);
  private Solver solver = new AnotherSolver();

  @Test
  public void shouldSolveQuicklySolvablePuzzles() {
    TestUtils.assertSolving(TestData.QUICKLY_SOLVABLE_TEST_DATA, parser, solver);
  }

}
