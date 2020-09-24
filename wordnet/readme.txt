/* *****************************************************************************
 *  Name:   Yutong Shen
 *  NetID:  yutongs
 *  Precept:  P03A
 *
 *  Partner Name:  Haoyuan He
 *  Partner NetID:  haoyuanh
 *  Partner Precept:  P03A
 *
 *  Hours to complete assignment (optional):  5h
 *
 **************************************************************************** */

Programming Assignment 6: WordNet


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in synsets.txt. Why did you make this choice?
 **************************************************************************** */
  We use hash map to store the information in synsets.txt, with integer type as
  its key and string type as its value. The integer type (key) could represent
  the id of each synset and string represents its contents. We use Hash table
  to save more memory and let the program to be more efficient than using BST.


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in hypernyms.txt. Why did you make this choice?
 **************************************************************************** */
  We use the digraph to store information in hypernyms.txt. When reading from
  the input, we first transfer every elements into an array. Then with id as
  its one endpoint, evey hypernym associates with the id as another endpoint,
  we construct edges and add the edges to the digraph. Therefore, we could
  construct a diagraph(DAG) with hypernyms.


/* *****************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithm? Express your answer as a function of the
 *  number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.)
 **************************************************************************** */

Description:
 We use directedCycle data type to check through the input digraph. It use
 depth-first search algorithm. To check if the digraph contains cycle, we
 use the hasCycle method of this data type to detect cycles. To find the
 number of roots in a digraph, we calculate the number of vertex that has
 0 directed edges incident from the given vertex. If the total root number
 is bigger than 1, then the digraph is not a DAG.



Order of growth of running time: O(V + E)
         - creates a directedcycle object: V + E
         - runs through to check cycle: V + E
         - cycles through all vertices in the graph to check roots: V
         - total: 3V + 2E


/* *****************************************************************************
 *  Describe concisely your algorithm to compute the shortest common ancestor
 *  in ShortestCommonAncestor. For each method, give the order of growth of
 *  the best- and worst-case running times. Express your answers as functions
 *  of the number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.)
 *
 *  If you use hashing, assume the uniform hashing assumption so that put()
 *  and get() take constant time per operation.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't forget
 *  to count the time needed to initialize the marked[], edgeTo[], and
 *  distTo[] arrays.
 **************************************************************************** */

Description:
For length and ancestor of two vertices, we write a private method as helper
to taken two BreadthFirstDirectedPaths data types(digraph, and vertex). Then,
we go through every vertex in the digraph to check if the two inputs have path
to the vertex. If path exists, calculate the distance of ancestral path. If it
is the shortest,then update the shortest result with the current distance and
the ancestor as the current vertex with the shortest distance.

For length and ancestor of two subsets, the algorithm is quite similar,
instead, the private method takes in the BreadthFirstDirectedPaths data type
with digraph and the iterable subsets. Then we also go through the loop to
find the shortest distance and ancestor.


                                 running time
method                  best case            worst case
--------------------------------------------------------
length()                 V + E               V + E

ancestor()               V + E               V + E

lengthSubset()           V + E               V + E

ancestorSubset()         V + E               V + E

Note:
BreadthFirstDirectedPath(* 2): 2(V + E)
Iterate to find ancestor: constant         V
Total:                   2V + 2E             3V + 2E



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
We assert that we follow the protocol as described on the assignment page. We
coded this assignment together using video calls and debugged together. Two of
the projects are coded on Haoyuan's computer and two are on mine. Then we
uploaded the projects separately.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
NA
