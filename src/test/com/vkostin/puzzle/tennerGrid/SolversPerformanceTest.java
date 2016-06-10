package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.PerformanceTest;
import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(PerformanceTest.class)
public class SolversPerformanceTest {

  private Parser parser = new Parser(PuzzleAsArray::new);

  private AnotherSolver anotherSolver = new AnotherSolver();

  @Test
  public void testAnotherSolver() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, anotherSolver);
  }

}
