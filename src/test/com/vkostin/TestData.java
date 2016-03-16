package com.vkostin;

class TestData {
  final static String PUZZLE_3_X_4 = String.join("\n"
          ,"\\  3\\ 4\\ 6\\"
          ,"\\6 _   _   _"
          ,"\\7 _   _   _"
          );

  final  static  String SOLUTION_3_X_4 = String.join("\r"
          ,"\\  3\\ 4\\ 6\\"
          ,"\\6 1   3   2"
          ,"\\7 2   1   4"
  );

  final static String PUZZLE_5_X_5 = String.join("\r\n"
          , "\\  4\\  9\\  \\   \\"
          , "\\4 _    _    21\\ \\"
          , "\\7 _    _    _    16\\"
          , "\\  \\23 _    _    _"
          , "\\  \\   \\16 _    _"
  );

  final static String SOLUTION_5_X_5 = String.join("\n\r"
          , "\\  4\\  9\\  \\   \\"
          , "\\4 3    1    21\\ \\"
          , "\\7 1    2    4    16\\"
          , "\\  \\23 6    8    9"
          , "\\  \\   \\16 9    7"
  );

  final static String PUZZLE_6_X_6 = String.join("\n"
          , "\\    \\  21\\  10\\     \\  \\"
          , "\\   8\\8  _     _     16\\ 4\\"
          , "\\22 _     _     _      _   _"
          , "\\13 _     _     5\\10  _   _"
          , "\\    \\    \\7  _      _    \\"
          , "\\    \\    \\4  _      _    \\"
  );

  final static String SOLUTION_6_X_6 = String.join("\n"
          , "\\    \\  21\\  10\\     \\  \\"
          , "\\   8\\8  5     3     16\\ 4\\"
          , "\\22 2     9     7      3   1"
          , "\\13 6     7     5\\10  7   3"
          , "\\    \\    \\7  2      5    \\"
          , "\\    \\    \\4  3      1    \\"
  );

  final static String PUZZLE_7_X_7 = String.join("\n"
          , "\\  5\\ 13\\   \\   \\  16\\ 8\\"
          , "\\5  _   _   15\\ 11\\16 _   _"
          , "28\\ _   _    _    _     _   _"
          , "\\    \\  \\4 _    _      \\  \\"
          , "\\   4\\ 3\\3 _    _     9\\ 4\\"
          , "\\24 _   _    _    _     _   _"
          , "\\4  _   _     \\   \\4  _   _"
  );

  final static String SOLUTION_7_X_7 = String.join("\n"
          , "\\  5\\ 13\\   \\   \\  16\\ 8\\"
          , "\\5  1   4   15\\ 11\\16 9   7"
          , "28\\ 4   9    5    2     7   1"
          , "\\    \\  \\4 1    3      \\  \\"
          , "\\   4\\ 3\\3 2    1     9\\ 4\\"
          , "\\24 1   2    7    5     6   3"
          , "\\4  3   1     \\   \\4  3   1"
  );

  final static String PUZZLE_8_X_8 = String.join("\n"
          , "\\    \\  41\\ 6\\    \\    \\  33\\ 7\\"
          , "\\    \\14 _   _    11\\  16\\11 _   _"
          , "\\  14\\26 _   _     _     _     _   _"
          , "\\7  _     _    \\13 _     _     _   9\\"
          , "\\15 _     _   7\\  14\\    \\7  _   _"
          , "\\   8\\22 _   _     _    15\\10 _   _"
          , "\\37 _     _   _     _     _     _    \\"
          , "\\7  _     _    \\    \\12 _     _    \\"
  );

  final static String SOLUTION_8_X_8 = String.join("\n"
          , "\\    \\  41\\ 6\\    \\    \\  33\\ 7\\"
          , "\\    \\14 9   5    11\\  16\\11 6   5"
          , "\\  14\\26 4   1     7     9     3   2"
          , "\\7  5     2    \\13 4     7     2   9\\"
          , "\\15 9     6   7\\  14\\    \\7  1   6"
          , "\\   8\\22 8   5     9    15\\10 7   3"
          , "\\37 6     7   2     5     8     9    \\"
          , "\\7  2     5    \\    \\12 7     5    \\"
  );

}
