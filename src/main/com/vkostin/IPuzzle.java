package com.vkostin;

public interface IPuzzle {
  ValueCell findFirstUnsolvedValueCellOrNull();
  boolean hasErrors();
}
