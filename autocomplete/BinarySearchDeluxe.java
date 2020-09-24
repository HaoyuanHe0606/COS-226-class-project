/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Partner Name:    Yutong Shen
 *  Partner NetID:   yutongs
 *  Partner Precept: P03A
 *
 *  Description: This program enables finding the first or last key in a sorted
 * array.
 **************************************************************************** */

import java.util.Comparator;

public class BinarySearchDeluxe {
    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(
            Key[] a, Key key, Comparator<Key> comparator) {
        // corner cases
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException();
        if (a.length == 0)
            return -1;


        // the lo pointer
        int lo = 0;
        // the hi pointer
        int hi = a.length - 1;
        // the record of the index
        int record = -1;
        // binarily search for k
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comp = comparator.compare(key, a[mid]);
            if (comp == 0) {
                record = mid;
                hi = mid - 1;
            }
            else if (comp > 0)
                lo = mid + 1;
            else hi = mid - 1;
        }
        // return the record
        return record;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(
            Key[] a, Key key, Comparator<Key> comparator) {

        // corner cases
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException();
        if (a.length == 0)
            return -1;

        // the lo pointer
        int lo = 0;
        // the hi pointer
        int hi = a.length - 1;
        // the record of the index
        int record = -1;
        // binarily search for k
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comp = comparator.compare(key, a[mid]);
            if (comp == 0) {
                record = mid;
                lo = mid + 1;
            }
            else if (comp > 0)
                lo = mid + 1;
            else hi = mid - 1;
        }
        // return the record
        return record;
    }

    // unit testing (required)
    public static void main(String[] args) {
        String[] a = { "A", "A", "C", "G", "G", "T" };

        int index1 = BinarySearchDeluxe.firstIndexOf(
                a, "G", String.CASE_INSENSITIVE_ORDER);
        int index2 = BinarySearchDeluxe.lastIndexOf(
                a, "G", String.CASE_INSENSITIVE_ORDER);

        System.out.println(index1);
        System.out.println(index2);

    }
}


