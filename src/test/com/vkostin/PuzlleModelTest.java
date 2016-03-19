package com.vkostin;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class PuzlleModelTest {

  private Function<Cell[][], IPuzzle> createPuzzle1 = cells -> new Puzzle(cells);
  private Function<Cell[][], IPuzzle> createPuzzle3 = cells -> new Puzzle3(cells);
  private Function<Cell[][], IPuzzle> createPuzzle4 = cells -> new Puzzle4(cells);
  private Function<Cell[][], IPuzzle> createPuzzle5 = cells -> new Puzzle5(cells);

  private PuzzleSolver solver = new PuzzleSolver();

  private void testSolvingWithParser(Function<Cell[][], IPuzzle> createPuzzle) {
    PuzzleParser parser = new PuzzleParser(createPuzzle);
    TestData.ALL_PUZZLES_WITH_SOLUTIONS.entrySet().stream()
            .forEach(e ->
                    Assert.assertEquals(
                            "solving: " + e.getKey(),
                            parser.parse(e.getValue()),
                            PuzzleSolver.s_solve(parser.parse(e.getKey()))
//                            solver.solve(parser.parse(e.getKey()))
                    ));
  }

  @Test
  public void puzzle1() throws Exception {
    testSolvingWithParser(createPuzzle1);
  }

  @Test
  public void puzzle3() throws Exception {
    testSolvingWithParser(createPuzzle3);
  }

  @Test
  public void puzzle4() throws Exception {
    testSolvingWithParser(createPuzzle4);
  }

  @Test
  public void puzzle5() throws Exception {
    testSolvingWithParser(createPuzzle5);
  }
}
