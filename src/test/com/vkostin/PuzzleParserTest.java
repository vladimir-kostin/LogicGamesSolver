package com.vkostin;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class PuzzleParserTest {

  PuzzleParser puzzleParser;

  @Before
  public void setUp() throws Exception {
    puzzleParser = new PuzzleParser();
  }

  @Test
  public void shouldParseSimplePuzzle() throws Exception {

    final String input = String.join("\r\n"
            , "\\  4\\  9\\  \\   \\"
            , "\\4 _    _    21\\ \\"
            , "\\7 _    _    _    16\\"
            , "\\  \\23 _    _    _"
            , "\\  \\   \\16 _    _"
    );

    Puzzle result = puzzleParser.parse(input);

    assertLine1(result.getCells()[0]);
    assertLine2(result.getCells()[1]);
    assertLine3(result.getCells()[2]);
    assertLine4(result.getCells()[3]);
    assertLine5(result.getCells()[4]);
  }

  private void assertTaskCell(Cell cell, int expectedSumOfValueBelow, int expectedSumOfValuesOnTheRight) {
    Assert.assertTrue(cell instanceof TaskCell);
    TaskCell taskCell = (TaskCell) cell;

    Assert.assertEquals(expectedSumOfValueBelow, taskCell.getSumOfValuesBelow());
    Assert.assertEquals(expectedSumOfValuesOnTheRight, taskCell.getSumOfValueOnTheRight());
  }

  private void assertEmptyValueCell(Cell cell) {
    Assert.assertTrue(cell instanceof  ValueCell);
    ValueCell valueCell = (ValueCell) cell;

    Assert.assertEquals(-1, valueCell.getValue());
  }

  private void assertLine1(Cell[] line) {
    assertTaskCell(line[0], 0, 0);
    assertTaskCell(line[1], 4, 0);
    assertTaskCell(line[2], 9, 0);
    assertTaskCell(line[3], 0, 0);
    assertTaskCell(line[4], 0, 0);
  }

  private void assertLine2(Cell[] line) {
    assertTaskCell(line[0], 0, 4);
    assertEmptyValueCell(line[1]);
    assertEmptyValueCell(line[2]);
    assertTaskCell(line[3], 21, 0);
    assertTaskCell(line[4], 0, 0);
  }

  private void assertLine3(Cell[] line) {
    assertTaskCell(line[0], 0, 7);
    assertEmptyValueCell(line[1]);
    assertEmptyValueCell(line[2]);
    assertEmptyValueCell(line[3]);
    assertTaskCell(line[4], 16, 0);
  }

  private void assertLine4(Cell[] line) {
    assertTaskCell(line[0], 0, 0);
    assertTaskCell(line[1], 0, 23);
    assertEmptyValueCell(line[2]);
    assertEmptyValueCell(line[3]);
    assertEmptyValueCell(line[4]);
  }

  private void assertLine5(Cell[] line) {
    assertTaskCell(line[0], 0, 0);
    assertTaskCell(line[1], 0, 0);
    assertTaskCell(line[2], 0, 16);
    assertEmptyValueCell(line[3]);
    assertEmptyValueCell(line[4]);
  }

}
