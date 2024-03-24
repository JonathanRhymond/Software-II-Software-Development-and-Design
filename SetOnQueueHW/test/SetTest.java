import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    @Test
    public void testAdd() {
        Set<String> set = this.constructorTest();
        assertTrue(set.size() == 0);

        set.add("item1");
        assertFalse(set.size() == 0);
        assertTrue(set.contains("item1"));
    }

    @Test
    public void testRemove() {
        Set<String> set = this.createFromArgsTest("item1", "item2", "item3");
        assertEquals(3, set.size());

        set.remove("item2");
        assertEquals(2, set.size());
        assertFalse(set.contains("item2"));
    }

    @Test
    public void testRemoveAny() {
        Set<String> set = this.createFromArgsTest("item1", "item2", "item3");
        int initialSize = set.size();

        String removedItem = set.removeAny();
        assertNotNull(removedItem);
        assertFalse(set.contains(removedItem));
        assertEquals(initialSize - 1, set.size());
    }

    @Test
    public void testContains() {
        Set<String> set = this.createFromArgsTest("item1", "item2", "item3");

        assertTrue(set.contains("item1"));
        assertTrue(set.contains("item2"));
        assertTrue(set.contains("item3"));
        assertFalse(set.contains("item4"));
    }

    @Test
    public void testSize() {
        Set<String> set = this.createFromArgsTest("item1", "item2", "item3");
        assertEquals(3, set.size());

        set.remove("item2");
        assertEquals(2, set.size());

        set.add("item4");
        assertEquals(3, set.size());
    }

}
