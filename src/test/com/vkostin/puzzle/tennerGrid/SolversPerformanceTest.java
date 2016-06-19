package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.PerformanceTest;
import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(PerformanceTest.class)
public class SolversPerformanceTest {

  private Parser parser = new Parser(PuzzleAsArray::new);
  private FluentSolver fluentSolver = new FluentSolver();

  @Test(timeout = 60000)
  public void testFluentSolver() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, fluentSolver);
  }

}
