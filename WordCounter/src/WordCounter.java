import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author J. Rhymond
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     * Alphabetical comparator for strings.
     *
     * @implements Comparator<String>
     * @param o1
     *            The first string to compare.
     * @param o2
     *            The second string to compare.
     * @return A negative integer, zero, or a positive integer as the first
     *         string is less than, equal to, or greater than the second string.
     * @requires o1 and o2 are not null.
     * @ensures The comparison is performed in a case-insensitive manner. If the
     *          case-insensitive comparison is equal, the original order is
     *          used.
     */
    public static class AlphabeticalComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    /**
     * Returns the next word or separator starting at the given position in the
     * text.
     *
     * @param text
     *            The input text.
     * @param position
     *            The starting position for extraction.
     * @param separators
     *            A set of characters considered as separators.
     * @return The next word or separator starting at the given position.
     * @requires text and separators are not null, 0 <= position, position <
     *           |text|.
     * @ensures The returned string contains characters starting from the given
     *          position until a separator is encountered (or the end of the
     *          text is reached).
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String output = "";
        int count = 0;
        char returned = ' ';

        if (separators.contains(text.charAt(position))) {
            while (count < text.substring(position, text.length()).length()) {
                returned = text.charAt(position + count);
                if (separators.contains(text.charAt(position + count))) {
                    output += returned;
                    count++;
                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
            count = 0;
        } else {
            while (count < text.substring(position, text.length()).length()) {
                returned = text.charAt(position + count);
                if (!separators.contains(text.charAt(position + count))) {
                    output += returned;
                    count++;
                } else {
                    count = text.substring(position, text.length()).length();
                }
            }
        }
        return output;
    }

    /**
     * Generates a generic HTML header with the specified title.
     *
     * @param out
     *            The output stream to write the HTML header to.
     * @param title
     *            The title to be displayed in the HTML header.
     * @requires out is not null.
     * @ensures A generic HTML header is written to the output stream, including
     *          the specified title.
     */
    public static void generateHTMLHeader(SimpleWriter out, String title) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }

    /**
     * Generates a generic HTML footer.
     *
     * @param out
     *            The output stream to write the HTML footer to.
     * @requires out is not null.
     * @ensures A generic HTML footer is written to the output stream.
     */
    public static void generateHTMLFooter(SimpleWriter out) {
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Main method.
     *
     * @param <V>
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static <V> void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        //set of reasonable separators
        Set<Character> seperatorSet = new Set1L<>();
        seperatorSet.add(' ');
        seperatorSet.add('/');
        seperatorSet.add('-');
        seperatorSet.add('_');
        seperatorSet.add('&');
        seperatorSet.add('.');
        seperatorSet.add(',');
        seperatorSet.add('!');
        seperatorSet.add('?');
        seperatorSet.add('\"');
        seperatorSet.add('\'');
        seperatorSet.add(';');
        seperatorSet.add(':');

        //requests information from user on input file and save location
        out.print("Enter the name of the input file: ");
        String input = in.nextLine();
        out.println();
        out.print("Enter the name of the output file: ");
        String outputFile = in.nextLine();
        out.println();

        //creates SimpleReader for input file
        SimpleReader fileReader = new SimpleReader1L(input);

        //creates SimpleWriter for output file
        SimpleWriter filePrinter = new SimpleWriter1L(outputFile);

        //creates map for terms and number of times they appear
        Map<String, Integer> termsCount = new Map1L<>();

        //creates queues for terms (sortable with comparator)
        Queue<String> wordsList = new Queue1L<>();
        Queue<String> wordsListCopy = new Queue1L<>();

        //loop to read and count terms
        while (!fileReader.atEOS()) {
            String currentLine = fileReader.nextLine();

            int index = 0;
            String currentWord = "";
            while (index < currentLine.length()) {
                currentWord = nextWordOrSeparator(currentLine, index,
                        seperatorSet);
                if (termsCount.hasKey(currentWord)) {
                    int tempCount = termsCount.value(currentWord);
                    tempCount++;
                    termsCount.remove(currentWord);
                    termsCount.add(currentWord, tempCount);
                } else {
                    termsCount.add(currentWord, 1);
                    boolean containsSeparator = false;
                    for (char c : currentWord.toCharArray()) {
                        if (seperatorSet.contains(c)) {
                            containsSeparator = true;
                            break;
                        }
                    }
                    if (!containsSeparator) {
                        wordsList.enqueue(currentWord);
                        wordsListCopy.enqueue(currentWord);
                    }
                }
                index += currentWord.length();
            }
        }

        //sorts wordsList with comparator
        Comparator<String> comparator = new AlphabeticalComparator();
        wordsList.sort(comparator);

        //creates output file header
        generateHTMLHeader(filePrinter, outputFile);

        //prints body header
        filePrinter.print("<h2>");
        filePrinter.print("Words Counted in: " + input);
        filePrinter.println("<h2>");
        filePrinter.println("<hr>");

        //opens body table
        filePrinter.println("<table border = \"1\">");
        filePrinter.println("<tbody>");
        filePrinter.println("<tr>");
        filePrinter.print("<th>");
        filePrinter.print("Words");
        filePrinter.println("</th>");
        filePrinter.print("<th>");
        filePrinter.print("Counts");
        filePrinter.println("</th>");
        filePrinter.println("</tr>");

        //loop to complete body table
        for (int i = 0; i < wordsListCopy.length(); i++) {
            String currentWord = wordsList.dequeue();
            int currentCount = termsCount.value(currentWord);

            filePrinter.println("<tr>");
            filePrinter.print("<td>");
            filePrinter.print(currentWord);
            filePrinter.print("</td>");
            filePrinter.print("<td>");
            filePrinter.print(currentCount);
            filePrinter.print("</td>");
            filePrinter.println("</tr>");
        }

        //closes body table
        filePrinter.println("</tbody>");
        filePrinter.println("</table>");

        //generates output file footer
        generateHTMLFooter(filePrinter);

        //close readers and writers
        out.close();
        in.close();
        fileReader.close();
        filePrinter.close();
    }

}
