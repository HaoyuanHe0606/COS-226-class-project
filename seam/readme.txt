/* *****************************************************************************
 *  Name:  Yutong Shen
 *  NetID: yutongs
 *  Precept:  P03A
 *
 *  Partner Name:    Haoyuan He
 *  Partner NetID:   haoyuanh
 *  Partner Precept: P03A
 *
 *  Hours to complete assignment (optional): 5h
 *
 **************************************************************************** */

Programming Assignment 7: Seam Carving


/* *****************************************************************************
 *  Describe concisely your algorithm to find a horizontal (or vertical)
 *  seam.
 **************************************************************************** */
  The solution is to implement the shortest-paths algorithm
  for an edge-weighted DAG. For find vertical seam, we firstly
  initialize the edge,dist and energy arrays(they are 1d array with length
  height*width), then for every index, we firstly set their edgeto to be -1 and
  distance(weight) to be positive infinity. (not the top row pixel since it is
  the starting point). The weight in dist[]for top row pixel would be their
  energy. Then for pixels under the first row. we compare the distance from
  the distance to the pixel above it(left, right, mid), to the current pixel.
  We relax an edge if the dist[toPixel] is bigger than dist[previousPixel] +
  energy[toPixel], and this edge is recorded in edge[toPixel] = previousPixel.
  For horizontal seam, we transpose the picture, do the
  find vertical seam, then transpose again. To return the shortest path. We
  firstly get the pixel passed through in edge array in reverse order, then
  flip it to get the right order.

/* *****************************************************************************
 *  Describe what makes an image suitable to the seam-carving approach
 *  (in terms of preserving the content and structure of the original
 *  image, without introducing visual artifacts). Describe an image that
 *  would not work well.
 **************************************************************************** */
An image with distinct areas of background, foregound, middleground, ect, are
suitable to the seam-carving, and each area may have the same color
 (they have the same color cross over a vertical/ horizontal line so that work
 best for removing seams)


/* *****************************************************************************
 *  Perform computational experiments to estimate the running time to reduce
 *  a W-by-H image by one column and one row (i.e., one call each to
 *  findVerticalSeam(), removeVerticalSeam(), findHorizontalSeam(), and
 *  removeHorizontalSeam()). Use a "doubling" hypothesis, where you
 *  successively increase either W or H by a constant multiplicative
 *  factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one.
 **************************************************************************** */

(keep W constant)
 W = 1000
 multiplicative factor (for H) = 2

 H           time (seconds)      ratio       log ratio
------------------------------------------------------
500         0.24
1000        0.492               2.05
2000        0.575               1.17        4.57
4000        0.997               1.73        3.49
8000        1.759               1.76        1.03
16000       3.541               2.01        1.23
32000       7.512               2.12        1.08
64000       15.883              2.11        0.99
128000      38.59               2.42        1.18


(keep H constant)
 H = 1000
 multiplicative factor (for W) = 2

 W           time (seconds)      ratio       log ratio
------------------------------------------------------
500         0.234
1000        0.487               2.08
2000        0.619               1.27        0.33
4000        1.004               1.61        1.99
8000        1.857               1.857       1.30
16000       3.667               1.97        1.10
32000       7.123               1.94        0.98
64000       15.213              2.13        1.14
128000      31.29               2.05        0.95



/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) as a function
 *  of both W and H, such as
 *
 *       ~ 5.3*10^-8 * W^5.1 * H^1.5
 *
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient and exponent to two significant digits.
 **************************************************************************** */


Running time (in seconds) to find and remove one horizontal seam and one
vertical seam, as a function of both W and H:


    ~ 1.4*10^-8 * W^0.95 * H^1.18
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
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */
  We followed the protocol as described on the assignment page while doing
  this project. We coded together during the collaboration. We discussed the
  assignment via video calls. Haoyuan He uploaded the java program and I
  uploaded the readme text.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
  NA
