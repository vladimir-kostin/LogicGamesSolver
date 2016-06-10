package com.vkostin.common;

public abstract class AbstractFluentCheckSingleChangeSolverInstanse {
  protected final Puzzle _puzzle;
  protected final FluentPuzzle _fluentPuzzle;

  public AbstractFluentCheckSingleChangeSolverInstanse(final Puzzle puzzle) {
    _puzzle = puzzle;
    _fluentPuzzle = new FluentPuzzle(puzzle);
  }

  public Puzzle solve() {
    FluentCell unsolvedCell = findUnsolvedCell();
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


  protected FluentCell findUnsolvedCell() {
    return _fluentPuzzle.cells().stream()
            .filter(this::isValueCellUnsolved)
            .findAny()
            .orElse(null);
  }

  private Puzzle finalizeSolvedPuzzle(final Puzzle puzzle) { return puzzle; }

  protected abstract boolean isValueCellUnsolved(FluentCell cell);
  protected abstract Iterable<? extends Object> assumptionsToBeMade();
  protected abstract boolean isAnyRuleBroken(FluentCell cell);
  protected abstract void setValue(Object value, FluentCell cell);
  protected abstract void clearValue(FluentCell cell);
}
