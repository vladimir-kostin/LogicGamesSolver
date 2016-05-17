package com.vkostin.puzzle.lineSweeper;

import com.vkostin.common.Cell;

import java.util.Objects;
import java.util.Optional;

class PathCell implements Cell {
  private PathWay _path;

  public PathCell() { this(null); }
  public PathCell(PathWay path) { this._path = path; }

  public PathWay getPath() { return _path; }
  public void setPath(PathWay path) { this._path = path; }
  public void clearPath() { this._path = null; }

  public boolean hasNonEmptyPath() {
    return Optional.ofNullable(_path)
            .map(PathWay::isNotEmpty)
            .orElse(false);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PathCell pathCell = (PathCell) o;
    return _path == pathCell._path;
  }

  @Override
  public int hashCode() {
    return Objects.hash(_path);
  }

  @Override
  public String toString() {
    return (null == _path) ? "_" : "" + _path;
  }

}
