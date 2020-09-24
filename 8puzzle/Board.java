/* *****************************************************************************
 *  Name:    Yutong Shen
 *  NetID:   yutongs
 *  Precept: P03A
 *
 *  Partner Name:    Haoyuan He
 *  Partner NetID:   haoyuanh
 *  Partner Precept: P03A
 *
 *  Description: a data type that models an n-by-n board with sliding tiles.
 *               Containing methods to calculate the size of the board, its
 *               hamming and manhattan numbers, as well as an iterable type
 *               for its neighbors. Methods also include checks of whether
 *               the given board is equal to another, and whether the board
 *               is the ultimate goal or it is solvable
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private static final int BLANK = 0; // use 0 to indicate an empty tile
    private final int n; // number of tiles on each side
    private final int[][] t; // tiles constructed for operations on board
    // (not just the number)
    private int hamming; // pre compute hamming numbers
    private int manhattan; // pre compute manhattan numbers
    private int zx; // x-cord of blank location
    private int zy; // y-cord of blank location


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        // record the number of tiles on each side and copy all input tiles into
        // instance variable
        n = tiles.length;
        t = new int[tiles.length][tiles.length];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                t[row][col] = tiles[row][col];
                if (t[row][col] == BLANK) {
                    // record the blank location
                    zx = row;
                    zy = col;
                }
                else {
                    // calculate the theoretical row and col of the tile at this
                    // coordinate should be, add the horizontal and vertical
                    // distance to manhattan
                    int x = (t[row][col] - 1) / n;
                    int y = (t[row][col] - 1) % n;
                    manhattan += Math.abs(x - row) + Math.abs(y - col);

                    // comapre each value in the given tiles with the coordinate
                    // it is suppose to be, and increment hamming number if the
                    // actual coordinate doesn't equal to theoretical coordinate
                    int value = row * n + col + 1;
                    if (value != t[row][col])
                        hamming++;

                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        // construct an empty string, and firstly add the side size + "\n"
        StringBuilder result = new StringBuilder();
        result.append(n + "\n");
        // construct a nested loop to add every coordinate in the array to
        // result, each line of result only consists tiles in the same row
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                result.append(String.format("%2d ", tileAt(row, col)));
            }
            result.append("\n");
        }
        return result.toString();

    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col) {
        // corner case
        if (!(row >= 0 && row <= n - 1 && col >= 0 && col <= n - 1))
            throw new IllegalArgumentException();
        // return the position of tile in two dimensional tiles array as an
        // instance variable
        if (t[row][col] == BLANK)
            return 0;
        return t[row][col];

    }

    // board size n
    public int size() {
        // simply return the instance variable n
        return n;
    }


    // number of tiles out of place
    public int hamming() {
        return hamming;

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;

    }

    // is this board the goal board?
    public boolean isGoal() {
        // since theoretical and actual value of the tile is not
        // euqal, the board would not reach the goal
        return manhattan == 0;

    }

    // does this board equal y?
    public boolean equals(Object y) {
        // if the input object is empty, not equal
        if (y == null)
            return false;
        if (y == this)
            return true;
        if (y.getClass() != this.getClass())
            return false;
        else {
            Board y1 = (Board) y;
            if (y1.n != this.n)
                return false;

            else {
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {
                        if (y1.t[row][col] != t[row][col])
                            return false;

                    }
                }
                return true;
            }

        }

    }

    // change the position of two tiles on board
    private int[][] exch(int r, int c, int r1, int c1) {
        // create a copy of the original tiles array
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = t[i][j];
            }

        }

        // swap two tiles
        int temp = copy[r][c];
        copy[r][c] = copy[r1][c1];
        copy[r1][c1] = temp;
        return copy;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> neighbors = new Queue<Board>();
        // check the position for the up, down, left, and right
        // of the given tile

        // slide the tile up
        if (zx > 0) {
            neighbors.enqueue(new Board(exch(zx, zy, zx - 1, zy)));
        }

        // slide the tile down
        if (zx < n - 1) {
            neighbors.enqueue(new Board(exch(zx, zy, zx + 1, zy)));
        }

        // slide the tile left
        if (zy > 0) {
            neighbors.enqueue(new Board(exch(zx, zy, zx, zy - 1)));
        }

        // slide the tile right
        if (zy < n - 1) {
            neighbors.enqueue(new Board(exch(zx, zy, zx, zy + 1)));
        }

        return neighbors;
    }


    // is this board solvable?
    public boolean isSolvable() {
        // create a copy of a one-dimensional array by transfering the original
        // two-dimensional tiles array and record the blank position
        int[] copy = new int[n * n];
        int zeroLocation = 0;
        int initial = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (t[i][j] == BLANK) zeroLocation = initial;
                copy[initial++] = t[i][j];
            }
        }

        // calculating the number of inversions by creating a nested loop
        int inversions = 0;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] == BLANK)
                continue;
            // compare the value of tile each position after i in the array
            for (int j = i; j < copy.length; j++) {
                if (copy[j] < copy[i] && copy[j] != BLANK)
                    inversions++;
            }

        }

        // check if the size is even
        boolean evenTiles = ((n % 2) == 0);
        if (evenTiles) {
            // if even, then inversions plus the row number
            inversions += zeroLocation / n;
        }

        // check if the total inversions are even
        boolean evenInversions = ((inversions % 2) == 0);

        // odd tiles + even inversions (true); even tiles + even inversions
        // (false);
        return (evenTiles != evenInversions);


    }

    // unit testing (required)
    public static void main(String[] args) {
        Board testBoard = new Board(new int[][] {

                { 8, 1, 3 },

                { 4, 0, 2 },

                { 7, 6, 5 }
        });
        Board testBoard1 = new Board(new int[][] {

                { 4, 1, 7 },

                { 1, 8, 3 },

                { 0, 6, 5 }
        });

        for (Board board : testBoard.neighbors()) {
            StdOut.println(board.isGoal());
        }
        StdOut.println(testBoard.toString());
        StdOut.println(testBoard.hamming());
        StdOut.println(testBoard.manhattan());
        StdOut.println(testBoard.size());
        StdOut.println(testBoard.isSolvable());
        StdOut.println(testBoard.tileAt(0, 1));
        StdOut.println(testBoard.equals(testBoard1));

    }

}
