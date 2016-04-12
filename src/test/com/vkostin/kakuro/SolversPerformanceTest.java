package com.vkostin.kakuro;

import com.vkostin.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.function.Function;

@Category(PerformanceTest.class)
public class SolversPerformanceTest {

  private Function<Cell[][], Puzzle> createPuzzleAsArray = cells -> new PuzzleAsArray(cells);
  private Function<Cell[][], Puzzle> createPuzzleAsList = cells -> new PuzzleAsList(cells);

  private SimpleSolver simpleSolver = new SimpleSolver();
  private BetterSolver betterSolver = new BetterSolver();
  private AnotherSolver anotherSolver = new AnotherSolver();

  private void testSolverWithModel(Solver solver, Function<Cell[][], Puzzle> createPuzzle) {
    Parser parser = new Parser(createPuzzle);
    TestData.ALL_TEST_DATA.stream()
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
