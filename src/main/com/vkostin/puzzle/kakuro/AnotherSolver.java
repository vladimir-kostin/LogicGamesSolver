package com.vkostin.puzzle.kakuro;

import com.vkostin.common.*;

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
      CFluentCell<CRules.KakuroTaskCell> taskCellAbove;
      Optional<CFluentCell<? extends Cell>> cellAbove = Optional.of(cell);
      do {
        cellAbove.map(c -> c.neighbourTo(Direction.UP));
      } while (cellAbove.)
      return false;
    }

    private boolean isAnyRuleBrokenToTheLeft(final CFluentCell<IntegerValueCell> cell) {
      return false;
    }

  }

}
