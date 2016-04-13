------------
[2016-04-13]
------------
..Summary
Draw a single loop, minesweeper style

..Description
1. Similar to Slitherlink, the goal is to draw a single closed loop never crosses itself of branches off.
2. A number in a cell denotes how many of the 8 adjacent cells are passed by the loop.
3. The loop can only go horizontally or vertically between cells, but not over the numbers.
4. The loop doesn't need to cover all the board.

------------
Q: how to provide to solver a line-sweeper puzzle (via text file) ?
'space' : is used as a separator between cells (any number of 'space' are allowed to be used)
'_' : will indicate an empty cell (which might get line fragment)
'x' : will indicate a number in cell (line will NOT go over this cell)

simple example:
2 _ _ 2
_ _ _ _
_ _ _ _
2 _ _ 2

2 _ _ _ 2
_ _ _ _ _
_ _ _ _ _
_ _ _ _ _
2 _ _ _ 2

Q: how to provide an answer to this puzzle (via text file) ?
ascii-symbols is bad idea --> since display will depend on proper font
2╔═╗2
╔╝_╚╗
║___║
╚╗_╔╝
2╚═╝2

186 ║
187 ╗
188 ╝
200 ╚
201 ╔
205 ═
