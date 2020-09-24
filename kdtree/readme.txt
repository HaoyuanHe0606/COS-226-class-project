/* *****************************************************************************
 *  Name:     Haoyuan He
 *  NetID:    haoyuanh
 *  Precept:  P03A
 *
 *  Partner Name:       Yutong Shen
 *  Partner NetID:      yutongs
 *  Partner Precept:    P03A
 *
 *  Hours to complete assignment (optional): 8hrs
 *
 **************************************************************************** */

Programming Assignment 5: Kd-Trees


/* *****************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **************************************************************************** */
We have the instance variables of the value associated with the node, its 2d
point, its rectangle, its left subtree, its right sub tree, and its level in
the kdtree (the root has the level 0, even levels are vertical, and odd levels
are horizontal).
/* *****************************************************************************
 *  Describe your method for range search in a kd-tree.
 **************************************************************************** */
We use a private method to check if the node is inside the searching rectangle,
if it is inside, then we enqueue the queue of internals. In this method, we
recusursively check if the researching rectangle intersects with the rectangle
associated with the given node, if it is, then we check if the node is inside
the researching rectangle. If the node satisfies both condition, then we enqueue
it into queue of internals. This searching method recursively search nodes on
both the left and right subtrees of the kdtree. Then, in public method, we
implement the private method to search the nodes inside researching rectangle
starting from null.

/* *****************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **************************************************************************** */
We follows the description in the checklist, that if the closest point
discovered so far is closer than the distance between the query point
and the rectangle corresponding to a node, there is no need to explore
that node (or its subtrees) (the prune rule), and we recursively go to the
subtree that is on the same side of the splitting line as the query point.

/* *****************************************************************************
 *  How many nearest-neighbor calculations can your PointST implementation
 *  perform per second for input1M.txt (1 million points), where the query
 *  points are random points in the unit square?
 *
 *  Fill in the table below, using one digit after the decimal point
 *  for each entry. Use at least 1 second of CPU time.
 *  (Do not count the time to read the points or to build the 2d-tree.)
 *
 *  Repeat the same question but with your KdTreeST implementation.
 *
 **************************************************************************** */


                 # calls to         /   CPU time     =   # calls to nearest()
                 client nearest()       (seconds)        per second
                ------------------------------------------------------
PointST: 6070 / 0.099s = 61313 /s

KdTreeST: 729 / 0.002s = 364500 / s

Note: more calls per second indicates better performance.

We add an instance variable to both programs that can record the number of calls
/ calculations of nearest neighbor. Then we use Stopwatch to record the time
elapsed when executing the nearest neighbor search. Divide them we get the
calls per second.

/* *****************************************************************************
 *  Did you fill out the mid-semester feedback form?
 *  If not, please do so now: https://forms.gle/72SRLDPmR49NJDM66
 **************************************************************************** */
Yes

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
We followed the protocol as described on the assignment page.
Haoyuan and Yutong discuss algorithms and work together throughout the
assignment.



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on  how helpful the class meeting was and on how much you learned
 * from doing the assignment, and whether you enjoyed doing it.
 **************************************************************************** */
NA
