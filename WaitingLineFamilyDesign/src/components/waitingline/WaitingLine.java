package components.waitingline;

/**
 * {@code QueueKernel} enhanced with secondary methods.
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
 *
 * @author Jonathan Rhymond
 * @author Suya Dulal
 * @author Alex Zhang
 * @author Jozef Deruytter
 *
 */
public interface WaitingLine<T> extends WaitingLineKernel<T> {

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    T firstInLine();

    /**
     * Replaces the front of {@code this} with {@code x}, and returns the old
     * front.
     *
     * @param x
     *            the new front entry
     * @return the old front entry
     * @aliases reference {@code x}
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * <replaceFront> is prefix of #this  and
     * this = <x> * #this[1, |#this|)
     * </pre>
     */
    T replaceFirstInLine(T x);

    /**
     * Concatenates ("appends") {@code q} to the end of {@code this}.
     *
     * @param q
     *            the {@code Queue} to be appended to the end of {@code this}
     * @updates this
     * @clears q
     * @ensures this = #this * #q
     */
    void appendToLine(WaitingLine<T> q);

    /**
     * Remove the entry at {@code pos} from {@code this}.
     *
     * @param pos
     *            the position of the entry to be removed
     * @return the entry at {@code pos}
     * @requires pos <= length of {@code this}
     * @ensures the entry at {@code pos} be removed and be returned
     */
    T removeFromLine(int pos);

    /**
     * Removes {@code element} from {@code this}.
     *
     * @param element
     *            the entry to be removed
     * @return the previous position of {@code element}
     * @requires {@code element} is in {@code this}
     * @ensures {@code element} be removed and its former position be returned
     */
    int removeFromLine(T element);

    /**
     *
     * Report if {@code element} if in {@code this}.
     *
     * @param element
     *            the element to be checked
     *
     * @return true if element is in {@code this} and false otherwise
     * @ensures isInLine = (element is in this)
     */
    boolean isInLine(T element);

    /**
     *
     * Report the placeInLine of {@code element} in {@code this}.
     *
     * @param element
     *            the element to be checked
     *
     * @return the position of {@code element} in {@code this}
     */

    int placeInLine(T element);

}
