/* *****************************************************************************
 *  Name:    Yutong Shen
 *  NetID:   yutongs
 *  Precept: P03A
 *
 *  Partner Name:    Haoyuan He
 *  Partner NetID:   haoyuanh
 *  Partner Precept: P03A
 *
 *  Description:  this program constructs a basic data structure for wordnet
 *                using the concepts of graph and symbol tables. It is able
 *                to iterate all nouns, find the word in the noun sets, find a
 *                shortest common ancestor of 2 nouns, and calculate the
 *                distance between two nouns.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class WordNet {
    // keep track and record all sysnsets from input
    private final Map<Integer, String> sysnsetList;
    // keep track all nouns and their id from input
    private final Map<String, Bag<Integer>> nounList;
    // shortest common ancestor
    private final ShortestCommonAncestor sca;

    // constructor takes the name of the two input files
    public WordNet(String sysnsets, String hypernyms) {
        if (sysnsets == null || hypernyms == null)
            throw new IllegalArgumentException();
        sysnsetList = new HashMap<Integer, String>();
        nounList = new HashMap<String, Bag<Integer>>();

        // read inputs from sysnsets
        In read = new In(sysnsets);

        // read from input, transform datatype to int and store evey id,
        // sysnset, and nouns into the two instance STs
        int size = 0;
        while (read.hasNextLine()) {
            size++;
            String line = read.readLine();
            // update sysnsetList by reading from the first field and second
            // field of the input
            String[] allArgs = line.split(",");
            int id = Integer.parseInt(allArgs[0]);
            String sysnset = allArgs[1];
            sysnsetList.put(id, sysnset);
            // update nounList by reading from the id and second field
            // separated by space
            String[] nouns = sysnset.split(" ");
            for (String noun : nouns) {
                if (!nounList.containsKey(noun))
                    nounList.put(noun, new Bag<Integer>());
                nounList.get(noun).add(id);
            }
        }

        // construct the graph with size derived above
        Digraph G = new Digraph(size);
        In read1 = new In(hypernyms);
        while (read1.hasNextLine()) {
            String line = read1.readLine();
            String[] nums = line.split(",");
            int id = Integer.parseInt(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                int endPoint = Integer.parseInt(nums[i]);
                // if (endPoint >= 0 && endPoint < size)
                G.addEdge(id, endPoint);
            }
        }
        sca = new ShortestCommonAncestor(G);
    }

    // all WordNet nouns
    public Iterable<String> nouns() {
        return nounList.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException();
        return nounList.containsKey(word);
    }

    // a synset(second field of synset.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2))
            throw new IllegalArgumentException();

        // use the ancestorSubset method to go through iteration
        // and find ancestor, use the ancestor as id to get sysnset
        Bag<Integer> a = nounList.get(noun1);
        Bag<Integer> b = nounList.get(noun2);
        int ancestor = sca.ancestorSubset(a, b);
        return sysnsetList.get(ancestor);
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2))
            throw new IllegalArgumentException();

        // use the ancestorSubset method to go through iteration
        // and find shortest distance
        Bag<Integer> a = nounList.get(noun1);
        Bag<Integer> b = nounList.get(noun2);
        return sca.lengthSubset(a, b);
    }

    // unit testing (required)
    public static void main(String[] args) {
        WordNet test = new WordNet(args[0], args[1]);
        StdOut.println(test.distance("apple", "food"));
        StdOut.println(test.sca("apple", "food"));
        StdOut.println(test.isNoun("vigorish"));
        for (String noun : test.nouns())
            StdOut.println(test.isNoun(noun));

    }
}
