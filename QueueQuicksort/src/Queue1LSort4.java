import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @mathdefinitions <pre>
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * </pre>
 */
public final class Queue1LSort4<T> extends Queue1L<T> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort4() {
        super();
    }

    /**
     * Partitions {@code q} into two parts: entries no larger than
     * {@code partitioner} are put in {@code front}, and the rest are put in
     * {@code back}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be partitioned
     * @param partitioner
     *            the partitioning value
     * @param front
     *            upon return, the entries no larger than {@code partitioner}
     * @param back
     *            upon return, the entries larger than {@code partitioner}
     * @param order
     *            ordering by which to separate entries
     * @clears q
     * @replaces front, back
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(#q, front * back)  and
     * for all x: T where (<x> is substring of front)
     *  ([relation computed by order.compare method](x, partitioner))  and
     * for all x: T where (<x> is substring of back)
     *  (not [relation computed by order.compare method](x, partitioner))
     * </pre>
     */
    private static <T> void partition(Queue<T> q, T partitioner, Queue<T> front,
            Queue<T> back, Comparator<T> order) {
        assert q != null : "Violation of: q is not null";
        assert partitioner != null : "Violation of: partitioner is not null";
        assert front != null : "Violation of: front is not null";
        assert back != null : "Violation of: back is not null";
        assert order != null : "Violation of: order is not null";

        //Iterate through the elements in the input queue
        while (q.length() > 0) {
            T x = q.dequeue(); // Dequeue an element from q
            int comparisonResult = order.compare(x, partitioner);

            // Check the comparison result and enqueue x accordingly
            if (comparisonResult <= 0) {
                front.enqueue(x); // x is less than or equal to partitioner
            } else {
                back.enqueue(x); // x is greater than partitioner
            }
        }

        // Clear the original queue
        q.clear();

    }

    @Override
    public void sort(Comparator<T> order) {
        assert order != null : "Violation of: order is not null";
        if (this.length() <= 1) {
            // The queue is already sorted if it has 0 or 1 element
            return;
        }

        // Choose a pivot element (in this case, the first element)
        T pivot = this.dequeue();

        // Initialize two new queues for partitioning
        Queue<T> smallerEntries = new Queue1L<T>();
        Queue<T> largerEntries = new Queue1L<T>();

        // Use the partition function to split the queue into smaller and larger partitions
        partition(this, pivot, smallerEntries, largerEntries, order);

        // Recursively sort the two partitions
        smallerEntries.sort(order);
        largerEntries.sort(order);

        // Reconstruct the original queue by merging the sorted partitions
        this.clear();
        while (smallerEntries.length() > 0) {
            this.enqueue(smallerEntries.dequeue());
        }
        this.enqueue(pivot); // The pivot is already in its sorted position
        while (largerEntries.length() > 0) {
            this.enqueue(largerEntries.dequeue());
        }
    }

}
