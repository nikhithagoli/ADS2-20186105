/*************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *
 *  A generic bag or multiset, implemented using a linked list.
 *
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * N.
     */
    private int num;         // number of elements in bag
    /**
     * first.
     */
    private Node first;    // beginning of bag

    /**
     * Class for node.
     */
    private class Node {
        /**
         * value.
         */
        private Item item;
        /**
         * next.
         */
        private Node next;
    }

   /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        num = 0;
    }

   /**
    * Determines if empty.
    *
    * @return     True if empty, False otherwise.
    */
    public boolean isEmpty() {
        return first == null;
    }

   /**
    * size.
    *
    * @return     { description_of_the_return_value }
    */
    public int size() {
        return num;
    }

   /**
    * add.
    *
    * @param      item  The item
    */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        num++;
    }


   /**
    * iterator.
    *
    * @return     { description_of_the_return_value }
    */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * curent.
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**
         * removes.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * { function_description }.
         *
         * @return     { description_of_the_return_value }
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
