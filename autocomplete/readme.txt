/* *****************************************************************************
 *  Name:  Yutong Shen
 *  NetID:  yutongs
 *  Precept:  P03A
 *
 *  Partner Name:  Haoyuan He
 *  Partner NetID:  haoyuanh
 *  Partner Precept:   P03A
 *
 *  Hours to complete assignment (optional): 6h
 *
 **************************************************************************** */

Programming Assignment 3: Autocomplete


/* *****************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  to find the first index of a key that equals the search key.
 **************************************************************************** */
 It is similar to the normal binary search algorithm except that when we find
 the index equaling the key, we don't stop, but decrement the hi pointer
 excecute the method recursively until we find the lowest index equalling the
 key.

/* *****************************************************************************
 *  Identify which sorting algorithm (if any) that your program uses in the
 *  Autocomplete constructor and instance methods. Choose from the following
 *  options:
 *
 *    none, selection sort, insertion sort, mergesort, quicksort, heapsort
 *
 *  If you are using an optimized implementation, such as Arrays.sort(),
 *  select the principal algorithm.
 **************************************************************************** */

Autocomplete() : merge sort // the array sorted is a array containing objects
                           //Term that implement Comparable and use a Comparator

allMatches() : merge sort // the array sorted is a array containing objects
                         // Term that implement Comparable and use a Comparator

numberOfMatches() : none // it is not necessary to sort the array since it is
                         // already sorted in constructor

/* *****************************************************************************
 *  How many compares (in the worst case) does each of the operations in the
 *  Autocomplete data type make, as a function of both the number of terms n
 *  and the number of matching terms m? Use Big Theta notation to simplify
 *  your answers.
 *
 *  Recall that with Big Theta notation, you should discard both the
 *  leading coefficients and lower-order terms, e.g., Theta(m^2 + m log n).
 **************************************************************************** */

Autocomplete(): Theta(nlogn) // merge sort an array of n elements

allMatches(): Theta(logn + mlogm) // first use binary search to find the first
                                  // index and last, which causes logn compares,
                                  // and to merge sort the array of m matching
                                  // objects requires mlogm compares

numberOfMatches(): Theta(logn) // the only thing we need to compare is to use
                               // binary search to find the first and last
                               // index.





/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 **************************************************************************** */
I collaborate with Haoyuan He for this assignment. We coded together when doing
the assignment. For Term and BinarySearchDeluxue, we worked on Haoyuan's
computer, and for AutoComplete and Readme, we worked on my computer. Therefore,
the uploader for these four files is not the same person.

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
NA

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */
NA



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
 NA
