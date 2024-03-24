import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * Generates a tag cloud to a html file from a given input text using Standard
 * Java components.
 *
 * @author Jonathan Rhymond & Suya Dulal
 */
public final class TagCloudGeneratorStandard {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TagCloudGeneratorStandard() {
        // no code needed here
    }

    /**
     * String of all the possible word separators.
     */
    private static final String SEPARATORS = " \t\n\r,-.!?[]';:/()";

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
    private static class AlphabeticalComparator
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            int compare = o1.getKey().compareToIgnoreCase(o2.getKey());
            if (compare == 0) {
                compare = o2.getValue().compareTo(o1.getValue());
            }
            return compare;
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
    private static class CompareArgs
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            int compare = o2.getValue().compareTo(o1.getValue());
            if (compare == 0) {
                compare = o1.getKey().compareTo(o2.getKey());
            }
            return compare;
        }
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
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection entries(SEPARATORS) = {}
     * then
     *   entries(nextWordOrSeparator) intersection entries(SEPARATORS) = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection entries(SEPARATORS) /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of entries(SEPARATORS)  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of entries(SEPARATORS))
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position) {
        assert text != null : "Violation of: text is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int i = position + 1;
        while (i < text.length()
                && SEPARATORS.indexOf(text.charAt(position)) == SEPARATORS
                        .indexOf(text.charAt(i))) {
            i++;
        }
        return text.substring(position, i);
    }

    /**
     * Read input files, stores words into the pair.
     *
     * @param line
     *            read from the text
     * @param entries
     *            map which includes word and its count
     * @requires |line| > 0
     */
    private static void getCountMap(String line, Map<String, Integer> entries) {
        int position = 0;
        while (position < line.length()) {
            String word = nextWordOrSeparator(line, position).toLowerCase();
            if (SEPARATORS.indexOf(word.charAt(0)) < 0) {
                if (!entries.containsKey(word)) {
                    entries.put(word, 1);
                } else {
                    int value = entries.get(word);
                    entries.put(word, value + 1);

                }
            }
            position += word.length();
        }
    }

    /**
     * Put all keys and associated values from the map into a new PriorityQueue.
     *
     * @param entries
     *            Map for all the words and counts
     * @param comp
     *            Comparator
     * @return PriorityQueue sorted
     *
     */
    private static PriorityQueue<Map.Entry<String, Integer>> sortWords(
            Map<String, Integer> entries,
            Comparator<Map.Entry<String, Integer>> comp) {
        PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue(
                entries.size(), comp);

        Set<Map.Entry<String, Integer>> keySet = entries.entrySet();
        for (Iterator<Map.Entry<String, Integer>> it = keySet.iterator(); it
                .hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            q.add(entry);
        }
        return q;
    }

    /**
     * Generate and print out the tag cloud in the HTML file. Words should be in
     * alphabetical order and with different fonts depend on number of
     * appearances.
     *
     * @param outFile
     *            SimpleWriter
     * @param q
     *            PriorityQueue which stores the words and number of appearances
     * @param NumberOfWords
     *            Number of words are expected to print out in the HTMl file
     * @param fileName
     *            The name of the HTML file
     * @param min
     *            Minimum appearances in the SortingMachine
     * @param max
     *            Maximum appearances in the SortingMachine
     *
     */
    private static void outputFullWordCloud(PrintWriter outFile,
            PriorityQueue<Map.Entry<String, Integer>> q, int NumberOfWords,
            String fileName, int min, int max) {

        //print header
        outFile.write("<html>\n<head>\n<title>" + "Top " + NumberOfWords
                + " words in " + fileName + "</title>\n");
        outFile.write(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\""
                        + "rel=\"stylesheet\" type=\"text/css\">\n");
        outFile.write("</head>\n</body>\n");
        outFile.write("<h2>" + "Top " + NumberOfWords + " words in " + fileName
                + "</h2>\n");
        outFile.write(
                "<hr>\n" + "<div class=\"cdiv\">\n" + "<p class=\"cbox\">\n");

        //print out words in order with different sizes
        int size = q.size();
        int minFont = 11;
        int scale = 37;
        int font;
        for (int i = 0; i < size; i++) {
            Map.Entry<String, Integer> temp = q.remove();
            if (max != min) {
                font = (temp.getValue() - min) * scale / (max - min) + minFont;
            } else {
                font = minFont;
            }
            outFile.write("<span style=\"cursor:default\" class=\"f" + font
                    + "\" title=\"count: " + temp.getValue() + "\">"
                    + temp.getKey() + "</span>\n");
        }

        //print footer
        outFile.write("</p>\n</div>\n</body>\n</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the input file: ");
        String inputFile = in.nextLine();
        System.out.print("Enter the output file: ");
        String outputFile = in.nextLine();

        BufferedReader read;
        try {
            read = new BufferedReader(new FileReader(inputFile));
        } catch (IOException e) {
            System.err.println("Error opening file.");
            in.close();
            return;
        }
        PrintWriter print = null;
        try {
            print = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFile)));
        } catch (IOException e) {
            System.err.println("Error creating output file.");
        }
        try {
            if (print != null) {
                System.out.print("Enter number of words: ");
                int n = in.nextInt();

                Map<String, Integer> entries = new HashMap<>();
                String line = read.readLine();
                while (read.readLine() != null) {
                    getCountMap(line, entries);
                    line = read.readLine();
                }

                // Create comparator to sort
                Comparator<Map.Entry<String, Integer>> wordCount = new CompareArgs();
                // Words in decreasing order
                PriorityQueue<Map.Entry<String, Integer>> decreaseWords = sortWords(
                        entries, wordCount);

                //alphabetical order comparator
                Comparator<Map.Entry<String, Integer>> alphabetSort = new AlphabeticalComparator();
                PriorityQueue<Map.Entry<String, Integer>> sorted = new PriorityQueue<>(
                        entries.size(), alphabetSort);

                int min = 0, max = 0;
                if (n >= 2) {
                    // Remove the sorted words
                    Map.Entry<String, Integer> temp = decreaseWords.remove();
                    max = temp.getValue();
                    sorted.add(temp);
                    for (int i = 0; i < (n - 2); i++) {
                        temp = decreaseWords.remove();
                        sorted.add(temp);
                    }
                    temp = decreaseWords.remove();
                    min = temp.getValue();
                    sorted.add(temp);
                } else if (n == 1) {
                    Map.Entry<String, Integer> temp = decreaseWords.remove();
                    sorted.add(temp);
                    max = temp.getValue();
                }
                outputFullWordCloud(print, sorted, n, inputFile, min, max);
                print.close();
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
        }
        in.close();
        try {
            read.close();
        } catch (IOException e) {
            System.err.println("Error closing input file.");
        }
    }

}
