import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;
import components.stack.Stack1L;

public class SequenceTest {

    @Test
    // Test adjusting the length of leftStack
    public void test1() {
        Stack<Integer> leftStack = new Stack1L<>();
        Stack<Integer> rightStack = new Stack1L<>();

        leftStack.push(1);
        leftStack.push(2);
        leftStack.push(3);

        rightStack.push(4);
        rightStack.push(5);

        Sequence3.setLengthOfLeftStack(leftStack, rightStack, 2);

        assertEquals(2, leftStack.length());
        assertEquals(3, rightStack.length());
    }

    @Test
    // Test adjusting the length of leftStack when lengths are equal
    public void test2() {
        Stack<Integer> leftStack = new Stack1L<>();
        Stack<Integer> rightStack = new Stack1L<>();

        leftStack.push(1);
        leftStack.push(2);

        rightStack.push(3);
        rightStack.push(4);

        Sequence3.setLengthOfLeftStack(leftStack, rightStack, 2);

        assertEquals(2, leftStack.length());
        assertEquals(2, rightStack.length());
    }

    @Test
    // Test adding and removing elements from the stack
    public void test3() {
        Stack<Integer> stack = new Stack1L<>();

        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.length());

        int removed = stack.pop();
        assertEquals(2, removed);
        assertEquals(1, stack.length());
    }

    @Test
    // Test getting the size of the stack
    public void test4() {
        Stack<Integer> stack = new Stack1L<>();

        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.length());
    }

    @Test
    // Test adjusting the length of leftStack when new length is 0
    public void test5() {
        Stack<Integer> leftStack = new Stack1L<>();
        Stack<Integer> rightStack = new Stack1L<>();

        leftStack.push(1);
        leftStack.push(2);

        rightStack.push(3);
        rightStack.push(4);

        Sequence3.setLengthOfLeftStack(leftStack, rightStack, 0);

        assertEquals(0, leftStack.length());
        assertEquals(4, rightStack.length());
    }

    @Test
    // Test adding and removing multiple elements
    public void test6() {
        Stack<Integer> stack = new Stack1L<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.length());

        stack.pop();
        assertEquals(2, stack.length());

        stack.push(4);
        stack.push(5);
        assertEquals(4, stack.length());
    }

}
