package com.vkostin.tennerGrid;

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

  final static String PUZZLE_14 = String.join("\n"
          , " _  6  0  4  _  _  _  _  _  7"
          , " _  1  _  _  _  _  5  _  _  _"
          , " _  _  _  _  3  0  7  8  1  5"
          , " 1  _  7  9  8  _  6  3  0  _"
          , " 9  _  _  _  _  7  _  1  _  _"
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

}
