package com.vkostin;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class PuzlleModelTest {

  private Function<Cell[][], IPuzzle> createPuzzle1 = cells -> new Puzzle(cells);
  private Function<Cell[][], IPuzzle> createPuzzle2 = cells -> new Puzzle2(cells);
  private Function<Cell[][], IPuzzle> createPuzzle3 = cells -> new Puzzle3(cells);

  private PuzzleSolver solver = new PuzzleSolver();

  @Test
  public void puzzle1() throws Exception {

    PuzzleParser parser = new PuzzleParser(createPuzzle1);

    for (int j = 0; j < 500; j++) {
      Assert.assertEquals(parser.parse(TestData.SOLUTION_3_X_4)
              , solver.solve(parser.parse(TestData.PUZZLE_3_X_4)));

      Assert.assertEquals(parser.parse(TestData.SOLUTION_5_X_5)
              , solver.solve(parser.parse(TestData.PUZZLE_5_X_5)));
    }
  }

  @Test
  public void puzzle2() throws Exception {

    PuzzleParser parser = new PuzzleParser(createPuzzle2);

    for (int j = 0; j < 500; j++) {
      Assert.assertEquals(parser.parse(TestData.SOLUTION_3_X_4)
              , solver.solve(parser.parse(TestData.PUZZLE_3_X_4)));

      Assert.assertEquals(parser.parse(TestData.SOLUTION_5_X_5)
              , solver.solve(parser.parse(TestData.PUZZLE_5_X_5)));
    }
  }

  @Test
  public void puzzle3() throws Exception {

    PuzzleParser parser = new PuzzleParser(createPuzzle3);

      for (int j = 0; j < 500; j++) {
        Assert.assertEquals(parser.parse(TestData.SOLUTION_3_X_4)
                , solver.solve(parser.parse(TestData.PUZZLE_3_X_4)));

        Assert.assertEquals(parser.parse(TestData.SOLUTION_5_X_5)
                , solver.solve(parser.parse(TestData.PUZZLE_5_X_5)));
      }
  }

}
