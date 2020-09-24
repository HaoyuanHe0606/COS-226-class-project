/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Description:  A randomized queue is similar to a stack or queue, except
 * that the item removed is chosen uniformly at random among items in the
 * data structure.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // the queue of items
    private Item[] queue;
    // number of items in a queue
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // this is a private method to resize the array
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = queue[i];
        queue = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (n == 0)
            resize(1);
        if (n == queue.length)
            resize(2 * queue.length);
        queue[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        int k = StdRandom.uniform(n);
        Item copy = queue[k];
        queue[k] = queue[n - 1];
        queue[n - 1] = null;
        n--;
        if (n == queue.length / 4)
            resize(queue.length / 2);
        return copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty())
            throw new NoSuchElementException();

        int k = StdRandom.uniform(n);
        return queue[k];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        // an instance variable for iteration
        private int i;
        // a copy of the index
        private final int[] copy;

        // make a copy of the index
        public RandomIterator() {
            i = 0;
            copy = new int[n];
            for (int j = 0; j < n; j++)
                copy[j] = j;

        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (i == 0)
                StdRandom.shuffle(copy);
            return queue[copy[i++]];
        }
    }

    // unit testing (required)


    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        System.out.println(test.isEmpty());
        System.out.println(test.size());


        test.enqueue("1");
        test.enqueue("2");
        test.enqueue("3");
        test.enqueue("4");

        System.out.println(test.sample());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.isEmpty());

    }
}
