import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    /*
     * String for first name
     */
    private String firstName;

    /*
     * String for last name
     */
    private String lastName;

    /*
     * String for email address
     */
    private String email;

    /*
     * Integer of dotValue
     */
    private int dotNum;

    /*
     * Map of all last names and their dotNum
     */
    private static Map<String, Integer> database = new Map1L<String, Integer>();

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        String lastNameLower = lastName.toLowerCase();
        if (EmailAccount1.database.hasKey(lastNameLower)) {
            int num = EmailAccount1.database.value(lastNameLower);
            this.dotNum = num + 1;
            EmailAccount1.database.replaceValue(lastNameLower, this.dotNum);
        } else {
            this.dotNum = 1;
            EmailAccount1.database.add(lastNameLower, 1);
        }

    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        return (this.firstName + " " + this.lastName);
    }

    @Override
    public String emailAddress() {

        return (this.lastName.toLowerCase() + "." + this.dotNum + "@osu.edu");
    }

    @Override
    public String toString() {

        return ("Name: " + this.name() + ", Email: " + this.emailAddress());
    }

}
