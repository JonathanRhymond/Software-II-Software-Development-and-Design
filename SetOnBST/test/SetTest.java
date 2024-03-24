import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Jonathan Rhymond & Suya Dulal
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Tests the constructor when empty.
     */
    @Test
    public final void testConstructor1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest();
        Set<String> sRef = this.createFromArgsRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests the constructor when not empty.
     */
    @Test
    public final void testConstructor2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b", "c");
        Set<String> sRef = this.createFromArgsRef("a", "b", "c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests adding to an empty set.
     */
    @Test
    public final void testAdd1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest();
        Set<String> sRef = this.createFromArgsRef("a");

        sTest.add("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests adding to a non-empty set.
     */
    @Test
    public final void testAdd2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a");
        Set<String> sRef = this.createFromArgsRef("a", "b");

        sTest.add("b");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests adding to a non-empty set.
     */
    @Test
    public final void testAdd3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b");
        Set<String> sRef = this.createFromArgsRef("a", "b", "c");

        sTest.add("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests removing from a set with only one element, making an empty set.
     */
    @Test
    public final void testRemove1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a");
        Set<String> sRef = this.createFromArgsRef();

        sTest.remove("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests removing from a set with more than one element.
     */
    @Test
    public final void testRemove2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b");
        Set<String> sRef = this.createFromArgsRef("a");

        sTest.remove("b");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests removing from a set with more than one element.
     */
    @Test
    public final void testRemove3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b", "c");
        Set<String> sRef = this.createFromArgsRef("a", "b");

        sTest.remove("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest, sRef);
    }

    /**
     * Tests removeAny with a set with only one element, making an empty set.
     */
    @Test
    public final void testRemoveAny1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a");
        Set<String> sRef = this.createFromArgsRef("a");

        String removed = sTest.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sRef.contains(removed), true);
        sRef.remove(removed);
        assertEquals(sTest, sRef);
    }

    /**
     * Tests removeAny with a set with more than one element.
     */
    @Test
    public final void testRemoveAny2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b", "c");
        Set<String> sRef = this.createFromArgsRef("a", "b", "c");

        String removed = sTest.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sRef.contains(removed), true);
        sRef.remove(removed);
        assertEquals(sTest, sRef);
    }

    /**
     * Tests contains method on an empty set.
     */
    @Test
    public final void testContains1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest();
        Set<String> sRef = this.createFromArgsRef();

        String item = "a";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest.contains(item), sRef.contains(item));
        assertEquals(sTest, sRef);
    }

    /**
     * Tests contains method on set with more than one element.
     */
    @Test
    public final void testContains2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b", "c");
        Set<String> sRef = this.createFromArgsRef("a", "b", "c");

        String item = "a";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest.contains(item), sRef.contains(item));
        assertEquals(sTest, sRef);
    }

    /**
     * Tests contains method on set with more than one element.
     */
    @Test
    public final void testContains3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b", "c");
        Set<String> sRef = this.createFromArgsRef("a", "b", "c");

        String item = "d";

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest.contains(item), sRef.contains(item));
        assertEquals(sTest, sRef);
    }

    /**
     * Tests size method on an empty set.
     */
    @Test
    public final void testSize1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest();
        Set<String> sRef = this.createFromArgsRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest.size(), sRef.size());
        assertEquals(sTest, sRef);
    }

    /**
     * Tests size method on a set with more than one element.
     */
    @Test
    public final void testSize2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> sTest = this.createFromArgsTest("a", "b", "c");
        Set<String> sRef = this.createFromArgsTest("a", "b", "c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sTest.size(), sRef.size());
        assertEquals(sTest, sRef);
    }
}
