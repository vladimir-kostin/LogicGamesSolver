package com.vkostin;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class PuzlleModelTest {

  private Function<Cell[][], IPuzzle> createPuzzle1 = cells -> new Puzzle(cells);
  private Function<Cell[][], IPuzzle> createPuzzle3 = cells -> new Puzzle3(cells);
  private Function<Cell[][], IPuzzle> createPuzzle4 = cells -> new Puzzle4(cells);

  private PuzzleSolver solver = new PuzzleSolver();

  private void testSolvingWithParser(Function<Cell[][], IPuzzle> createPuzzle) {
    PuzzleParser parser = new PuzzleParser(createPuzzle);
    TestData.ALL_PUZZLES_WITH_SOLUTIONS.entrySet().stream()
            .forEach(e ->
                    Assert.assertEquals(
                            parser.parse(e.getValue()),
                            solver.solve(parser.parse(e.getKey()))
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
}
