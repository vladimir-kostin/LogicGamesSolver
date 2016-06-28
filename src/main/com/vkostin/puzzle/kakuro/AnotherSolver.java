package com.vkostin.puzzle.kakuro;

import com.vkostin.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnotherSolver implements Solver {
  @Override
  public Puzzle solve(final Puzzle puzzle) {
    return new SolverInstance(puzzle).solve();
  }

  public static class SolverInstance extends AnotherSolverInstance<IntegerValueCell, IntegerValue> {
    public SolverInstance(final Puzzle puzzle) {
      super(puzzle);
    }

    @Override
    protected Iterable<IntegerValue> assumptionsToBeMade() {
      return CRules.assumptions();
    }

    @Override
    protected Class<IntegerValueCell> valueCellClass() {
      return IntegerValueCell.class;
    }

    @Override
    protected boolean isAnyRuleBroken(final CFluentCell<IntegerValueCell> cell) {
      if (isAnyRuleBrokenToAbove(cell)) return true;
      if (isAnyRuleBrokenToTheLeft(cell)) return true;

      return false;
    }

    private boolean isAnyRuleBrokenToAbove(final CFluentCell<IntegerValueCell> cell) {
      Optional<CFluentCell<? extends Cell>> cellAbove = Optional.of(cell);
      do {
        cellAbove = cellAbove.flatMap(CFluentCell::neighbourUpwards);
      } while (cellAbove.filter(c -> c.cell() instanceof IntegerValueCell).isPresent());
      CFluentCell<CRules.KakuroTaskCell> taskCellAbove = (CFluentCell<CRules.KakuroTaskCell>) cellAbove.get();

      List<IntegerValueCell> valueCellsBelow = new ArrayList<>();
      for (Optional<CFluentCell<? extends Cell>> below = cellAbove.flatMap(CFluentCell::neighbourDownwards);
           below.map(CFluentCell::cell).filter(c -> c instanceof IntegerValueCell).isPresent();
           below = below.flatMap(CFluentCell::neighbourDownwards)) {

        valueCellsBelow.add((IntegerValueCell) below.map(CFluentCell::cell).get());
      }

      return IntegerValueCell.doCellsFailToMeetExpectation(
              taskCellAbove.cell().task().sumOfValuesBelow()
              , valueCellsBelow
              , Rules::hasProperValue
              , false);
    }

    private boolean isAnyRuleBrokenToTheLeft(final CFluentCell<IntegerValueCell> cell) {
      Optional<CFluentCell<? extends Cell>> cellToTheLeft = Optional.of(cell);
      do {
        cellToTheLeft = cellToTheLeft.flatMap(CFluentCell::neighbourToTheLeft);
      } while (cellToTheLeft.filter(c -> c.cell() instanceof IntegerValueCell).isPresent());
      CFluentCell<CRules.KakuroTaskCell> taskCellToTheLeft = (CFluentCell<CRules.KakuroTaskCell>) cellToTheLeft.get();

      List<IntegerValueCell> valueCellsToTheRight = new ArrayList<>();
      for (Optional<CFluentCell<? extends Cell>> onTheRight = cellToTheLeft.flatMap(CFluentCell::neighbourToTheRight);
              onTheRight.map(CFluentCell::cell).filter(c -> c instanceof IntegerValueCell).isPresent();
              onTheRight = onTheRight.flatMap(CFluentCell::neighbourToTheRight)) {

        valueCellsToTheRight.add((IntegerValueCell) onTheRight.map(CFluentCell::cell).get());
      }

      return IntegerValueCell.doCellsFailToMeetExpectation(
              taskCellToTheLeft.cell().task().sumOfValuesOnTheRight()
              , valueCellsToTheRight
              , Rules::hasProperValue
              , false);
    }

  }

}
