package com.vkostin.tennerGrid;

import com.vkostin.PerformanceTest;
import com.vkostin.PuzzleAsArray;
import com.vkostin.TestUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(PerformanceTest.class)
public class SolversPerformanceTest {

  private Parser parser = new Parser(PuzzleAsArray::new);

  private SimpleSolver simpleSolver = new SimpleSolver();
  private BetterSolver betterSolver = new BetterSolver();

  @Test
  public void testSimpleSolver() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, simpleSolver);
  }

  @Test
  public void testBetterSolver() {
    TestUtils.assertSolving(TestData.ALL_TEST_DATA, parser, betterSolver);
  }

}
