package com.vkostin.puzzle.kakuro;

import com.vkostin.common.PerformanceTest;
import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(PerformanceTest.class)
public class SolversPerformanceTest {

  private Parser parser = new Parser(PuzzleAsArray::new);
  private FluentSolver fluentSolver = new FluentSolver();
  private AnotherSolver anotherSolver = new AnotherSolver();

  @Test(timeout = 60000)
  public void testFluentSolver() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, fluentSolver);
  }

  @Test(timeout = 60000)
  public void testAnotherSolver() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, anotherSolver);
  }

}
