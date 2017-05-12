package com.vkostin.common;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AnotherSolverInstance<VC extends AValueCell<V>, V extends IValue> {
  protected final Puzzle _puzzle;
  protected final CFluentPuzzle _fluentPuzzle;
  private final List<CFluentCell<VC>> _valueCells;

  public AnotherSolverInstance(final Puzzle puzzle) {
    this._puzzle = puzzle;
    // TODO: either parser MUST parse to proper class, or conversion upon new CFluentPuzzle
    this._fluentPuzzle = new CFluentPuzzle(puzzle);
    _valueCells = this._fluentPuzzle.cells().stream()
            .filter(c -> null != c.cell().as(valueCellClass()))
            .map(c -> (CFluentCell<VC>)c)
            .collect(Collectors.toList());
  }

  public Puzzle solve() {
    Optional<CFluentCell<VC>> optionalUnsolvedCell = findUnsolvedCell();
    if (!optionalUnsolvedCell.isPresent()) return finalizeSolvedPuzzle(_puzzle);

    CFluentCell<VC> unsolvedCell = optionalUnsolvedCell.get();
    for (V assumption : assumptionsToBeMade()) {
      unsolvedCell.cell().setValue(assumption);

      if (isAnyRuleBroken(unsolvedCell)) continue;

      Puzzle result = solve();
      if (null != result) return result;
    }

    unsolvedCell.cell().clearValue();
    return null;
  }

  protected Puzzle finalizeSolvedPuzzle(final Puzzle puzzle) { return puzzle; }

  private Optional<CFluentCell<VC>> findUnsolvedCell() {
    return _valueCells.stream()
            .filter(vc -> null == vc.cell().value())
            .findFirst();
  }

  protected abstract Iterable<V> assumptionsToBeMade();
  protected abstract Class<VC> valueCellClass();
  protected abstract boolean isAnyRuleBroken(CFluentCell<VC> cell);
}
