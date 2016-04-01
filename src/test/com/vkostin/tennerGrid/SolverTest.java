package com.vkostin.tennerGrid;

import com.vkostin.PuzzleAsArray;
import org.junit.Before;
import org.junit.Test;

public class SolverTest extends com.vkostin.SolverTest {

  @Before
  public void setUp() {
    parser = new Parser(PuzzleAsArray::new);
    solver = new Solver();
  }

  @Test
  public void shouldSolve___() { shouldSolve(TestData.PUZZLE___, TestData.SOLUTION___); }

  @Test
  public void shouldSolve__1() { shouldSolve(TestData.PUZZLE__1, TestData.SOLUTION__1); }

}
