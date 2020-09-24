/* *****************************************************************************
 *  Name:    Yutong Shen
 *  NetID:   yutongs
 *  Precept: P03A
 *
 *  Partner Name:    Haoyuan He
 *  Partner NetID:   haoyuanh
 *  Partner Precept: P03A
 *
 *  Description: A program that implement A* search to solve n-by-n slider
 * puzzles. It can also return the solutions to a puzzle and return the number
 * of moves to solve the initial board.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    // the pointer node
    private SearchNode s;
    // the stack which stores the solutions
    private final Stack<Board> stacksolution;

    // a private class that uses linked structures to deal with moves and
    // mahattan distance
    private static class SearchNode implements Comparable<SearchNode> {
        // the board
        private final Board board;
        // how many moves to get to a certen step
        private int moves;
        // the previous node
        private final SearchNode prev;

        // the constructor which help to increment moves and intiate variables
        public SearchNode(Board board, SearchNode prev) {
            this.board = board;
            this.prev = prev;
            if (prev == null)
                this.moves = 0;
            else this.moves = prev.moves + 1;

        }

        // compares the manhattan distance of two boards
        public int compareTo(SearchNode other) {
            return (this.board.hamming() + this.moves) -
                    (other.board.hamming() + other.moves);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        // corner cases
        if (initial == null)
            throw new IllegalArgumentException();
        if (!initial.isSolvable())
            throw new IllegalArgumentException();

        // initiate variables
        stacksolution = new Stack<>();
        MinPQ<SearchNode> nodes = new MinPQ<>();
        s = new SearchNode(initial, null);
        nodes.insert(s);

        // add and delete boards to the priority queue until the board is the
        // goal
        while (!s.board.isGoal()) {
            s = nodes.delMin();
            for (Board neighbors : s.board.neighbors()) {
                if (s.prev == null || !neighbors.equals(s.prev.board)) {
                    nodes.insert(new SearchNode(neighbors, s));
                }
            }
        }
    }

    // min number of moves to solve initial board
    public int moves() {
        return s.moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        SearchNode i = s;
        // inversely trace back the boards
        while (i != null) {
            stacksolution.push(i.board);
            i = i.prev;
        }
        return stacksolution;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[][] blocks = new int[n][n];
        while (!StdIn.isEmpty())
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    blocks[i][j] = StdIn.readInt();

        Board btest = new Board(blocks);
        if (btest.isSolvable()) {
            Solver stest = new Solver(btest);
            if (!btest.isGoal()) {
                StdOut.println("Minimum number of moves = " + stest.moves());
                for (Board board : stest.solution())
                    StdOut.println(board);
            }
            else StdOut.println(btest);
        }
        else {
            StdOut.println(btest);
            StdOut.println("Unsolvable puzzle");
        }
    }
}
