import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Jonathan Rhymond & Suya Dulal
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    /**
     * Sample test case for Constructor.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /**
     * Sample test case for Add.
     */
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Test case for Add to non empty.
     */
    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "potato", "tomato");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "potato", "tomato", "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Test case for RemoveFirst method to empty.
     */
    @Test
    public final void testRemoveFirstEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "potato");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String x = m.removeFirst();
        assertEquals("potato", x);
        assertEquals(m, mExpected);
    }

    /**
     * Test case for Remove method to non empty.
     */
    @Test
    public final void testRemoveFirstNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "potato", "tomato");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "tomato");
        String x = m.removeFirst();
        assertEquals("potato", x);
        assertEquals(m, mExpected);
    }

    /**
     * Test case for change to extraction mode.
     */
    @Test
    public final void testChangeToExtractionModeSingle() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "potato");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "potato");
        m.changeToExtractionMode();
        assertFalse(m.isInInsertionMode());
        assertEquals(m, mExpected);

    }

    /**
     * Test case for change to extraction mode method on multiple elements.
     */
    @Test
    public final void testChangeToExtractionModeMulti() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "potato", "tomato", "apple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "potato", "tomato", "apple");
        m.changeToExtractionMode();
        assertFalse(m.isInInsertionMode());
        assertEquals(m, mExpected);
    }

    /**
     * Test case for is in insertion mode.
     */
    @Test
    public final void testIsInInsertionModeSingle() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "potato");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "potato");
        assertTrue(m.isInInsertionMode());
        assertEquals(m, mExpected);
    }

    /**
     * Test case for is in insertion mode method on on multiple elements.
     */
    @Test
    public final void testIsInInsertionModeMulti() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "potato", "tomato", "apple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "potato", "tomato", "apple");
        assertTrue(m.isInInsertionMode());
        assertEquals(m, mExpected);
    }

    /**
     * Test case for Size method on empty Sorting Machine.
     */
    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        final int expectedSize = 0;
        assertEquals(expectedSize, m.size());
    }

    /**
     * Test case for Size method on non empty Sorting Machines.
     */
    @Test
    public final void testSizeNonEmpty() {
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false, "1",
                "2", "3", "4", "5", "6", "7", "8", "9");
        final int expectedSize = 9;
        assertEquals(expectedSize, s.size());
    }

    /**
     * Test case for Order method on empty Sorting Machine.
     */
    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        s.changeToExtractionMode();
        assertEquals(s.order(), ORDER);
    }

    /**
     * Test case for Order method on non empty Sorting Machine.
     */
    @Test
    public final void testOrderNonEmpty() {
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "50",
                "49", "48", "47", "46", "45", "44", "43", "42");
        s.changeToExtractionMode();
        assertEquals(s.order(), ORDER);
    }

}
