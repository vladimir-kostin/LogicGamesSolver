package com.vkostin.lineSweeper;

import java.util.Objects;

import static com.vkostin.lineSweeper.Direction.*;

public enum Way {
  DOWN_LEFT(DOWN, LEFT),
  DOWN_RIGHT(DOWN, RIGHT),
  LEFT_RIGHT(LEFT, RIGHT),
  UP_DOWN(UP, DOWN),
  UP_LEFT(UP, LEFT),
  UP_RIGHT(UP, RIGHT),
  EMPTY(null, null);

  private final Direction dir1;
  private final Direction dir2;
  private Way(Direction dir1, Direction dir2) {
    this.dir1 = dir1;
    this.dir2 = dir2;
  }

  static boolean isEmpty(Way way) { return EMPTY.equals(way); }
  static boolean isNotEmpty(Way way) { return !isEmpty(way); }

  public boolean connectsToCellIn(Direction direction) {
    return Objects.equals(dir1, direction) || Objects.equals(dir2, direction);
  }

  public boolean connectsToCellAbove() { return connectsToCellIn(UP); }
  public boolean connectsToCellBelow() { return  connectsToCellIn(DOWN); }
  public boolean connectsToCellOnTheLeft() { return connectsToCellIn(LEFT); }
  public boolean connectsToCellOnTheRight() { return connectsToCellIn(RIGHT); }

}
;