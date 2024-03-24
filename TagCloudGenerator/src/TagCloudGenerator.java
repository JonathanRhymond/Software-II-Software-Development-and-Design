import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine3;
import components.utilities.Reporter;

/**
 * Put a short phrase describing the program here.
 *
 * @author Jonathan Rhymond & Suya Dulal
 *
 */
public final class TagCloudGenerator {

    // Constants for font size mapping
    /**
     * Minimum font size.
     */
    private static final int FONTMIN = 10;

    // Initial values for word occurrences
    /**
     * Maximum font size.
     */
    private static final int FONTMAX = 50;

    /**
     * Minimum amount of word occurrences.
     */
    private static int WORDMIN = Integer.MAX_VALUE;

    /**
     * Maximum amount of word occurrences.
     */
    private static int WORDMAX = 0;

    /**
     * Separator string.
     */
    private static final String SEPARATORS = " \t\n\r\\|:;'\",.<>/?`~!@%^&#$*()+-_={}[]";

    /**
     * This class implements a comparator for comparing pairs of strings and
     * integers in a case-insensitive alphabetical order based on the string
     * keys.
     *
     * The comparison is performed using the {@code compareToIgnoreCase} method
     * of the string keys.
     *
     * @param <String>
     *            the type of the first element in the pair
     * @param <Integer>
     *            the type of the second element in the pair
     */
    public static class AlphabeticalComparator
            implements Comparator<Map.Pair<String, Integer>> {

        @Override
        public int compare(Pair<String, Integer> arg0,
                Pair<String, Integer> arg1) {
            return arg0.key().compareToIgnoreCase(arg1.key());
        }

    }

    /**
     * This class implements a comparator for comparing pairs of strings and
     * integers in descending order based on the integer values.
     *
     * The comparison is performed using the {@code compareTo} method of the
     * integer values.
     *
     * @param <String>
     *            the type of the first element in the pair
     *
     * @param <Integer>
     *            the type of the second element in the pair
     *
     */
    public static class CompareArgs
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Pair<String, Integer> arg0,
                Pair<String, Integer> arg1) {
            return arg1.value().compareTo(arg0.value());
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * Generates a map containing the count of each unique word in the input,
     * based on the given SimpleReader.
     *
     * The method reads input from the provided SimpleReader and processes each
     * word, updating the count in the resulting map. Words are identified using
     * the nextWordOrSeparator method.
     *
     * @param input
     *            the SimpleReader from which to read input
     * @return a map containing the count of each unique word in the input
     * @requires input is not null and properly initialized
     * @ensures the resulting map contains unique words as keys and their counts
     *          as values
     */
    public static Map<String, Integer> getCountMap(SimpleReader input) {

        int index = 0;

        // Map of strings and their counts
        Map<String, Integer> countMap = new Map1L<String, Integer>();

        String s = "";

        while (!input.atEOS()) {
            s += " ";
            s += input.nextLine();

        }

        // Loop through the input text to count occurrences of each unique word
        while (index < s.length() - 1) {
            String nextWord = nextWordOrSeparator(s, index);
            index += nextWord.length();
            // Check if the word is not in the map and not a separator
            if (!countMap.hasKey(nextWord)
                    && !SEPARATORS.contains(nextWord.substring(0, 1))) {
                countMap.add(nextWord, 1);
            } else if (!SEPARATORS.contains(nextWord.substring(0, 1))) {
                // If the word is already in the map, increment its count
                countMap.replaceValue(nextWord, countMap.value(nextWord) + 1);
            }
//         // Check if the word is not in the map and not a separator - //'corrected'
//            if (!countMap.hasKey(nextWord) && !countMap.hasKey(nextWord.toLowerCase())
//                    && !SEPARATORS.contains(nextWord.substring(0, 1))) {
//                countMap.add(nextWord.toLowerCase(), 1);
//            } else if (!SEPARATORS.contains(nextWord.substring(0, 1))) {
//                // If the word is already in the map, increment its count
//                countMap.replaceValue(nextWord.toLowerCase(), countMap.value(nextWord) + 1);
//            }
        }

        return countMap;

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code SEPARATORS}) or "separator string" (maximal length string of
     * characters in {@code SEPARATORS}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires text is not null, 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection SEPARATORS = {}
     * then
     *   entries(nextWordOrSeparator) intersection SEPARATORS = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection SEPARATORS /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of SEPARATORS  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of SEPARATORS)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position) {
        assert text != null : "Violation of: text is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int lastPosition = position + 1;
        int length = text.length();

        String result = text.substring(position, position + 1);

        // Check if the character at the starting position is a separator
        if (SEPARATORS.indexOf(text.charAt(position)) > 1) {
            for (int i = lastPosition; i < length
                    && SEPARATORS.indexOf(text.charAt(i)) > 0; i++) {
                // Expand the result to include the separator characters
                result = text.substring(position, i + 1);
            }
        } else if (SEPARATORS.indexOf(text.charAt(position)) < 0) {
            // Check if the character at the starting position is not a separator
            for (int i = lastPosition; i < length
                    && SEPARATORS.indexOf(text.charAt(i)) < 0; i++) {
                // Expand the result to include the word characters
                result = text.substring(position, i + 1);
            }
        }
        return result;

    }

    /**
     * Generates a sorted queue of pairs (String, Integer) from the given map.
     *
     * The method takes a map containing pairs of strings and integers, removes
     * pairs from the map, and enqueues them into a queue. The resulting queue
     * is then sorted in alphabetical order based on the string keys using the
     * AlphabeticalComparator.
     *
     * @param map
     *            the map containing pairs of strings and integers
     * @return a sorted queue of pairs (String, Integer) based on alphabetical
     *         order
     * @requires map is not null and properly initialized
     * @ensures the resulting queue is sorted in alphabetical order based on the
     *          string keys
     */
    public static Queue<Map.Pair<String, Integer>> getSortedQueue(
            Map<String, Integer> map) {
        Queue<Map.Pair<String, Integer>> pairs = new Queue1L<>();

        // Enqueue all pairs from the map into the queue
        while (map.size() > 0) {
            pairs.enqueue(map.removeAny());
        }
        pairs.sort(new AlphabeticalComparator());
        return pairs;

    }

    /**
     * Outputs the HTML header for a word counting report.
     *
     * The method takes a SimpleWriter, a file name, and the number of words,
     * and outputs the corresponding HTML header with title, style sheet link,
     * and heading.
     *
     * @param out
     *            the SimpleWriter for output
     * @param fileName
     *            the name of the file being processed
     * @param wordsNum
     *            the number of words counted
     * @requires out is not null and properly initialized
     * @ensures the HTML header is output to the SimpleWriter
     */
    public static void outputHeader(SimpleWriter out, String fileName,
            int wordsNum) {

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Counting " + wordsNum + " words from: " + fileName
                + "</title>");
        // Link to an external style sheet for formatting
        out.println("<link href=\"http://web.cse.ohio-state.edu/software/2231/"
                + "web-sw2/assignments/projects"
                + "/tag-cloud-generator/data/tagcloud.css\""
                + " rel=\"stylesheet\"" + " type=\"text/css\">"
                + "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");

        out.println("<body>");
        out.println("<h2>Counting " + wordsNum + " words from: " + fileName
                + "</h2>");
        out.println("<hr>");

    }

    /**
     * Calculates the font size for a word based on a linear mapping function.
     *
     * The method takes an input value x and calculates the corresponding font
     * size using a linear mapping function. The mapping is based on the input
     * range [WORDMIN, WORDMAX] and the desired output range [MIN, MAX].
     *
     * @param x
     *            the input value used for font size calculation
     * @return the calculated font size based on the linear mapping function
     * @requires WORDMIN <= x <= WORDMAX
     * @ensures MIN <= result <= MAX
     */
    public static int fontSize(int x) {

        return (((FONTMAX - FONTMIN) * (x - WORDMIN)) / (WORDMAX - WORDMIN))
                + FONTMIN;
    }

    /**
     * Outputs a word cloud based on the given SortingMachine of word-frequency
     * pairs.
     *
     * The method takes a SortingMachine containing word-frequency pairs, a
     * SimpleWriter for output, the name of the file being processed, and the
     * total number of words. It processes the input SortingMachine, extracts
     * the required number of word-frequency pairs, calculates font sizes, and
     * outputs an HTML representation of the word cloud.
     *
     * @param sm
     *            the SortingMachine containing word-frequency pairs
     * @param out
     *            the SimpleWriter for output
     * @param fileName
     *            the name of the file being processed
     * @param wordsNum
     *            the total number of words
     * @requires sm is not null and properly initialized, out is not null and
     *           properly initialized
     * @ensures the word cloud HTML representation is output to the SimpleWriter
     */
    public static void outputWordCloud(
            SortingMachine<Map.Pair<String, Integer>> sm, SimpleWriter out,
            String fileName, int wordsNum) {

        out.println("<div class = \"cdiv\">");
        out.println("<p class = \"cbox\">");

        SortingMachine<Map.Pair<String, Integer>> sm2 = new SortingMachine3<>(
                new AlphabeticalComparator());

        Map<String, Integer> countMap = new Map1L<String, Integer>();

        int count = 0;
        // Extract required number of word-frequency pairs from sm
        while (count < wordsNum) {
            Map.Pair<String, Integer> removed = sm.removeFirst();
            sm2.add(removed);
            countMap.add(removed.key().toLowerCase(), removed.value());
            //countMap.add(removed.key(), removed.value()); - //'corrected'
            count++;
        }

        sm2.changeToExtractionMode();

        // Update WORDMIN and WORDMAX based on the counts in countMap
        for (Map.Pair<String, Integer> m : countMap) {
            if (m.value() < WORDMIN) {
                WORDMIN = m.value();
            }

            if (m.value() > WORDMAX) {
                WORDMAX = m.value();
            }
        }

        // Output each word with font size based on count using fontSize method
        while (sm2.size() > 0) {
            Map.Pair<String, Integer> removed = sm2.removeFirst();
            int fontsize = fontSize(removed.value());
            out.println("<span style=\"cursor:default\"class=\"" + "f"
                    + fontsize + "\" title=\"count: " + removed.value() + "\">"
                    + removed.key().toLowerCase() + "</span>");
        }

    }

    /**
     * Outputs the HTML footer for the word cloud report.
     *
     * The method takes a SimpleWriter and outputs the closing HTML tags for the
     * body and HTML document, completing the structure of the word cloud
     * report.
     *
     * @param out
     *            the SimpleWriter for output
     * @requires out is not null and properly initialized
     * @ensures the HTML footer is output to the SimpleWriter
     */
    public static void outputFooter(SimpleWriter out) {

        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }

    /**
     * The main method of the word cloud generator program.
     *
     * This method interacts with the user to obtain input for file names and
     * the desired number of words for the word cloud. It then reads the input
     * file, generates a word count map, sorts it based on word frequency, and
     * outputs an HTML representation of the word cloud to the specified output
     * file.
     *
     * @param args
     *            command-line arguments (not used in this program)
     * @requires User interaction to provide input file name, output file name,
     *           and number of words for the word cloud
     * @ensures Word cloud HTML representation is generated and saved to the
     *          output file based on the provided input file and word count
     *          criteria
     */
    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Prompt user for input file name
        out.print("Enter a file name: ");
        String fileName = in.nextLine();
        out.println();

        // Read from the input file
        SimpleReader fileIn = new SimpleReader1L(fileName);
        // Check for blank file
        if (fileIn.nextLine() == "") {
            Reporter.assertElseFatalError(fileIn.nextLine() != "",
                    "Blank File");
//            System.out.println("Yay, found"); //to see if finds error
        }

        // Prompt user for output file name
        out.print("Enter an output file name: ");
        String outputFileName = in.nextLine();
        out.println();

        // Prompt user for the number of words in the word cloud
        out.print("How many words in cloud: ");
        int wordsNum = in.nextInteger();
        out.println();

        // Check for non-positive number of words
        Reporter.assertElseFatalError(wordsNum >= 0, "Non-Positive number");

        // Create SimpleWriter for output
        SimpleWriter fileOut = new SimpleWriter1L(outputFileName);

        // Generate word count map from the input
        Map<String, Integer> countMap = getCountMap(fileIn);

        // Create a SortingMachine to sort word-frequency pairs
        SortingMachine<Map.Pair<String, Integer>> sm = new SortingMachine3<>(
                new CompareArgs());

        // Add all word-frequency pairs to the SortingMachine
        while (countMap.size() > 0) {
            sm.add(countMap.removeAny());
        }
        sm.changeToExtractionMode();

        // Output HTML body via 3 methods
        outputHeader(fileOut, fileName, wordsNum);
        outputWordCloud(sm, fileOut, fileName, wordsNum);
        outputFooter(fileOut);

        // Close the input and output streams
        fileIn.close();
        fileOut.close();
        in.close();
        out.close();

    }

}