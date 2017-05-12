---------------------------------------
[2016-04-01]
---------------------------------------
..Summary
Fill the board with numbers 1 to 9 according to the sum

..Description
1. Your goal is to write a number in every blank tile (those without a diagonal line)
2. The numbers you see in the tiles with diagonal lines are hints in the form of sum
3. The small number in the top right corner tells you the sum of the digits to its right.
4. The small number in the bottom left corner tells you the sum of the digits under it.
5. You can enter digits ranging from 1 to 9. No same number should appear in a row or column.
6. In some instances, in bigger puzzles, two separate lines of contiguous digits will be on the same row, however they will be separated by a diagonal-line tile.
7. In this case the above rule doesn't apply for the entire row: i.e. you could have the same digit on the left group of tiles and the right one.
8. The same applies for columns.
9. Tiles which have a diagonal line without numbers aren't used in the game.

---------------------------------------
[2016-03-13]
---------------------------------------
Q: how to provide to solver a kakuro puzzle (via text file) ?
- First idea:
'space' : is used as a separator between cells (any number of 'space' are allowed to be used)
'_' : will indicate cell MUST be solved
'\' : indicates cell must NOT be solved
numbers are allowed to be on both sides of '\'
'\x' : indicates cells on right side must be solved
'x\' : indicates cells below must be solved
'x\x' : cells below must be solved as well as cells on right side

validation checks:
- in every row equal amount of cells


simple example:
\  4\  9\  \   \
\4 _   _   21\ \
\7 _   _   _   16\
\  \23 _   _   _
\  \   \16 _   _



Q: how solver would store a puzzle (in memory)?
- Two dimensional array of cells will be used.
=======================================
