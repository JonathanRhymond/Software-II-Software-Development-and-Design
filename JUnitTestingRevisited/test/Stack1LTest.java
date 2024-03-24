import components.stack.Stack;
import components.stack.Stack1L;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        // TODO - fill in body
        return new Stack1L<>();
    }

    @Override
    protected final Stack<String> constructorRef() {

        // TODO - fill in body
        return new Stack3<>();
    }

}
