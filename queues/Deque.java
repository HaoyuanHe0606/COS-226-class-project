/* *****************************************************************************
 *  Name:    Haoyuan He
 *  NetID:   haoyuanh
 *  Precept: P03A
 *
 *  Description:   A double-ended queue or deque (pronounced “deck”) is a
 * generalization of a stack and a queue that supports adding and removing
 * items from either the front or the back of the data structure.
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // number of elements in a queue
    private int n;
    // the pointer of the first node
    private Node first;
    // the pointer of the last node
    private Node last;

    private class Node {
        // the item of a node
        private Item item;
        // the next node
        private Node next;
        // the previous node
        private Node previous;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // corner case
        if (item == null)
            throw new IllegalArgumentException();

        Node thisnode = new Node();
        thisnode.item = item;
        thisnode.previous = null;

        if (this.isEmpty()) {
            thisnode.next = null;
            first = thisnode;
            last = thisnode;
        }
        else {
            thisnode.next = first;
            first.previous = thisnode;
            first = thisnode;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        // corner case
        if (item == null)
            throw new IllegalArgumentException();

        Node thisnode = new Node();
        thisnode.item = item;

        if (this.isEmpty()) {
            thisnode.next = null;
            thisnode.previous = null;
            first = thisnode;
            last = thisnode;
        }
        else {
            last.next = thisnode;
            thisnode.next = null;
            thisnode.previous = last;
            last = thisnode;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        // corner case
        if (this.isEmpty())
            throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;
        if (first != null)
            first.previous = null;
        n--;
        if (n == 0)
            last = null;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        // corner case
        if (this.isEmpty())
            throw new NoSuchElementException();

        Node newlast = last.previous;
        Item item = last.item;

        // garbage collection
        last.previous = null;

        last = newlast;
        if (last != null)
            last.next = null;
        n--;
        if (n == 0)
            first = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        // the current node
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> test = new Deque<String>();
        System.out.println(test.isEmpty());
        System.out.println(test.size());

        test.addFirst("1");
        test.addFirst("2");
        test.addLast("3");
        test.addLast("4");

        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        System.out.println(test.isEmpty());

        Deque<Integer> test2 = new Deque<Integer>();
        for (int i = 0; i < 8; i++)
            test2.addLast(i);
        for (int s : test2) {
            System.out.println(s);
        }
    }
}

