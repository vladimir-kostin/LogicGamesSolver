package com.vkostin.lineSweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class LoopedPathBuilder {

  public List<FluentCell> buildLoopedPathStartingFrom(final FluentCell startCell) {
    if (!hasNonEmptyPath(startCell)) return null;

    Direction d = getDirectionFromCellDifferentFrom(startCell, null);

    List<FluentCell> loopedPath = new ArrayList<>();
    for (FluentCell c = startCell; null != c; d = getDirectionFromCellDifferentFrom(c, d.opposite())) {
      loopedPath.add(c);
      c = c.neighbourTo(d);
      if (!hasNonEmptyPath(c)) return null;
      if (startCell == c) return loopedPath;
    }
    return  null;
  }

  private boolean hasNonEmptyPath(FluentCell cell) {
    return pathCell(cell)
            .map(PathCell::hasNonEmptyPath)
            .orElse(false);
  }

  private Optional<PathCell> pathCell(FluentCell cell) {
    return Optional.ofNullable(cell)
            .map(FluentCell::cell)
            .map(c -> c.as(PathCell.class));
  }

  private Direction getDirectionFromCellDifferentFrom(FluentCell cell, Direction unwantedDirection) {
    return pathCell(cell)
            .map(PathCell::getPath)
            .map(pathWay -> getDirectionFromPathDifferentFrom(pathWay, unwantedDirection))
            .orElse(null)
    ;
  }

  private Direction getDirectionFromPathDifferentFrom(PathWay pathWay, Direction unwantedDirection) {
    return Stream.of(Direction.values())
            .filter(d -> d != unwantedDirection)
            .filter(pathWay::connectsToCellIn)
            .findAny()
            .orElse(null);
  }

}
