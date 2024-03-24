import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
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
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        // TODO - fill in body

        s2.clear();
        if (!(s1.length() <= 1)) {
            for (int c = 1; c <= s1.length() - 1; c++) {
                int i = s1.remove(c);
                int j = s1.remove(c - 1);
                int halfAvg;
                if (i + j == Integer.MAX_VALUE) {
                    halfAvg = ((i / 2) + (j / 2));
                    s2.add(c - 1, (((halfAvg) / 2) * 2));
                    s1.add(c - 1, j);
                    s1.add(c, i);
                }
                if (i + j == Integer.MIN_VALUE) {
                    halfAvg = ((i / 2) + (j / 2));
                    s2.add(c - 1, (((halfAvg) / 2) * 2));
                    s1.add(c - 1, j);
                    s1.add(c, i);
                } else {
                    halfAvg = i + j;
                    s2.add(c - 1, ((halfAvg) / 2));
                    s1.add(c - 1, j);
                    s1.add(c, i);
                }
            }
        }

    }

}