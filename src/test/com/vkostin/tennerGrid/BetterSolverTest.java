package com.vkostin.tennerGrid;

import com.vkostin.PuzzleAsArray;
import com.vkostin.SolverTest;
import org.junit.Before;
import org.junit.Test;

public class BetterSolverTest extends SolverTest {

  @Before
  public void setUp() {
    parser = new Parser(PuzzleAsArray::new);
    solver = new BetterSolver();
  }

  @Test
  public void shouldSolve___() { shouldSolve(TestData.PUZZLE___, TestData.SOLUTION___); }

  @Test
  public void shouldSolve__1() { shouldSolve(TestData.PUZZLE__1, TestData.SOLUTION__1); }

  @Test
  public void shouldSolve__8() { shouldSolve(TestData.PUZZLE__8, TestData.SOLUTION__8); }

  @Test
  public void shouldSolve_10() { shouldSolve(TestData.PUZZLE_10, TestData.SOLUTION_10); }

  @Test
  public void shouldSolve_14() { shouldSolve(TestData.PUZZLE_14, TestData.SOLUTION_14); }

  @Test
  public void shouldSolve_19() { shouldSolve(TestData.PUZZLE_19, TestData.SOLUTION_19); }

  @Test
  public void shouldSolve_20() { shouldSolve(TestData.PUZZLE_20, TestData.SOLUTION_20); }

}
