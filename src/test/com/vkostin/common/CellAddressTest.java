package com.vkostin.common;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CellAddressTest {

  @Test
  public void testHashCode() {
    int n = 30;
    Set<Integer> hasCodes = new HashSet<>();
    for (int rowIndex = 0; rowIndex < n; rowIndex++) {
      for (int colIndex = 0; colIndex < n; colIndex++) {
        hasCodes.add(new CellAddress(rowIndex, colIndex).hashCode());
      }
    }

    assertEquals(n*n, hasCodes.size());
  }


  @Test
  public void cannotHandleDimension32() {
    int n = 32;
    Set<Integer> hasCodes = new HashSet<>();
    for (int rowIndex = 0; rowIndex < n; rowIndex++) {
      for (int colIndex = 0; colIndex < n; colIndex++) {
        hasCodes.add(new CellAddress(rowIndex, colIndex).hashCode());
      }
    }

    assertTrue( n*n > hasCodes.size());

  }

}
