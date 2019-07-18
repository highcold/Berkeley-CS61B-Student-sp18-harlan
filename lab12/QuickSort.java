import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for (Item item : unsorted) {
            if (unsorted.peek().compareTo(pivot) > 0) {
                greater.enqueue(unsorted.dequeue());
            } else if (unsorted.peek().compareTo(pivot) == 0) {
                equal.enqueue(unsorted.dequeue());
            } else {
                less.enqueue(unsorted.dequeue());
            }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        if (items == null) {
            throw new IllegalArgumentException("Items must not be null");
        }
        if (items.size() <= 1) {
            return items;
        }

        Item pivot = getRandomItem(items);
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        partition(items, pivot, less, equal, greater);

        return catenate(catenate(quickSort(less), equal), quickSort(greater));
    }

    @Test
    public void quickSortedQueueTest() {
        Queue<Integer> queue = new Queue<>();
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);
        for (int i = 0; i < a.length; i++) {
            queue.enqueue(a[i]);
        }
        queue = QuickSort.quickSort(queue);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
