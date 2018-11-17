/**.
 * { item_description }
 */
import java.util.Iterator;
/**.
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * { var_description }
     */
    private int n;
    /**.
     * { var_description }
     */
    private Node first;
    /**.
     * Class for node.
     */
    private class Node {
        /**.
         * { var_description }
         */
        private Item item;
        /**.
         * { var_description }
         */
        private Node next;
    }

   /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        n = 0;
    }
/**.
 * Determines if empty.
 * time complexity in average case is 1.
 * @return     True if empty, False otherwise.
 */
    public boolean isEmpty() {
        return first == null;
    }
/**.
 * { function_description }
 * time complexity in average case is 1.
 * @return     { description_of_the_return_value }
 */
    public int size() {
        return n;
    }
/**.
 * { function_description }
 * time complexity in average case is 1.
 * @param      item  The item
 */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
/**.
 * { function_description }
 * time complexity in average case is 1.
 * @return     { description_of_the_return_value }
 */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
/**.
 * Class for list iterator.
 */
    private class ListIterator implements Iterator<Item> {
        /**.
         * { var_description }
         */
        private Node current = first;
        /**.
         * Determines if it has next.
         * time complexity in average case is 1.
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**.
         * time complexity in average case is 1.
         * { function_description }
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * { function_description }
         * time complexity in average case is 1.
         * @return     { description_of_the_return_value }
         */
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
