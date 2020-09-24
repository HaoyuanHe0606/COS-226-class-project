/* *****************************************************************************
 *  Name:    Yutong Shen
 *  NetID:   yutongs
 *  Precept: P03A
 *
 *  Partner Name:    Haoyuan He
 *  Partner NetID:   haoyuanh
 *  Partner Precept: P03A
 *
 *  Description:  The program implements a data type that provides autocomplete
 *                functionality for a given set of string and weights, using
 *                Term and BinarySearchDeluxe.
 *                It sorts the terms in lexicographic order; use binary search
 *                to find the all query strings that start with a given prefix;
 *                and sort the matching terms in descending order by weight.
 *                # corner case: Throw an IllegalArgumentException in the
 *                 constructor if either its argument array is null or any
 *                 entry is null. Throw an IllegalArgumentException in
 *                 allMatches() and numberOfMatches() if its argument is null.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Autocomplete {
    private final Term[] lists; // instance variable of input array of terms

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        // cornor case
        if (terms == null)
            throw new IllegalArgumentException();
        // initialize the Term array, lists
        lists = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null)
                throw new IllegalArgumentException();
            // let every element of the array instance variable
            // equals to the input
            lists[i] = terms[i];
        }
        // The constructor must make O(nlogn) compares
        Arrays.sort(lists);

    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        // corner case
        if (prefix == null)
            throw new IllegalArgumentException();
        // if the length of prefix is 0, then just return the original array
        // sorted by reverse weight order
        int n = prefix.length();
        if (n == 0) {
            Term[] all = new Term[lists.length];
            for (int i = 0; i < lists.length; i++)
                all[i] = lists[i];
            Arrays.sort(all, Term.byReverseWeightOrder());
            return all;
        }

        // find the first and last index of lists using the given key
        // the key is the first char of prefix
        Term key = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(lists, key,
                                                    Term.byPrefixOrder(n));
        int last = BinarySearchDeluxe.lastIndexOf(lists, key,
                                                  Term.byPrefixOrder(n));
        // if there is no such key, return an empty array
        if (first == -1)
            return new Term[0];

        // else, create an array with length of last-first+1 and
        // load in elements from lists to matches
        Term[] matches = new Term[last - first + 1];
        for (int i = first; i <= last; i++)
            matches[i - first] = lists[i];

        // sort the result array by reverse weight order
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;


    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        // corner case
        if (prefix == null)
            throw new IllegalArgumentException();
        // same method as allMatches, find the first and last index using key
        int n = prefix.length();
        Term key = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(lists, key,
                                                    Term.byPrefixOrder(n));
        int last = BinarySearchDeluxe.lastIndexOf(lists, key,
                                                  Term.byPrefixOrder(n));
        // return zero is there is no matching term
        if (first == -1)
            return 0;
        // calculate the number of matching elements
        int result = last - first + 1;
        return result;


    }

    // unit testing (required)
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }

}


