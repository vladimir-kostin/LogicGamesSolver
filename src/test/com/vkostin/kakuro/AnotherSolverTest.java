package com.vkostin.kakuro;

import com.vkostin.PuzzleAsArray;
import com.vkostin.SolverTest;
import org.junit.Before;
import org.junit.Test;

public class AnotherSolverTest extends SolverTest {
  @Before
  public void setUp() throws Exception {
    parser = new com.vkostin.kakuro.Parser(PuzzleAsArray::new);
    solver = new AnotherSolver();
  }

  @Test
  public void shouldSolve____() { shouldSolve(TestData.PUZZLE____, TestData.SOLUTION____); }

  @Test
  public void shouldSolve___1() { shouldSolve(TestData.PUZZLE___1, TestData.SOLUTION___1); }

  @Test
  public void shouldSolve___8() { shouldSolve(TestData.PUZZLE___8, TestData.SOLUTION___8); }

  @Test
  public void shouldSolve__20() { shouldSolve(TestData.PUZZLE__20, TestData.SOLUTION__20); }

}