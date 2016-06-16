package com.vkostin.puzzle.kakuro;

import com.vkostin.common.Puzzle;
import com.vkostin.common.PuzzleAsArray;
import com.vkostin.common.TestParameter;
import org.junit.Test;

import static org.junit.Assert.*;

public class FluentSolverTest {

  private Parser parser = new Parser(PuzzleAsArray::new);

  private FluentSolver fluentSolver = new FluentSolver();

  private void testSingleCase(TestParameter parameter) {
    Puzzle input = parser.parse(parameter.puzzle());
    Puzzle expected = parser.parse(parameter.solution());

    Puzzle result = fluentSolver.solve(input);

    assertEquals("Error solving: kakuro " + parameter.number(), expected, result);
  }

  private TestParameter getParamenterByNumber(int number) {
    return TestData.ALL_TEST_DATA.stream()
            .filter(tp -> tp.number() == number)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("cannot find: kakuro " + number));
  }

  @Test
  public void solve0() {
    testSingleCase(getParamenterByNumber(0));
  }

  @Test
  public void solve1() {
    testSingleCase(getParamenterByNumber(1));
  }

  @Test
  public void solve8() {
    testSingleCase(getParamenterByNumber(8));
  }

  @Test
  public void solve20() {
    testSingleCase(getParamenterByNumber(20));
  }

  @Test(timeout = 60000)
  public void solveAll() {
    for (TestParameter p : TestData.ALL_TEST_DATA) {
      System.out.println("Solving: " + p.number() + "...");
      testSingleCase(p);
      System.out.println("Solved: " + p.number() + ".");
    }
  }

}
