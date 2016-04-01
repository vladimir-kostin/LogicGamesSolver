package com.vkostin.kakuro;

import com.vkostin.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class PuzzleSolversPerformanceTest {

  private Function<Cell[][], Puzzle> createPuzzle1 = cells -> new PuzzleAsArray(cells);
  private Function<Cell[][], Puzzle> createPuzzle3 = cells -> new PuzzleAsList(cells);

  private SimplePuzzleSolver simpleSolver = new SimplePuzzleSolver();
  private BetterPuzzleSolver betterSolver = new BetterPuzzleSolver();

  private void testSolverWithModel(Solver solver, Function<Cell[][], Puzzle> createPuzzle) {
    Parser parser = new Parser(createPuzzle);
    TestData.ALL_PUZZLES_WITH_SOLUTIONS.entrySet().stream()
            .forEach(e ->
                    Assert.assertEquals(
                            "solving: " + e.getKey(),
                            parser.parse(e.getValue()),
                            solver.solve(parser.parse(e.getKey()))
                    ));
  }

  @Test
  public void testSimpleSolver_wModel1() {
    testSolverWithModel(simpleSolver, createPuzzle1);
  }

  @Test
  public void testSimpleSolver_wModel3() {
    testSolverWithModel(simpleSolver, createPuzzle3);
  }

  @Test
  public void testBetterSolver_wModel1() {
    testSolverWithModel(betterSolver, createPuzzle1);
  }

  @Test
  public void testBetterSolver_wModel3() {
    testSolverWithModel(betterSolver, createPuzzle3);
  }

}
