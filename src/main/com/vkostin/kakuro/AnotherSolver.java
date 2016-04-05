package com.vkostin.kakuro;

import com.vkostin.Puzzle;
import com.vkostin.Solver;

public class AnotherSolver implements Solver {

  @Override
  public Puzzle solve(Puzzle puzzle) { return new SolverInstance(puzzle).solve(); }

  static class SolverInstance extends BetterPuzzleSolver.SolverInstance  {
    public SolverInstance(Puzzle puzzle) { super(puzzle); }
  }

}
