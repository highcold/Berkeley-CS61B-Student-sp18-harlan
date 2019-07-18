import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> queues = new Queue<>();
        for (Item item : items) {
            Queue<Item> queue = new Queue<>();
            queue.enqueue(item);
            queues.enqueue(queue);
        }
        return queues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> queue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            queue.enqueue(getMin(q1, q2));
        }
        return queue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items == null) {
            return null;
        }
        if (items.isEmpty()) {
            return null;
        }
        Queue<Queue<Item>> queues = makeSingleItemQueues(items);
        while (true) {
            Queue<Item> q1 = queues.dequeue();
            if (queues.isEmpty()) {
                return q1;
            }
            Queue<Item> q2 = queues.dequeue();
            queues.enqueue(mergeSortedQueues(q1, q2));
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        Integer[] a = new Integer[1000];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);
        for (int i = 0; i < a.length; i++) {
            queue.enqueue(a[i]);
        }
        queue = MergeSort.mergeSort(queue);
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }
    }

    @Test
    public void mergeSortedQueuesTest() {
        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();
        q1.enqueue(1);
        q1.enqueue(5);
        q1.enqueue(10);
        q2.enqueue(2);
        q2.enqueue(3);
        q2.enqueue(100);
        Queue<Integer> q = mergeSortedQueues(q1, q2);
        System.out.println(q);
    }

    @Test
    public void quickSortedQueueTest() {
        Queue<Integer> queue = new Queue<>();
        Integer[] a = new Integer[1000];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        StdRandom.shuffle(a);
        for (int i = 0; i < a.length; i++) {
            queue.enqueue(a[i]);
        }
        QuickSort.quickSort(queue);
        for (int i = 0; i < 10; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
