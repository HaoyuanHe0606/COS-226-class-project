/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Partner Name:    Yutong Shen
 *  Partner NetID:   yutongs
 *  Partner Precept: P03A
 *
 *  Description:  It  represents an autocomplete term—a query string and an
 * associated integer weight. It supports comparing terms by three different
 * orders: lexicographic order by query string (the natural order); in
 * descending order by weight (an alternate order); and lexicographic
 * order by query string but using only the first r characters (a family
 * of alternate orderings).
 **************************************************************************** */

import java.util.Comparator;

public class Term implements Comparable<Term> {
    // the query string
    private final String query;
    // an integer weight
    private final long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null)
            throw new IllegalArgumentException();
        if (weight < 0)
            throw new IllegalArgumentException();
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // an inner class to compare by reverse weight order
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term a, Term b) {
            return Long.compare(b.weight, a.weight);
        }

    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new PrefixOrder(r);
    }

    // an inner class to compare by prefix order
    private static class PrefixOrder implements Comparator<Term> {
        // an instance variable r
        private final int r;

        // the constructer in this class
        public PrefixOrder(int r) {
            if (r < 0)
                throw new IllegalArgumentException();
            this.r = r;
        }

        // a compare method to realize commapre by prefix order
        public int compare(Term a, Term b) {
            // pointer in a
            int i = 0;
            // the recorded value of compareTo
            int record = 0;
            // length of a
            int la = a.query.length();
            // length of b
            int lb = b.query.length();

            while (record == 0 && i < r) {
                // when a, b are the same, return 0;
                if (i == la && i == lb)
                    return 0;

                // when reaching the smaller length of a and b, but still can't
                // get the result, consider the shorter one as the smaller one
                if (i == Math.min(la, lb))
                    return Integer.compare(la, lb);

                // compare the char at each position until find the one that
                // differs
                record = (Character.toString(a.query.charAt(i))).compareTo(
                        Character.toString(b.query.charAt(i)));
                i++;
            }
            return record;
        }
    }


    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.weight + "\t" + this.query;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term test1 = new Term("abcd", 100);
        Term test2 = new Term("abcde", 10);
        Term test3 = new Term("abc", 100);
        System.out.println(test1.compareTo(test2));

        Comparator<Term> comp1 = Term.byReverseWeightOrder();
        Comparator<Term> comp2 = Term.byPrefixOrder(2);
        System.out.println(comp1.compare(test1, test2));
        System.out.println(comp2.compare(test2, test3));

    }
}
