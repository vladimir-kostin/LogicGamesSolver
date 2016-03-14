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

}
