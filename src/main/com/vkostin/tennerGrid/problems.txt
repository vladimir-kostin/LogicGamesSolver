------------
[2016-04-01]
------------
..Summary
Counting up to 10

..Description
1. Your goal is to enter every digit, from 0 to 9, in each row of the Grid.
2. The number on the bottom row gives you the sum from that column.
3. Digits can repeat on the same column, however digits in contiguous tiles must be different, even diagonally. Obviously digits can't repeat on the same row.

------------
[2016-03-29]
------------
Q: how to provide to solver a tenner-grid puzzle (via text file) ?
'space' : is used as a separator between cells (any number of 'space' are allowed to be used)
'_' : will indicate cell MUST be solved
'x' : will indicate cell is solved already and is a part of puzzle ('0' is allowed value)
last row: will have sum of values above

simple example:
 _  _  _  3  7  0  4  5  1  2
 3  1  _  2  _  8  9  7  4  5
 6  7  _  _  9  2  1  3  _  _
17 17 10 10 22 10 14 15 13  7
