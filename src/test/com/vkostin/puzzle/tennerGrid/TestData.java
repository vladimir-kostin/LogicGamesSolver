package com.vkostin.puzzle.tennerGrid;

import com.vkostin.common.TestParameter;

import java.util.Arrays;
import java.util.List;

class TestData {
  final static String PUZZLE___ = String.join("\r\n"
          , "0 1 2 3 4 5 6 7 8 _"
          , "0 1 2 3 4 5 6 7 8 9"
  );

  final static String SOLUTION___ = String.join("\r\n"
          , "0 1 2 3 4 5 6 7 8 9"
          , "0 1 2 3 4 5 6 7 8 9"
  );

  final static String PUZZLE__1 = String.join("\n"
          , " _  _  _  3  7  0  4  5  1  2"
          , " 3  1  _  2  _  8  9  7  4  5"
          , " 6  7  _  _  9  2  1  3  _  _"
          , "17 17 10 10 22 10 14 15 13  7"

  );

  final static String SOLUTION__1 = String.join("\n"
          , " 8  9  6  3  7  0  4  5  1  2"
          , " 3  1  0  2  6  8  9  7  4  5"
          , " 6  7  4  5  9  2  1  3  8  0"
          , "17 17 10 10 22 10 14 15 13  7"

  );

  final static String PUZZLE__8 = String.join("\r"
          , " 3  1  _  7  6  2  _  5  9  _"
          , " 6  9  _  _  5  _  _  _  _  3"
          , " _  8  _  _  2  7  _  4  _  6"
          , " _  6  _  _  1  5  _  9  2  7"
          , "16 24 10 28 14 22  5 25 12 24"
  );

  final static String SOLUTION__8 = String.join("\r"
          , " 3  1  0  7  6  2  4  5  9  8"
          , " 6  9  2  4  5  8  0  7  1  3"
          , " 3  8  5  9  2  7  1  4  0  6"
          , " 4  6  3  8  1  5  0  9  2  7"
          , "16 24 10 28 14 22  5 25 12 24"
  );

  final static String PUZZLE_10 = String.join("\n"
          , " _  4  0  _  8  6  _  _  _  _"
          , " _  6  _  _  3  _  _  _  _  7"
          , " _  7  3  _  _  5  _  _  _  _"
          , " 9  _  0  _  3  1  _  8  _  _"
          , "25 19  8 29 16 12 23 11 23 14"
  );

  final static String SOLUTION_10 = String.join("\n"
          , " 7  4  0  9  8  6  5  1  3  2"
          , " 1  6  5  4  3  0  8  2  9  7"
          , " 8  7  3  9  2  5  4  0  6  1"
          , " 9  2  0  7  3  1  6  8  5  4"
          , "25 19  8 29 16 12 23 11 23 14"
  );

  final static String PUZZLE_14 = String.join("\n"
          , " _  6  0  4  _  _  _  _  _  7"
          , " _  1  _  _  _  _  5  _  _  _"
          , " _  _  _  _  3  0  7  8  1  5"
          , " 1  _  7  9  8  _  6  3  0  _"
          , " 9  _  _  _  _  7  _  1  _  _"
          , "24 25 15 30 25 19 22 21 21 23"
          );

  final static String SOLUTION_14 = String.join("\n"
          , " 5  6  0  4  3  1  2  9  8  7"
          , " 7  1  2  9  8  6  5  0  4  3"
          , " 2  9  6  4  3  0  7  8  1  5"
          , " 1  4  7  9  8  5  6  3  0  2"
          , " 9  5  0  4  3  7  2  1  8  6"
          , "24 25 15 30 25 19 22 21 21 23"
  );

  final static String PUZZLE_19 = String.join("\n"
          , " 5  _  _  _  _  0  _  _  4  _"
          , " _  _  1  _  9  _  4  _  _  _"
          , " _  6  _  5  2  _  3  8  9  4"
          , " _  4  9  _  0  _  _  _  1  _"
          , " 2  6  _  _  9  5  _  7  _  _"
          , " 9  8  _  3  0  7  _  _  2  _"
          , "28 41 16 22 21 28 30 31 26 27"
  );

  final static String SOLUTION_19 = String.join("\n"
          , " 5  9  2  7  1  0  8  3  4  6"
          , " 0  8  1  3  9  7  4  6  2  5"
          , " 7  6  0  5  2  1  3  8  9  4"
          , " 5  4  9  3  0  8  6  2  1  7"
          , " 2  6  0  1  9  5  3  7  8  4"
          , " 9  8  4  3  0  7  6  5  2  1"
          , "28 41 16 22 21 28 30 31 26 27"
  );

  final static String PUZZLE_20 = String.join("\n"
          , " _  _  _  8  2  0  7  _  _  6"
          , " _  _  _  _  _  _  8  2  1  0"
          , " _  _  1  _  _  _  _  _  _  _"
          , " _  8  6  0  _  4  _  2  1  _"
          , " 6  _  7  _  3  2  5  _  _  0"
          , " _  1  0  _  9  8  _  _  5  _"
          , "18 26 23 30 33 22 39 25 31 23"
  );

  final static String SOLUTION_20 = String.join("\n"
          , " 1  3  4  8  2  0  7  5  9  6"
          , " 4  7  5  9  3  6  8  2  1  0"
          , " 0  3  1  8  7  2  9  5  6  4"
          , " 5  8  6  0  9  4  3  2  1  7"
          , " 6  4  7  1  3  2  5  8  9  0"
          , " 2  1  0  4  9  8  7  3  5  6"
          , "18 26 23 30 33 22 39 25 31 23"
  );

  final static List<TestParameter> QUICKLY_SOLVABLE_TEST_DATA = Arrays.asList(
          new TestParameter(0, PUZZLE___, SOLUTION___)
          , new TestParameter( 1, PUZZLE__1, SOLUTION__1)
          , new TestParameter( 8, PUZZLE__8, SOLUTION__8)
          , new TestParameter( 10, PUZZLE_10, SOLUTION_10)
  );

  final static List<TestParameter> ALL_TEST_DATA = Arrays.asList(
          new TestParameter(0, PUZZLE___, SOLUTION___)
          , new TestParameter( 1, PUZZLE__1, SOLUTION__1)
          , new TestParameter( 8, PUZZLE__8, SOLUTION__8)
          , new TestParameter( 10, PUZZLE_10, SOLUTION_10)
          , new TestParameter( 14, PUZZLE_14, SOLUTION_14)
          , new TestParameter( 19, PUZZLE_19, SOLUTION_19)
          , new TestParameter( 20, PUZZLE_20, SOLUTION_20)
  );

}
