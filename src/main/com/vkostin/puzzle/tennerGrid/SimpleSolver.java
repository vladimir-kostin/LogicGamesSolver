package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.Puzzle;
import com.vkostin.common.Solver;

public class SimpleSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) { return new SolverInstance(puzzle).solve(); }

  static class SolverInstance extends AbstractSolverInstance {
    public SolverInstance(Puzzle puzzle) { super(puzzle); }
  }

}
