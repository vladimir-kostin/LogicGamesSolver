package com.vkostin.lineSweeper;

enum PathWay {

  DOWN_LEFT {
    @Override public boolean connectsToCellAbove() { return false; }
    @Override public boolean connectsToCellBelow() { return true; }
    @Override public boolean connectsToCellOnTheLeft() { return true; }
    @Override public boolean connectsToCellOnTheRight() { return false; }
    @Override public String toString() { return "T"; }
  },
  DOWN_RIGHT {
    @Override public boolean connectsToCellAbove() { return false; }
    @Override public boolean connectsToCellBelow() { return true; }
    @Override public boolean connectsToCellOnTheLeft() { return false; }
    @Override public boolean connectsToCellOnTheRight() { return true; }
    @Override public String toString() { return "F"; }
  },
  LEFT_RIGHT {
    @Override public boolean connectsToCellAbove() { return false; }
    @Override public boolean connectsToCellBelow() { return false; }
    @Override public boolean connectsToCellOnTheLeft() { return true; }
    @Override public boolean connectsToCellOnTheRight() { return true; }
    @Override public String toString() { return "-"; }
  },
  UP_DOWN {
    @Override public boolean connectsToCellAbove() { return true; }
    @Override public boolean connectsToCellBelow() { return true; }
    @Override public boolean connectsToCellOnTheLeft() { return false; }
    @Override public boolean connectsToCellOnTheRight() { return false; }
    @Override public String toString() { return "|"; }
  },
  UP_LEFT {
    @Override public boolean connectsToCellAbove() { return true; }
    @Override public boolean connectsToCellBelow() { return false; }
    @Override public boolean connectsToCellOnTheLeft() { return true; }
    @Override public boolean connectsToCellOnTheRight() { return false; }
    @Override public String toString() { return "J"; }
  },
  UP_RIGHT {
    @Override public boolean connectsToCellAbove() { return true; }
    @Override public boolean connectsToCellBelow() { return false; }
    @Override public boolean connectsToCellOnTheLeft() { return false; }
    @Override public boolean connectsToCellOnTheRight() { return true; }
    @Override public String toString() { return "L"; }
  },
  EMPTY {
    @Override public boolean connectsToCellAbove() { return false; }
    @Override public boolean connectsToCellBelow() { return false; }
    @Override public boolean connectsToCellOnTheLeft() { return false; }
    @Override public boolean connectsToCellOnTheRight() { return false; }
    @Override public String toString() { return "_"; }
  };

  public abstract boolean connectsToCellAbove();
  public abstract boolean connectsToCellBelow();
  public abstract boolean connectsToCellOnTheLeft();
  public abstract boolean connectsToCellOnTheRight();

  private enum Direction {
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
}
