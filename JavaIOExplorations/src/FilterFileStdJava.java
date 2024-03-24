import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class FilterFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FilterFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        String inFile = args[0];
        String outFile = args[1];
        String stringsFile = args[2];

        BufferedReader input;
        BufferedReader stringsInput;
        PrintWriter output;

        Collection<String> stringSet = new HashSet<String>();

        try {
            input = new BufferedReader(new FileReader(inFile));
            stringsInput = new BufferedReader(new FileReader(stringsFile));
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(outFile)));
        } catch (IOException e) {
            System.err.println("Error opening file(s)");
            return;
        }
        try {
            String line = stringsInput.readLine();
            while (line != null) {
                stringSet.add(line);
                line = stringsInput.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading file");
        }
        try {
            String s = input.readLine();
            while (s != null) {
                if (stringSet.contains(s)) {
                    output.println(s);
                }
                s = input.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading or writing to file");
        }
        try {
            input.close();
            output.close();
            stringsInput.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }
    }

}
