/* *****************************************************************************
 *  Name:    Yutong Shen
 *  NetID:   yutongs
 *  Precept: P03A
 *
 *  Partner Name:    Haoyuan He
 *  Partner NetID:   haoyuanh
 *  Partner Precept: P03A
 *
 *  Description:  This program tends to implement an immutable data type based
 *                on a digraph G and find the shortest length between two
 *                vertices shortest common anscestor between two vertices
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ShortestCommonAncestor {
    // maximum value
    private static final int MAX = Integer.MAX_VALUE;
    // digraph G
    private final Digraph G;

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException();
        isDag(G);
        this.G = new Digraph(G);
    }

    // check if the digraph is a DAG
    private void isDag(Digraph graph) {
        // check cycle
        DirectedCycle check = new DirectedCycle(graph);
        if (check.hasCycle())
            throw new IllegalArgumentException();

        // check the number of roots
        int roots = 0;
        for (int i = 0; i < graph.V(); i++) {
            if (graph.outdegree(i) == 0)
                roots++;
        }
        if (roots > 1)
            throw new IllegalArgumentException();
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) {
        // corner case
        if (!checkIndex(v) || !checkIndex(w))
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
        return length(bfsV, bfsW);
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int lengthSubset(Iterable<Integer> subsetA,
                            Iterable<Integer> subsetB) {
        // corner case
        if (subsetA == null || subsetB == null)
            throw new IllegalArgumentException();
        if (!checkIndex(subsetA) || !checkIndex(subsetB))
            throw new IllegalArgumentException();
        if (checkCorner(subsetA) || checkCorner(subsetB))
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfsSubsetA
                = new BreadthFirstDirectedPaths(G, subsetA);
        BreadthFirstDirectedPaths bfsSubsetB
                = new BreadthFirstDirectedPaths(G, subsetB);
        return length(bfsSubsetA, bfsSubsetB);
    }

    // a private helper method to find the shortest ancestral path
    private int length(BreadthFirstDirectedPaths bfsV,
                       BreadthFirstDirectedPaths bfsW) {
        int shortest = MAX;
        // go through the loop to find ancestor and shortest ancestral path
        for (int i = 0; i < G.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int dis = bfsV.distTo(i) + bfsW.distTo(i);
                if (shortest == -1 || dis < shortest) shortest = dis;
            }
        }
        if (shortest != MAX) return shortest;
        return -1;
    }


    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) {
        // corner case
        if (!checkIndex(v) || !checkIndex(w))
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
        return ancestor(bfsV, bfsW);
    }


    // a shortest common ancestor of vertex subset A and B
    public int ancestorSubset(Iterable<Integer> subsetA,
                              Iterable<Integer> subsetB) {
        // corner case
        if (subsetA == null || subsetB == null)
            throw new IllegalArgumentException();
        if (!checkIndex(subsetA) || !checkIndex(subsetB))
            throw new IllegalArgumentException();
        if (checkCorner(subsetA) || checkCorner(subsetB))
            throw new IllegalArgumentException();
        BreadthFirstDirectedPaths bfsSubsetA
                = new BreadthFirstDirectedPaths(G, subsetA);
        BreadthFirstDirectedPaths bfsSubsetB
                = new BreadthFirstDirectedPaths(G, subsetB);
        return ancestor(bfsSubsetA, bfsSubsetB);

    }

    // private helper method to find the ancestor of two vertices
    private int ancestor(BreadthFirstDirectedPaths bfsV,
                         BreadthFirstDirectedPaths bfsW) {
        int ancestor = -1;
        int shortest = MAX;
        // go through the loop to find ancestor and shortest ancestral path
        for (int i = 0; i < G.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int dis = bfsV.distTo(i) + bfsW.distTo(i);
                if (dis < shortest) {
                    shortest = dis;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }


    // check if the input index for graph is valid or not
    private boolean checkIndex(int v) {
        if (v < 0 || v > G.V() - 1) return false;
        return true;
    }

    // check if the input iterable object for the graph is valid or not
    private boolean checkIndex(Iterable<Integer> vertices) {
        for (Integer vertex : vertices) {
            return (checkIndex(vertex));
        }
        return true;
    }

    // check if the input iterable object has zero vertices or not
    private boolean checkCorner(Iterable<Integer> vertices) {
        int size = 0;
        for (Integer vertex : vertices) {
            if (vertex == null) throw new IllegalArgumentException();
            size++;
        }
        return size == 0;
    }


    // unit testing
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        Queue<Integer> a = new Queue<Integer>();
        Queue<Integer> b = new Queue<Integer>();
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sca.length(v, w);
            int ancestor = sca.ancestor(v, w);
            a.enqueue(v);
            b.enqueue(w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
        StdOut.println(sca.ancestorSubset(a, b));
        StdOut.println(sca.lengthSubset(a, b));

    }
}
