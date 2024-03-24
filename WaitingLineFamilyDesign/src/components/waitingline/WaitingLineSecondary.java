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

public abstract class WaitingLineSecondary<T> implements WaitingLine<T> {

    /*
     * Common methods
     */

    @Override
    public final boolean equals(Object obj) {
        boolean isEqual = true;
        WaitingLine<?> w = (WaitingLine<?>) obj;
        if (obj == null) {
            isEqual = false;
        } else if (!(obj instanceof WaitingLine<?>)) {
            isEqual = false;
        } else if (this.sizeOfLine() != w.sizeOfLine()) {
            isEqual = false;
        } else if (obj == this) {
            isEqual = true;
        } else {
            Iterator<T> it1 = this.iterator();
            Iterator<?> it2 = w.iterator();
            while (it1.hasNext()) {
                T x1 = it1.next();
                Object x2 = it2.next();
                if (!x1.equals(x2)) {
                    isEqual = false;
                }
            }
        }
        return isEqual;

    }

    @Override
    public int hashCode() {
        final int a = 37;
        final int b = 17;
        int result = 0;
        int num = 0;
        Iterator<T> it = this.iterator();
        while (it.hasNext() && num < 2) {
            T x = it.next();
            result = a * result + b * x.hashCode();
            num++;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(",");
            }
        }
        result.append(">");
        return result.toString();
    }

    /*
     * Non-Kernel methods
     */

    @Override
    public T firstInLine() {
        Iterator<T> it = this.iterator();
        T x = it.next();
        return x;
    }

    @Override
    public T replaceFirstInLine(T x) {
        Queue<T> temp = new Queue1L<>();
        temp.enqueue(x);
        T replaced = this.leaveLine();
        while (this.sizeOfLine() > 0) {
            temp.enqueue(this.leaveLine());
        }
        while (temp.length() > 0) {
            this.joinLine(temp.dequeue());
        }
        return replaced;
    }

    @Override
    public void appendToLine(WaitingLine<T> q) {
        while (q.sizeOfLine() > 0) {
            this.joinLine(q.leaveLine());
        }
    }

    @Override
    public T removeFromLine(int pos) {
        WaitingLine<T> temp = this.newInstance();
        for (int i = 0; i < pos; i++) {
            temp.joinLine(this.leaveLine());
        }
        T returned = this.leaveLine();
        this.appendToLine(temp);
        return returned;
    }

    @Override
    public int removeFromLine(T element) {
        int pos = 0;
        boolean flag = false;
        WaitingLine<T> temp = this.newInstance();
        while (this.sizeOfLine() > 0 && !flag) {
            T currentElement = this.leaveLine();
            if (currentElement.equals(element)) {
                flag = true;
            } else {
                temp.joinLine(currentElement);
                pos++;
            }
        }
        this.appendToLine(temp);
        return pos;
    }

    @Override
    public boolean isInLine(T element) {
        boolean inLine = false;
        WaitingLine<T> temp = this.newInstance();
        while (this.sizeOfLine() > 0 && !inLine) {
            if (this.firstInLine().equals(element)) {
                inLine = true;
            } else {
                temp.joinLine(this.leaveLine());
            }
        }
        this.appendToLine(temp);
        return inLine;
    }

    @Override
    public int placeInLine(T element) {
        int pos = 0;
        WaitingLine<T> temp = this.newInstance();
        while (!this.firstInLine().equals(element)) {
            temp.joinLine(temp.leaveLine());
            pos++;
        }
        this.appendToLine(temp);
        return pos;
    }
}
