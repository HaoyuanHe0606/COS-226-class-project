/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Partner Name:    Yutong Shen
 *  Partner NetID:   yutongs
 *  Partner Precept: P03A
 *
 *  Description: Given a list of WordNet nouns x1, x2, ..., xn, return the noun
 * that is the least related to the others?
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        String outcast = null;
        int max = 0;
        for (int i = 0; i < nouns.length; i++) {
            int totald = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i != j)
                    totald += wordnet.distance(nouns[i], nouns[j]);
            }
            if (totald > max) {
                max = totald;
                outcast = nouns[i];
            }
        }
        return outcast;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
