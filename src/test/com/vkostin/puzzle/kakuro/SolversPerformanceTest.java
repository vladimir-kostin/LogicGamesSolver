package com.vkostin.puzzle.kakuro;

import com.vkostin.common.FluentCell;
import com.vkostin.common.PerformanceTest;
import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(PerformanceTest.class)
public class SolversPerformanceTest {

  private Parser parser = new Parser(PuzzleAsArray::new);

  private Solver solver = new Solver();
  private FluentSolver fluentSolver = new FluentSolver();

  @Test
  public void testProperSolver() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, solver);
  }

  @Test
  public void testFluentSolver() throws Exception {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, fluentSolver);
  }

}
