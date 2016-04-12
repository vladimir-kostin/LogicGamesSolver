package com.vkostin.tennerGrid;

import com.vkostin.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Category(PerformanceTest.class)
public class SolversPerformanceTest {

  private Function<Cell[][], Puzzle> createPuzzleAsArray = cells -> new PuzzleAsArray(cells);
  private Function<Cell[][], Puzzle> createPuzzleAsList = cells -> new PuzzleAsList(cells);

  private SimpleSolver simpleSolver = new SimpleSolver();
  private BetterSolver betterSolver = new BetterSolver();

//  private List<TestParameter> testData = Arrays.asList(
//          new TestParameter( 0, TestData.PUZZLE___, TestData.SOLUTION___),
//          new TestParameter( 1, TestData.PUZZLE__1, TestData.SOLUTION__1),
//          new TestParameter( 8, TestData.PUZZLE__8, TestData.SOLUTION__8),
//          new TestParameter(10, TestData.PUZZLE_10, TestData.SOLUTION_10),
//          new TestParameter(14, TestData.PUZZLE_14, TestData.SOLUTION_14),
//          new TestParameter(19, TestData.PUZZLE_19, TestData.SOLUTION_19),
//          new TestParameter(20, TestData.PUZZLE_20, TestData.SOLUTION_20)
//  );

  private void testSolverWithModel(Solver solver, Function<Cell[][], Puzzle> createPuzzle) {
    Parser parser = new Parser(createPuzzle);
    TestData.ALL_TEST_DATA.stream()
//    testData.stream()
            .forEach(e ->
                    Assert.assertEquals(
                            "solving: " + e.number(),
                            parser.parse(e.solution()),
                            solver.solve(parser.parse(e.puzzle()))
                    )
            );
  }

  @Test
  public void testSimpleSolver_wModelAsArray() { testSolverWithModel(simpleSolver, createPuzzleAsArray); }

  @Test
  public void testSimpleSolver_wModelAsList() { testSolverWithModel(simpleSolver, createPuzzleAsList); }

  @Test
  public void testBetterSolver_wModelAsArray() { testSolverWithModel(betterSolver, createPuzzleAsArray); }

  @Test
  public void testBetterSolver_wModelAsList() { testSolverWithModel(betterSolver, createPuzzleAsList); }

}
