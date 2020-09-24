/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Partner Name:    Yutong Shen
 *  Partner NetID:   yutongs
 *  Partner Precept: P03A
 *
 *  Description:  This is a mutable data type that uses a red–black BST to
 * represent a symbol table whose keys are two-dimensional points.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<Value> {
    // use a redblack tree as the symble table
    private final RedBlackBST<Point2D, Value> st;
    private int number;

    // construct an empty symbol table of points
    public PointST() {
        st = new RedBlackBST<Point2D, Value>();
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return st.isEmpty();
    }

    // number of points
    public int size() {
        return st.size();
    }

    // associate the value val with point p
    public void put(Point2D p, Value val) {
        // corner cases
        if (p == null || val == null)
            throw new IllegalArgumentException();

        st.put(p, val);
    }

    // value associated with point p
    public Value get(Point2D p) {
        // corner cases
        if (p == null)
            throw new IllegalArgumentException();

        return st.get(p);
    }

    // does the symbol table contain point p?
    public boolean contains(Point2D p) {
        // corner cases
        if (p == null)
            throw new IllegalArgumentException();

        return st.contains(p);
    }

    // all points in the symbol table
    public Iterable<Point2D> points() {
        return st.keys();
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        // cornerc cases
        if (rect == null)
            throw new IllegalArgumentException();
        // a queue to store the internal points
        Queue<Point2D> internals = new Queue<>();
        // find all internal points
        for (Point2D x : st.keys())
            if (rect.contains(x))
                internals.enqueue(x);
        return internals;
    }

    // a nearest neighbor of point p; null if the symbol table is empty
    public Point2D nearest(Point2D p) {
        // corner cases
        if (p == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            return null;
        // stores the nearest point
        Point2D storep = new Point2D(0, 0);
        // stores the nearest distance
        double stored = Double.POSITIVE_INFINITY;

        // update the nearest point and the nearest distance
        for (Point2D k : st.keys())
            if (p.distanceSquaredTo(k) < stored) {
                storep = k;
                stored = p.distanceSquaredTo(k);
                number++;
            }
        return storep;
    }

    public int numofcalls() {
        return number;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // unit test
        PointST<Integer> test = new PointST<Integer>();
        Point2D test1 = new Point2D(0, 0);
        Point2D test2 = new Point2D(1, 2);
        Point2D test3 = new Point2D(2, 3);
        RectHV test4 = new RectHV(0, 4, 2, 4);
        test.put(test1, 2);
        test.put(test2, 3);

        System.out.println(test.isEmpty());
        System.out.println(test.contains(test1));
        System.out.println(test.get(test1));
        System.out.println(test.nearest(test3));
        System.out.println(test.points());
        System.out.println(test.range(test4));
        System.out.println(test.size());


    }

}
