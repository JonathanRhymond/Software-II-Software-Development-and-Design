import components.stack.Stack;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class SequenceTest {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SequenceTest() {
        // no code needed here
    }

    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLength) {
        int currentLeftLength = leftStack.length();
        int requiredShift = currentLeftLength - newLeftLength;

        if (requiredShift < 0) {
            // Need to pop items from rightStack and push them to leftStack
            requiredShift *= -1; // Make it positive for easier iteration

            for (int i = 0; i < requiredShift; i++) {
                leftStack.push(rightStack.pop());
            }
        } else if (requiredShift > 0) {
            // Need to pop items from leftStack and push them to rightStack
            for (int i = 0; i < requiredShift; i++) {
                rightStack.push(leftStack.pop());
            }
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

    }

}
