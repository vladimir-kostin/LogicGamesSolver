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

  static boolean isEmpty(PathWay pathWay) { return EMPTY.equals(pathWay); }
  static boolean isNotEmpty(PathWay pathWay) { return !isEmpty(pathWay); }

}
