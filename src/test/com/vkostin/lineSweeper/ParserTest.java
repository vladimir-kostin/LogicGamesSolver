package com.vkostin.lineSweeper;

import com.vkostin.Cell;
import com.vkostin.Puzzle;
import com.vkostin.PuzzleAsList;
import com.vkostin.PuzzleBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

public class ParserTest {
  private Function<Cell[][], Puzzle> createPuzzle = PuzzleAsList::new;
  private Parser parser;

  @Before
  public void setUp() {
    parser = new Parser(createPuzzle);
  }

  private TaskCell tc(int numberOfPathCellsAround) { return  new TaskCell(numberOfPathCellsAround); }
  private PathCell pc() { return new PathCell(); }

  private PathCell pct() { return new PathCell(PathWay.DOWN_LEFT); }
  private PathCell pcf() { return new PathCell(PathWay.DOWN_RIGHT); }
  private PathCell pc_() { return new PathCell(PathWay.LEFT_RIGHT); }
  private PathCell pci() { return new PathCell(PathWay.UP_DOWN); }
  private PathCell pcj() { return new PathCell(PathWay.UP_LEFT); }
  private PathCell pcl() { return new PathCell(PathWay.UP_RIGHT); }

  private PuzzleBuilder aPuzzle() { return PuzzleBuilder.aPuzzle(createPuzzle); }

  @Test
  public void shouldParsePuzzle___() {
    Puzzle result = parser.parse(TestData.PUZZLE___);

    Puzzle expected = aPuzzle()
            .addRow(tc(3), pc( ), pc( ), pc( ), tc(3))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(tc(3), pc( ), pc( ), pc( ), tc(3))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParseSolution___() {
Puzzle result = parser.parse(TestData.SOLUTION___);

    Puzzle expected = aPuzzle()
            .addRow(tc(3), pcf(), pc_(), pct(), tc(3))
            .addRow(pcf(), pcj(), pc( ), pcl(), pct())
            .addRow(pci(), pc( ), pc( ), pc( ), pci())
            .addRow(pcl(), pct(), pc( ), pcf(), pcj())
            .addRow(tc(3), pcl(), pc_(), pcj(), tc(3))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldPartPuzzle__1() {
    Puzzle result = parser.parse(TestData.PUZZLE__1);

    Puzzle expected = aPuzzle()
            .addRow(pc( ), tc(3), pc( ), pc( ), tc(3), pc( ))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ), pc( ))
            .addRow(tc(5), pc( ), tc(3), pc( ), pc( ), tc(5))
            .addRow(pc( ), pc( ), pc( ), tc(5), pc( ), pc( ))
            .addRow(pc( ), pc( ), pc( ), pc( ), pc( ), pc( ))
            .build();

    Assert.assertEquals(expected, result);
  }

  @Test
  public void shouldParseSolution__1() {
    Puzzle result = parser.parse(TestData.SOLUTION__1);

    Puzzle expected = aPuzzle()
            .addRow(pc( ), tc(3), pc( ), pc( ), tc(3), pc( ))
            .addRow(pcf(), pc_(), pc_(), pc_(), pc_(), pct())
            .addRow(pcl(), pct(), pc( ), pc( ), pcf(), pcj())
            .addRow(tc(5), pci(), tc(3), pc( ), pci(), tc(5))
            .addRow(pcf(), pcj(), pc( ), tc(5), pcl(), pct())
            .addRow(pcl(), pc_(), pc_(), pc_(), pc_(), pcj())
            .build();

    Assert.assertEquals(expected, result);
  }

}
