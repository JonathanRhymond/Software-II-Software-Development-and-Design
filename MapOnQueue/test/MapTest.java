import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    @Test
    public void testAdd() {
        // Create a map using the reference implementation
        Map<String, String> map = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        // Test adding a new key-value pair
        map.add("key3", "value3");

        // Check if the map contains the new key
        assertTrue(map.hasKey("key3"));
    }

    @Test
    public void testRemove() {
        // Create a map using the reference implementation
        Map<String, String> map = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        // Test removing an existing key-value pair
        map.remove("key1");

        // Check if the map no longer contains the removed key
        assertFalse(map.hasKey("key1"));
    }

    @Test
    public void testRemoveAny() {
        // Create a map using the reference implementation
        Map<String, String> map = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        // Test removing an arbitrary key-value pair
        Map.Pair<String, String> removedPair = map.removeAny();

        // Check if the removed pair is not null
        assertNotNull(removedPair);
    }

    @Test
    public void testValue() {
        // Create a map using the reference implementation
        Map<String, String> map = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        // Test retrieving the value associated with a key
        String value = map.value("key1");

        // Check if the retrieved value is as expected
        assertEquals("value1", value);
    }

    @Test
    public void testHasKey() {
        // Create a map using the reference implementation
        Map<String, String> map = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        // Test checking if a key exists in the map
        boolean hasKey = map.hasKey("key1");

        // Check if the key exists in the map
        assertTrue(hasKey);
    }

    @Test
    public void testSize() {
        // Create a map using the reference implementation
        Map<String, String> map = this.createFromArgsRef("key1", "value1",
                "key2", "value2");

        // Test the size of the map
        assertEquals(2, map.size());
    }

}
