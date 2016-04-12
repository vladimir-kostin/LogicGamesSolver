package com.vkostin;

import org.junit.Assert;

import java.util.List;

public class TestUtils {

  private static void assertSolving(TestParameter testParameter, Parser parser, Solver solver) {
    Puzzle input = parser.parse(testParameter.puzzle());

    Puzzle result = solver.solve(input);

    Puzzle expected = parser.parse(testParameter.solution());
    Assert.assertEquals("solving: " + testParameter.number()
            , expected
            , result);
  }

  public static void assertSolving(List<TestParameter> testParameters, Parser parser, Solver solver) {
    testParameters.stream()
            .forEach(testParameter -> assertSolving(testParameter, parser, solver));
  }

}
