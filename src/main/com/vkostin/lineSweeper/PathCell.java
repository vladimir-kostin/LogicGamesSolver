package com.vkostin.lineSweeper;

import com.vkostin.Cell;

import java.util.Objects;

class PathCell implements Cell {
  private PathWay _path;

  public PathCell() { this(null); }
  public PathCell(PathWay path) { this._path = path; }

  public PathWay getPath() { return _path; }
  public void setPath(PathWay path) { this._path = path; }
  public void clearPath() { this._path = null; }

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

}
