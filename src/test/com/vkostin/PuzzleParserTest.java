package com.vkostin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PuzzleParserTest {

  PuzzleParser puzzleParser;

  @Before
  public void setUp() throws Exception {
    puzzleParser = new PuzzleParser();
  }

  @Test
  public void shouldParse3x4Puzzle() {
    final String input = String.join("\n"
            , "\\  3\\ 4\\ 6\\"
            , "\\6 _   _   _"
            , "\\7 _   _   _"
    );

    Puzzle result = puzzleParser.parse(input);

    assertRow(result.getCells()[0], tC(0, 0), tC(3, 0), tC(4, 0), tC(6, 0));
    assertRow(result.getCells()[1], tC(0, 6), vC(), vC(), vC());
    assertRow(result.getCells()[2], tC(0, 7), vC(), vC(), vC());
  }

  private TaskCell tC() { return tC(0,0); }
  private TaskCell tC(int sumBelow, int sumRight) {
    return new TaskCell(sumBelow, sumRight);
  }

  private ValueCell vC() {
    return new ValueCell();
  }

  private void assertRow(Cell[] line, Cell... expectedCells) {
    Assert.assertEquals(expectedCells.length, line.length);

    for (int j = 0; j < expectedCells.length; j++) {
      Assert.assertEquals(expectedCells[j], line[j]);
    }
  }

  @Test
  public void shouldParse5x5Puzzle() {

    final String input = String.join("\r\n"
            , "\\  4\\  9\\  \\   \\"
            , "\\4 _    _    21\\ \\"
            , "\\7 _    _    _    16\\"
            , "\\  \\23 _    _    _"
            , "\\  \\   \\16 _    _"
    );

    Puzzle result = puzzleParser.parse(input);

    assertRow(result.getCells()[0], tC(), tC(4, 0), tC(9, 0), tC(), tC());
    assertRow(result.getCells()[1], tC(0, 4), vC(), vC(), tC(21, 0), tC());
    assertRow(result.getCells()[2], tC(0,7), vC(), vC(), vC(), tC(16,0));
    assertRow(result.getCells()[3], tC(), tC(0,23), vC(), vC(), vC());
    assertRow(result.getCells()[4], tC(), tC(), tC(0,16), vC(), vC());
  }

}
