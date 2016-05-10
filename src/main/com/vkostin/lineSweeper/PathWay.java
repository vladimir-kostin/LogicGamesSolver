package com.vkostin.lineSweeper;

import java.util.Objects;

import static com.vkostin.lineSweeper.Direction.*;

public enum PathWay {
  DOWN_LEFT(DOWN, LEFT) {
    @Override public String toString() { return "T"; }
  },
  DOWN_RIGHT(DOWN, RIGHT) {
    @Override public String toString() { return "F"; }
  },
  LEFT_RIGHT(LEFT, RIGHT) {
    @Override public String toString() { return "-"; }
  },
  UP_DOWN(UP, DOWN) {
    @Override public String toString() { return "|"; }
  },
  UP_LEFT(UP, LEFT) {
    @Override public String toString() { return "J"; }
  },
  UP_RIGHT(UP, RIGHT) {
    @Override public String toString() { return "L"; }
  },
  EMPTY(null, null) {
    @Override public String toString() { return "_"; }
  };

  private final Direction dir1;
  private final Direction dir2;
  private PathWay(Direction dir1, Direction dir2) {
    this.dir1 = dir1;
    this.dir2 = dir2;
  }

  static boolean isEmpty(PathWay way) { return EMPTY.equals(way); }
  static boolean isNotEmpty(PathWay way) { return !isEmpty(way); }

  public boolean connectsToCellIn(Direction direction) {
    return Objects.equals(dir1, direction) || Objects.equals(dir2, direction);
  }

  public boolean connectsToCellAbove() { return connectsToCellIn(UP); }
  public boolean connectsToCellBelow() { return  connectsToCellIn(DOWN); }
  public boolean connectsToCellOnTheLeft() { return connectsToCellIn(LEFT); }
  public boolean connectsToCellOnTheRight() { return connectsToCellIn(RIGHT); }

}
