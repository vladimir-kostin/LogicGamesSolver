package com.vkostin.lineSweeper;

public enum Direction {
  DOWN {
    @Override public Direction opposite() { return UP; }
  },
  LEFT {
    @Override public Direction opposite() { return RIGHT; }
  },
  RIGHT {
    @Override public Direction opposite() { return LEFT; }
  },
  UP {
    @Override public Direction opposite() { return DOWN; }
  };

  public abstract Direction opposite();
}
