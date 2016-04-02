package com.vkostin.tennerGrid;

import com.vkostin.Puzzle;
import com.vkostin.Solver;

public class SimpleSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) { return new SolverInstance(puzzle).solve(); }

  static class SolverInstance extends AbstractSolverInstance {
    public SolverInstance(Puzzle puzzle) { super(puzzle); }
  }

}
