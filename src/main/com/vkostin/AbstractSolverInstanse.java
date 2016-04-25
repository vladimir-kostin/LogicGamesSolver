package com.vkostin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO think of better-solver on abstract level (simple solver checks entire puzzle on each step which is slow)
// 0. Might make sense to check whether puzzle contains error before any assumption is made
//
// 1. select one unsolved (value) cell to
// 2. try setting values to that cell
// 3. quickly check whether assumption creates error(s) in a puzzle
// 4. if no errors --> goto 1 (recursively)
// 5. if errors and another assumptions can be made to that cell --> goto 2
// 6. if here that means that some of previous assumptions were incorrect --> get out of recursion
public abstract class AbstractSolverInstanse {

  protected static class PuzzleWithCoordinates {
    private final List<CellWithCoordinates<Cell>> _cells;

    public PuzzleWithCoordinates(Puzzle puzzle) {
      List<CellWithCoordinates<Cell>> cells = new ArrayList<>();
      for (int rowIndex = 0; rowIndex < puzzle.getRowCount(); rowIndex++) {
        for (int columnIndex = 0; columnIndex < puzzle.getRowLength(); columnIndex++) {
          cells.add(new CellWithCoordinates<>(puzzle.getCellAt(rowIndex, columnIndex), rowIndex, columnIndex));
        }
      }
      _cells = Collections.unmodifiableList(cells);
    }

    public List<CellWithCoordinates<Cell>> cells() { return _cells; }
  }

  protected final Puzzle _puzzle;
  protected final PuzzleWithCoordinates _withCoords;
  protected AbstractSolverInstanse(Puzzle puzzle) {
    // TODO check for puzzle to NOT contain any error
    this._puzzle = puzzle;
    this._withCoords = new PuzzleWithCoordinates(puzzle);
  }

  public Puzzle solve() {
    CellWithCoordinates<Cell> unsolvedCell = findUnsolvedCell();
    // TODO : actually if no-unsolved cell is found there still might be a more complex error, so it might make sense to perform full check here
    if (null == unsolvedCell) return finalizeSolvedPuzzle(_puzzle);

    for (Object anAssumption : assumptionsToBeMade()) {
      setValue(anAssumption, unsolvedCell);

      if(isAnyRuleBroken(unsolvedCell)) continue;

      Puzzle result = solve();
      if (null != result) return result;
    }

    clearValue(unsolvedCell);
    return null;
  }

  protected CellWithCoordinates cellWithCoordinatesOrNullAt(int rowIndex, int columnIndex) {
    if (0 > rowIndex || _puzzle.getRowCount() <= rowIndex
            || 0 > columnIndex || _puzzle.getRowLength() <= columnIndex) return null;

    return new CellWithCoordinates(_puzzle.getCellAt(rowIndex, columnIndex), rowIndex, columnIndex);
  }

  protected Puzzle finalizeSolvedPuzzle(Puzzle puzzle) {
    return puzzle;
  }

  protected abstract CellWithCoordinates<Cell> findUnsolvedCell();
  protected abstract List<?> assumptionsToBeMade();
  protected abstract boolean isAnyRuleBroken(CellWithCoordinates<Cell> cell);
  protected abstract void setValue(Object value, CellWithCoordinates<Cell> cell);
  protected abstract void clearValue(CellWithCoordinates<Cell> cell);

}
