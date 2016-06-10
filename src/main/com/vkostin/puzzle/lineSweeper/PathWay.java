package com.vkostin.puzzle.lineSweeper;

import com.vkostin.common.Direction;

import java.util.Objects;

enum PathWay {
  DOWN_LEFT(Direction.DOWN, Direction.LEFT) {
    @Override public String toString() { return "T"; }
  },
  DOWN_RIGHT(Direction.DOWN, Direction.RIGHT) {
    @Override public String toString() { return "F"; }
  },
  LEFT_RIGHT(Direction.LEFT, Direction.RIGHT) {
    @Override public String toString() { return "-"; }
  },
  UP_DOWN(Direction.UP, Direction.DOWN) {
    @Override public String toString() { return "|"; }
  },
  UP_LEFT(Direction.UP, Direction.LEFT) {
    @Override public String toString() { return "J"; }
  },
  UP_RIGHT(Direction.UP, Direction.RIGHT) {
    @Override public String toString() { return "L"; }
  },
  EMPTY(null, null) {
    @Override public String toString() { return "_"; }
  };

  private final Direction dir1;
  private final Direction dir2;
  PathWay(Direction dir1, Direction dir2) {
    this.dir1 = dir1;
    this.dir2 = dir2;
  }

  static boolean isEmpty(PathWay way) { return EMPTY.equals(way); }
  static boolean isNotEmpty(PathWay way) { return !isEmpty(way); }

  public boolean connectsToCellIn(Direction direction) {
    return Objects.equals(dir1, direction) || Objects.equals(dir2, direction);
  }

  public boolean connectsToCellAbove() { return connectsToCellIn(Direction.UP); }
  public boolean connectsToCellBelow() { return  connectsToCellIn(Direction.DOWN); }
  public boolean connectsToCellOnTheLeft() { return connectsToCellIn(Direction.LEFT); }
  public boolean connectsToCellOnTheRight() { return connectsToCellIn(Direction.RIGHT); }

}
