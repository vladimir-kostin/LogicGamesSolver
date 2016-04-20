package com.vkostin.lineSweeper;

class TestData {
  final static String PUZZLE___ = String.join("\n"
          , "3 _ _ _ 3"
          , "_ _ _ _ _"
          , "_ _ _ _ _"
          , "_ _ _ _ _"
          , "3 _ _ _ 3"
  );

  final static String SOLUTION___ = String.join("\r"
          , "3 F - T 3"
          , "F J _ L T"
          , "| _ _ _ |"
          , "L T _ F J"
          , "3 L - J 3"
  );

  final static String PUZZLE__1 = String.join("\r\n"
          , "_ 3 _ _ 3 _"
          , "_ _ _ _ _ _"
          , "_ _ _ _ _ _"
          , "5 _ 3 _ _ 5"
          , "_ _ _ 5 _ _"
          , "_ _ _ _ _ _"
  );

  final static String SOLUTION__1 = String.join("\n\r"
          , "_ 3 _ _ 3 _"
          , "F - - - - T"
          , "L T _ _ F J"
          , "5 | 3 _ | 5"
          , "F J _ 5 L T"
          , "L - - - - J"
  );

  final static String PUZZLE__3 = String.join("\n"
          , "_ 3 _ 3 _ 4 _ _"
          , "_ _ _ _ _ _ _ _"
          , "_ 8 _ _ _ 6 _ _"
          , "_ _ _ 7 _ _ _ _"
          , "_ _ _ 6 _ _ _ _"
          , "_ _ _ 7 _ _ 8 _"
          , "_ 8 _ _ _ _ _ _"
          , "_ _ _ _ _ _ _ 3"
  );

  final static String SOLUTION__3 = String.join("\n"
          , "_ 3 _ 3 _ 4 F T"
          , "F - - - - - J |"
          , "| 8 F - T 6 _ |"
          , "L T | 7 | _ F J"
          , "_ | | 6 L T L T"
          , "F J | 7 F J 8 |"
          , "| 8 L T L T F J"
          , "L - - J _ L J 3"
  );

  final static String PUZZLE_23 = String.join("\n"
          , "_ _ _ _ _ _ _ _"
          , "_ _ 7 _ _ 6 _ 4"
          , "_ _ _ _ _ _ _ _"
          , "_ 7 _ _ _ 3 _ _"
          , "_ _ _ _ _ _ _ _"
          , "_ _ 4 _ _ _ _ _"
          , "3 _ _ _ _ _ 7 _"
          , "_ _ _ _ _ _ _ _"
  );

  final static String SOLUTION_23 = String.join("\n"
          , "F T _ F - - T _"
          , "| | 7 | _ 6 | 4"
          , "| L T L T _ L T"
          , "| 7 L - J 3 _ |"
          , "L T _ _ _ _ _ |"
          , "_ | 4 _ F T _ |"
          , "3 | _ F J | 7 |"
          , "_ L - J _ L - J"
  );

  final static String PUZZLE_38 = String.join("\n"
          , "_ _ 5 _ _ 5 _ _ _ _"
          , "_ _ _ _ _ _ _ _ _ _"
          , "_ _ 5 _ 5 _ _ _ _ 5"
          , "_ _ _ _ _ _ _ 6 _ _"
          , "_ _ 4 5 _ 8 _ _ 7 _"
          , "_ 7 _ _ _ _ _ _ _ _"
          , "_ _ _ _ _ 8 _ 8 _ _"
          , "4 7 _ _ _ _ _ _ _ _"
          , "_ _ _ 8 _ _ _ _ 8 _"
          , "_ _ _ _ _ 5 _ _ _ _"
  );

  final static String SOLUTION_38 = String.join("\n"
          , "F T 5 F T 5 F - - T"
          , "| L - J L - J F - J"
          , "L T 5 _ 5 _ _ L T 5"
          , "_ | _ _ F - T 6 L T"
          , "F J 4 5 | 8 L T 7 |"
          , "| 7 F T L - T L T |"
          , "L - J L T 8 | 8 | |"
          , "4 7 F - J F J F J |"
          , "F - J 8 F J F J 8 |"
          , "L - - - J 5 L - - J"
  );

}
