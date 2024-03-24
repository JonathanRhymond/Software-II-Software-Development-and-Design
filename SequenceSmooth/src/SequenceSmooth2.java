import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth2() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1,
            Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        // TODO - fill in body
        Sequence<Integer> s3 = new Sequence1L<Integer>();

        for (int k = 0; k < s1.length() - 1; k++) {
            int i = s1.remove(0);
            int j = s1.remove(0);
            int smoothed = (i + j) / 2;
            s3.add(smoothed, 0);

            s1.add(i, 0);
            s1.add(i, 1);
        }

        return s3;

    }

}