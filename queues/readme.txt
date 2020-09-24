/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Hours to complete assignment (optional): 4 hrs
 *
 **************************************************************************** */

Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
I used an array of items, because array indexes are known, so I can used
StdRandom to deque an item from a random position. And it is also easy to change
the memory.

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  __32n___  bytes

Deque:              ~  ___48n__  bytes
Deque: 16 object overhead
8  reference
8 * 3 node reference
48n in total

Randomized Queue:
8*4n if array size is 4n
32n in total



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

I discussed concepts with Yutong Shen in my precept.


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
