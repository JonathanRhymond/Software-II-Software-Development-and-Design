import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    @Test
    public void testEmptyConstructor() {
        Stack<String> testStack = this.createFromArgsTest();
        assertEquals(testStack.length(), 0);
    }

    @Test
    public void testNonEmptyConstructor() {
        Stack<String> testStack = this.createFromArgsTest("A", "B", "C");
        Stack<String> refStack = this.createFromArgsRef("A", "B", "C");
        assertEquals(testStack.length(), refStack.length());
        assertEquals(testStack.top(), refStack.top());
    }

    @Test
    public void testEmptyPush() {
        Stack<String> testStack = this.createFromArgsTest();
        Stack<String> refStack = this.createFromArgsRef();
        testStack.push("Potato");
        refStack.push("Potato");
        assertEquals(testStack.length(), refStack.length());
        assertEquals(testStack.top(), refStack.top());
    }

    @Test
    public void testNonEmptyPush() {
        Stack<String> testStack = this.createFromArgsTest("A", "B", "C");
        Stack<String> refStack = this.createFromArgsRef("A", "B", "C");
        testStack.push("Potato");
        refStack.push("Potato");
        assertEquals(testStack.length(), refStack.length());
        assertEquals(testStack.top(), refStack.top());
    }

    @Test
    public void testEmptyPop() {
        Stack<String> testStack = this.createFromArgsTest("Potato");
        Stack<String> refStack = this.createFromArgsRef("Potato");
        String testPop = testStack.pop();
        String refPop = refStack.pop();
        assertEquals(testStack.length(), refStack.length());
        assertEquals(testPop, refPop);
    }

    @Test
    public void testNonEmptyPop() {
        Stack<String> testStack = this.createFromArgsTest("A", "B", "C");
        Stack<String> refStack = this.createFromArgsRef("A", "B", "C");
        String testPop = testStack.pop();
        String refPop = refStack.pop();
        assertEquals(testStack.length(), refStack.length());
        assertEquals(testPop, refPop);
    }

    public void testEmptyLength() {
        Stack<String> testStack = this.createFromArgsTest();
        Stack<String> refStack = this.createFromArgsRef();
        assertEquals(testStack.length(), refStack.length());
    }

    public void testNonEmptyLength() {
        Stack<String> testStack = this.createFromArgsTest("A", "B", "C");
        Stack<String> refStack = this.createFromArgsRef("A", "B", "C");
        assertEquals(testStack.length(), refStack.length());
    }

}
