package com.vkostin;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class PuzlleModelTest {

  private Function<Cell[][], IPuzzle> createPuzzle1 = cells -> new Puzzle(cells);
  private Function<Cell[][], IPuzzle> createPuzzle2 = cells -> new Puzzle2(cells);
  private Function<Cell[][], IPuzzle> createPuzzle3 = cells -> new Puzzle3(cells);

  private PuzzleSolver solver = new PuzzleSolver();

  private void testSolvingWithParser(PuzzleParser parser) {
    TestData.ALL_PUZZLES_WITH_SOLUTIONS.entrySet().stream()
            .forEach(e ->
                    Assert.assertEquals(
                            parser.parse(e.getValue()),
                            solver.solve(parser.parse(e.getKey()))
                    ));
  }

  @Test
  public void puzzle1() throws Exception {
    testSolvingWithParser(new PuzzleParser(createPuzzle1));
  }

  @Test
  public void puzzle2() throws Exception {
    testSolvingWithParser(new PuzzleParser(createPuzzle2));
  }

  @Test
  public void puzzle3() throws Exception {
    testSolvingWithParser(new PuzzleParser(createPuzzle3));
  }

}
