/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Description: This program does Monte Carlo simulation to estimate
 * the percolation threshold.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    // This is an int array storing the number of open sites in each trial
    private final int[] opensites;
    // this is the n value of the grid
    private final int scale;
    // this is the number of trials
    private final int t;
    // this is the constant 1.96 in the calculation
    private final double statsnum;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        // throw an exception when encountering corner cases
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        // give values to the instance variables
        scale = n;
        t = trials;
        opensites = new int[trials];
        statsnum = 1.96;
        // perform the trials n times
        for (int k = 0; k < trials; k++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                int i = StdRandom.uniform(n);
                int j = StdRandom.uniform(n);
                test.open(i, j);
            }
            opensites[k] = test.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return (StdStats.mean(opensites) / (scale * scale));
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return (StdStats.stddev(opensites) / (scale * scale));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean() - (statsnum * stddev() / Math.sqrt(t)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + (statsnum * stddev() / Math.sqrt(t)));
    }

    // test client (see below)
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);          // number of vertices
        int trials = Integer.parseInt(args[1]);     // number of trials

        // record the elapsed time
        Stopwatch timer = new Stopwatch();
        PercolationStats test = new PercolationStats(n, trials);
        double time = timer.elapsedTime();

        // report statistics
        StdOut.printf("mean()           = " + "%.6f", test.mean());
        StdOut.println();
        StdOut.printf("stddev()         = " + "%.6f", test.stddev());
        StdOut.println();
        StdOut.printf("confidenceLow()  = " + "%.6f", test.confidenceLow());
        StdOut.println();
        StdOut.printf("confidenceHigh() = " + "%.6f", test.confidenceHigh());
        StdOut.println();
        StdOut.printf("elapsed time     = " + "%.3f", time);
    }
}
