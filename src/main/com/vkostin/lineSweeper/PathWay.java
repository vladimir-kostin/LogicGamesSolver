package com.vkostin.lineSweeper;

enum PathWay {
  DOWN_LEFT {
    @Override public boolean connectsToCellAbove() { return false; }
    @Override public boolean connectsToCellBelow() { return true; }
    @Override public boolean connectsToCellOnTheLeft() { return true; }
    @Override public boolean connectsToCellOnTheRight() { return false; }
  },
  DOWN_RIGHT {
    @Override public boolean connectsToCellAbove() { return false; }
    @Override public boolean connectsToCellBelow() { return true; }
    @Override public boolean connectsToCellOnTheLeft() { return false; }
    @Override public boolean connectsToCellOnTheRight() { return true; }
  },
  LEFT_RIGHT {
    @Override public boolean connectsToCellAbove() { return false; }
    @Override public boolean connectsToCellBelow() { return false; }
    @Override public boolean connectsToCellOnTheLeft() { return true; }
    @Override public boolean connectsToCellOnTheRight() { return true; }
  },
  UP_DOWN {
    @Override public boolean connectsToCellAbove() { return true; }
    @Override public boolean connectsToCellBelow() { return true; }
    @Override public boolean connectsToCellOnTheLeft() { return false; }
    @Override public boolean connectsToCellOnTheRight() { return false; }
  },
  UP_LEFT {
    @Override public boolean connectsToCellAbove() { return true; }
    @Override public boolean connectsToCellBelow() { return false; }
    @Override public boolean connectsToCellOnTheLeft() { return true; }
    @Override public boolean connectsToCellOnTheRight() { return false; }
  },
  UP_RIGHT {
    @Override public boolean connectsToCellAbove() { return true; }
    @Override public boolean connectsToCellBelow() { return false; }
    @Override public boolean connectsToCellOnTheLeft() { return false; }
    @Override public boolean connectsToCellOnTheRight() { return true; }
  };

  public abstract boolean connectsToCellAbove();
  public abstract boolean connectsToCellBelow();
  public abstract boolean connectsToCellOnTheLeft();
  public abstract boolean connectsToCellOnTheRight();
}
