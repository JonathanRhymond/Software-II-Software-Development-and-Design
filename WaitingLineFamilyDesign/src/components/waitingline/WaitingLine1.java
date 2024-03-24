package components.waitingline;

import java.util.Iterator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 *
 * @author Jonathan Rhymond
 * @author Suya Dulal
 * @author Alex Zhang
 * @author Jozef Deruytter
 *
 */
public class WaitingLine1<T> extends WaitingLineSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Line included in {@code this}.
     */
    private Queue<T> line;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.line = new Queue1L<T>();
    }

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public WaitingLine1() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public WaitingLine<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public void transferFrom(WaitingLine<T> arg0) {
        WaitingLine1<T> localSource = (WaitingLine1<T>) arg0;
        this.line = localSource.line;
        localSource.createNewRep();

    }

    @Override
    public void clear() {
        this.createNewRep();

    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public void joinLine(T x) {
        this.line.enqueue(x);

    }

    @Override
    public T leaveLine() {
        return this.line.dequeue();
    }

    @Override
    public int sizeOfLine() {
        return this.line.length();
    }

    @Override
    public Iterator<T> iterator() {
        return this.line.iterator();
    }

}
