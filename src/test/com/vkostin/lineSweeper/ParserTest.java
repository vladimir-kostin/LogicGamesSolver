package com.vkostin.lineSweeper;

import com.vkostin.Cell;
import com.vkostin.Puzzle;
import com.vkostin.PuzzleAsList;
import com.vkostin.PuzzleBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class ParserTest {
  private Function<Cell[][], Puzzle> createPuzzle = PuzzleAsList::new;
  private Parser parser;

  @Before
  public void setUp() {
    parser = new Parser(createPuzzle);
  }

  private TaskCell tc(int numberOfPathCellsAround) { return  new TaskCell(numberOfPathCellsAround); }
  private PathCell pc() { return new PathCell(); }

  private PuzzleBuilder aPuzzle() { return PuzzleBuilder.aPuzzle(createPuzzle); }

  @Test
  public void shouldParsePuzzle___() {
    Puzzle result = parser.parse(TestData.PUZZLE___);

    Puzzle expectd = aPuzzle()
            .addRow(tc(2), pc( ), pc( ), pc( ), tc(2))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(tc(2), pc( ), pc( ), pc( ), tc(2))
            .build();

    Assert.assertEquals(expectd, result);
  }

}
