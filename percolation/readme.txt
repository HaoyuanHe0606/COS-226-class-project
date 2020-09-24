/* *****************************************************************************
 *  Name:     Haoyuan He
 *  NetID:    haoyuanh
 *  Precept:  P03A
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Operating system: Mac OS
 *  Compiler: IntelliJ
 *  Text editor / IDE: IntelliJ
 *
 *  Have you taken (part of) this course before: NO
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II: No
 *
 *  Hours to complete assignment (optional): 6 hrs
 *
 **************************************************************************** */

Programming Assignment 1: Percolation



/* *****************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates?
 **************************************************************************** */
As indicated in the presept ppt, we can set one virtual site at the top
which is linked with all the sites in the first row, and another virtual site
at the bottom which is linked to all the sites at the bottom row. So if the
bottom virtual site is in union with the top virtual site, the system percolates

/* *****************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for various values of n and T when implementing
 *  Percolation.java with QuickFindUF.java (not QuickUnionUF.java). Use a
 *  "doubling" hypothesis, where you successively increase either n or T by
 *  a constant multiplicative factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one. Do not include
 *  data points that take less than 0.25 seconds.
 **************************************************************************** */

(keep T constant)
 T = 100
 multiplicative factor (for n) = 1.2

 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
55          0.296
66          0.618               2.088           4.038
79          1.291               2.089           4.038
95          2.498               1.935           3.621
114         4.850               1.942           3.640
137         9.661               1.992           3.780
164         20.497              2.121           4.124
197         43.327              2.114           4.106

log ratio seem to converge to 4.1

(keep n constant)
 n = 100
 multiplicative factor (for T) = 2

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
10          0.308
20          0.605               1.964           0.974
40          1.107               1.830           0.872
80          2.126               1.921           0.942
160         3.991               1.877           0.908
320         7.947               1.991           0.993
640         18.661              2.348           1.231
1280        34.634              1.856           0.892

average log ratio:
0.973

/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) of
 *  PercolationStats.java as function of both n and T, such as
 *
 *       ~ 5.3*10^-8 * n^5.0 * T^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient and exponent to two significant digits.
 **************************************************************************** */

QuickFindUF running time (in seconds) as a function of n and T:

    ~* 2.07*10^-8 * n^4.1 * T^0.973
       _______________________________________
to get the factor a, substitute the index of n and T into a trial with
sufficiently large n and T.


/* *****************************************************************************
 *  Repeat the previous two questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 **************************************************************************** */

(keep T constant)
 T = 100

n times 1.5 each time
 n          time (seconds)       ratio         log ratio
--------------------------------------------------------
210         0.261
315         0.580               2.222           1.969
473         1.331               2.295           2.049
709         3.162               2.376           2.134
1063        13.826              4.373           3.639
1595        52.431              3.792           3.287

log ratio seems to converge to 3.3

(keep n constant)
 n = 100

 T          time (seconds)       ratio         log ratio
--------------------------------------------------------
500         0.259
1000        0.500               1.931          0.949
2000        0.951               1.902          0.928
4000        1.814               1.907          0.931
8000        3.492               1.925          0.945
16000       6.768               1.938          0.955
32000       13.702              2.025          1.018
68000       28.465              2.077          1.055

log ratio seems to converge to 1.05

WeightedQuickUnionUF running time (in seconds) as a function of n and T:

    ~ 6.028*10^-11 * n^3.3 * T^1.05
       _______________________________________



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
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
NA
