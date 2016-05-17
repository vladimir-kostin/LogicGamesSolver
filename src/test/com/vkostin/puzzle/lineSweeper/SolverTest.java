package com.vkostin.puzzle.lineSweeper;

import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestUtils;
import org.junit.Test;

public class SolverTest {

  private Parser parser = new Parser(PuzzleAsArray::new);
  private Solver solver = new Solver();

  @Test
  public void shouldSolvePuzzles() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, solver);
  }

}
