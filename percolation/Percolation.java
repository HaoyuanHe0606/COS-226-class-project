/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuan
 *  Precept: P03A
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description:  Model an n-by-n percolation system using the union-find
 *                data structure.
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // This is the weightedquickunion data type functioning as the n * n grid
    private final WeightedQuickUnionUF grid;
    // This is another weightedquickunion data type used to avoid backwash
    private final WeightedQuickUnionUF grid2;
    // This is a boolean array to judge where a site is open or not
    private boolean[] openjudge;
    // this int is the "n" number in the n * n grid
    private final int total;
    // count the total open sites
    private int totalopen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // throw exception at the corner case
        if (n <= 0)
            throw new IllegalArgumentException();
        // give the instance variable the value of n
        total = n;
        // set the openjudge to the same number as grids (virtural grids
        // included)
        openjudge = new boolean[total * total + 1];
        grid = new WeightedQuickUnionUF(n * n + 2);
        grid2 = new WeightedQuickUnionUF(n * n + 1);
        totalopen = 0;
    }

    // a private method that helps to locate the current site from its row
    // and col
    private int gridsite(int row, int col) {
        int site = row * total + col + 1;
        return site;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // throw exceptions in corner cases
        if (row < 0 || row >= total)
            throw new IllegalArgumentException();
        if (col < 0 || col >= total)
            throw new IllegalArgumentException();
        // the current variable is the site at the indicated row and col
        int current = gridsite(row, col);
        // open the site and connect it to any adjcent open site
        if (!openjudge[current]) {
            openjudge[current] = true;
            totalopen++;
            // if on the top row, connect to the upper virtual site
            if (row == 0) {
                grid.union(current, 0);
                grid2.union(current, 0);
            }
            // if on the bottom row, connect to the lower virtual site
            // grid 2 should not be connected to the lower virtual site
            if (row == total - 1)
                grid.union(current, total * total + 1);
            // connect the left if not in the first col
            if (col != 0 && openjudge[current - 1]) {
                grid.union(current, current - 1);
                grid2.union(current, current - 1);
            }
            // connect right if not in the rightmost col
            if (col != total - 1 && openjudge[current + 1]) {
                grid.union(current, current + 1);
                grid2.union(current, current + 1);
            }
            // connect up if not in the top row
            if (row != 0 && openjudge[current - total]) {
                grid.union(current, current - total);
                grid2.union(current, current - total);
            }
            // connect down if not in the bottom row
            if (row != total - 1 && openjudge[current + total]) {
                grid.union(current, current + total);
                grid2.union(current, current + total);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // throw exceptions in corner cases
        if (row < 0 || row >= total)
            throw new IllegalArgumentException();
        if (col < 0 || col >= total)
            throw new IllegalArgumentException();
        // return if it is open
        return openjudge[gridsite(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // throw exceptions in corner cases
        if (row < 0 || row >= total)
            throw new IllegalArgumentException();
        if (col < 0 || col >= total)
            throw new IllegalArgumentException();
        // return if this site is directly connected to the top virtual site
        return (grid2.find(gridsite(row, col)) == grid2.find(0));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return totalopen;
    }

    // does the system percolate?
    public boolean percolates() {
        // check if the top virtual site is connected to the bottom virtual
        // site
        return (grid.find(total * total + 1) == grid.find(0));
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation test = new Percolation(10);
        test.open(2, 3);
        System.out.println(test.isOpen(0, 3));
        System.out.println(test.isOpen(2, 4));
        System.out.println(test.isFull(0, 0));
        System.out.println(test.isFull(2, 2));
        System.out.println(test.numberOfOpenSites());
        System.out.println(test.percolates());
    }
}

