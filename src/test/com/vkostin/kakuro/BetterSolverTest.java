package com.vkostin.kakuro;

import com.vkostin.PuzzleAsArray;
import com.vkostin.SolverTest;
import com.vkostin.TestUtils;
import org.junit.Before;
import org.junit.Test;

public class BetterSolverTest extends SolverTest {

  @Before
  public void setUp() {
    parser = new Parser(PuzzleAsArray::new);
    solver = new BetterSolver();
  }

  @Test
  public void shouldSolveQuicklySolvablePuzzles() {
    TestUtils.assertSolving(TestData.QUICKLY_SOLVEABLE_TEST_DATA, parser, solver);
  }

}
