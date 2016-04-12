package com.vkostin.kakuro;

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
  private AnotherSolver anotherSolver = new AnotherSolver();

  private List<PerformanceTestParameter> testData = Arrays.asList(
          new PerformanceTestParameter(  0, TestData.PUZZLE____, TestData.SOLUTION____),
          new PerformanceTestParameter(  1, TestData.PUZZLE___1, TestData.SOLUTION___1),
          new PerformanceTestParameter(  8, TestData.PUZZLE___8, TestData.SOLUTION___8),
          new PerformanceTestParameter( 20, TestData.PUZZLE__20, TestData.SOLUTION__20),
          new PerformanceTestParameter( 42, TestData.PUZZLE__42, TestData.SOLUTION__42),
          new PerformanceTestParameter( 64, TestData.PUZZLE__64, TestData.SOLUTION__64),
          new PerformanceTestParameter( 86, TestData.PUZZLE__86, TestData.SOLUTION__86),
          new PerformanceTestParameter(108, TestData.PUZZLE_108, TestData.SOLUTION_108));

  private void testSolverWithModel(Solver solver, Function<Cell[][], Puzzle> createPuzzle) {
    Parser parser = new Parser(createPuzzle);
    testData.stream()
            .forEach(e -> Assert.assertEquals("solving: " + e.number()
                    , parser.parse(e.solution())
                    , solver.solve(parser.parse(e.puzzle()))
            ));
  }

  @Test
  public void testSimpleSolver_wModelAsArray() { testSolverWithModel(simpleSolver, createPuzzleAsArray); }

  @Test
  public void testSimpleSolver_wModelAsList() { testSolverWithModel(simpleSolver, createPuzzleAsList); }

  @Test
  public void testBetterSolver_wModelAsArray() { testSolverWithModel(betterSolver, createPuzzleAsArray); }

  @Test
  public void testBetterSolver_wModelAsList() { testSolverWithModel(betterSolver, createPuzzleAsList); }

  @Test
  public void testAnotherSolver_wModelAsArray() { testSolverWithModel(anotherSolver, createPuzzleAsArray); }

  @Test
  public void testAnotherSolver_wModelAsList() { testSolverWithModel(anotherSolver, createPuzzleAsList); }

}
