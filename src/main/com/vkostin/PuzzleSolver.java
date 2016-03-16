package com.vkostin;

public class PuzzleSolver {

  public IPuzzle solve(IPuzzle puzzle) {
    if(puzzle.hasErrors()) {
      return null;
    }

    ValueCell unsolvedValueCell = puzzle.findFirstUnsolvedValueCellOrNull();
    if (null == unsolvedValueCell) {
      return puzzle;
    }

    // assumptions
    for (int valueToBeTried = ValueCell.MIN_ALLOWED_VALUE; valueToBeTried <= ValueCell.MAX_ALLOWED_VALUE; valueToBeTried++) {
      unsolvedValueCell.setValue(valueToBeTried);
      IPuzzle result = solve(puzzle);
      if(null != result) { return result; }
    }

    // no assumptions fit
    unsolvedValueCell.clearValue();
    return null;
  }

}
