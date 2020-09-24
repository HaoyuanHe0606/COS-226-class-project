/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Description:  It takes an integer k as a command-line argument; reads a
 * sequence of strings from standard input using StdIn.readString(); and prints
 * exactly k of them, uniformly at random. Print each item from the sequence
 * at most once.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        double number = 1.0;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (k == 0)
                return;
            else if (queue.size() < k)
                queue.enqueue(item);
            else if (StdRandom.uniform() < (k / number)) {
                queue.dequeue();
                queue.enqueue(item);
            }
            number++;
        }

        for (int j = 0; j < k; j++)
            System.out.println(queue.dequeue());
    }
}
