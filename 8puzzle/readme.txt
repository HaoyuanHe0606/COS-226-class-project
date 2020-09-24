/* *****************************************************************************
 *  Name:  Yutong Shen
 *  NetID:  yutongs
 *  Precept:  P03A
 *
 *  Partner Name:  Haoyuan He
 *  Partner NetID:  haoyuanh
 *  Partner Precept:  P03A
 *
 *  Hours to complete assignment (optional):
 *
 **************************************************************************** */

Programming Assignment 4: Slider Puzzle


/* *****************************************************************************
 *  Explain briefly how you represented the Board data type.
 **************************************************************************** */
The Board data type is basically represented as a 2-dimensional array of ints,
with index representing a specific position of tile, and the value represents
the number on the board at this position. It contains many methods for checking
the solvability and process of puzzle (isGoal, isSolvable), and optimizations
in an attempt to solve more difficult puzzles and reduce the memory footprint.
For instance, it calculates the manhattan distance in its constructor so it
is not computed many times, which will help speed the program up, same for
hamming number. Also, we considered the neighbors of the current board, and
add all neighbors into a queue, which would facilitate further computation.



/* *****************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 **************************************************************************** */
I'm only using the board and the previous search board to represent a search
node. I store the moves into an instance variable. Then it is like a linked
list.




/* *****************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 *
 *  What is the order of growth of the running time of your isSolvable()
 *  method in the worst case as function of the board size n? Use big Theta
 *  notation to simplify your answer, e.g., Theta(n log n) or Theta(n^3).
 **************************************************************************** */

Description: We firstly created a 1-D copy of the original 2-D array, and record
the position of the blank tile. Then, by creating a nested loop, we checked
whether every tile's value after a certain tile is larger than its value, if not
then increment the count of inversion by 1. After getting the final counts of
inversion, we checked whether it is odd or even. If it is even, then it should
plus the row the zerolocation at. We also checked if the size of the board is
even. Finally, we compare the size and inversion. If size is odd and inversion
is even, or even size and odd inversion, then puzzle is solvable, otherwise it's
unsolvable.


Order of growth of running time: Theta( n ^ 2  )



/* *****************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt and xx > yy.
 **************************************************************************** */


                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt     28            0.75              0.02
   puzzle30.txt     30            1.58              0.03
   puzzle32.txt     32            51.73             0.37
   puzzle34.txt     34            out of memory     0.16
   puzzle36.txt     36            out of memory     2.43
   puzzle38.txt     38            out of memory     0.68
   puzzle40.txt     40            can't solve in reasonable time   0.32
   puzzle42.txt     42            can't solve in reasonable time   5.25



/* *****************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 **************************************************************************** */

A better priority function is the most important. We can see that Manhattan is
a lot faster than hamming. It increases the performance more than 2x




/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
NA


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
NA




/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
NA


/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */
We, Yutong Shen and Haoyuan He, followed the protocol on the assignment page
to collaborate when doing this assignment. We coded and discussed both parts
of this assignments together.






/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
NA
