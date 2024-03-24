import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author J. Rhymond & S. Dulal
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     * Tests the string constructor of NaturalNumber3 by comparing it to a
     * reference constructor.
     *
     * @ensures conTest and conRef are equal
     */
    @Test
    public void stringConstructorTest() {
        NaturalNumber conTest = this.constructorTest("2");
        NaturalNumber conRef = this.constructorRef("2");
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the string constructor of NaturalNumber3 by comparing it to a
     * reference constructor.
     *
     * @ensures conTest and conRef are equal
     */
    @Test
    public void stringConstructorTest2() {
        NaturalNumber conTest = this.constructorTest("25834");
        NaturalNumber conRef = this.constructorRef("25834");
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the int constructor of NaturalNumber3 by comparing it to a
     * reference constructor.
     *
     * @ensures conTest and conRef are equal
     */
    @Test
    public void intConstructorTest() {
        NaturalNumber conTest = this.constructorTest(2);
        NaturalNumber conRef = this.constructorRef(2);
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the int constructor of NaturalNumber3 by comparing it to a
     * reference constructor.
     *
     * @ensures conTest and conRef are equal
     */
    @Test
    public void intConstructorTest2() {
        NaturalNumber conTest = this.constructorTest(2433434);
        NaturalNumber conRef = this.constructorRef(2433434);
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the NaturalNumber constructor of NaturalNumber3 by comparing it to
     * a reference constructor.
     *
     * @ensures conTest and conRef are equal
     */
    @Test
    public void naturalNumberConstructorTest() {
        NaturalNumber one = new NaturalNumber2(1);

        NaturalNumber conTest = this.constructorTest(one);
        NaturalNumber conRef = this.constructorRef(one);
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the NaturalNumber constructor of NaturalNumber3 by comparing it to
     * a reference constructor.
     *
     * @ensures conTest and conRef are equal
     */
    @Test
    public void naturalNumberConstructorTest2() {
        NaturalNumber eightyFive = new NaturalNumber2(85);

        NaturalNumber conTest = this.constructorTest(eightyFive);
        NaturalNumber conRef = this.constructorRef(eightyFive);
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the empty constructor of NaturalNumber3 by comparing it to a
     * reference constructor.
     *
     * @ensures conTest and conRef are equal
     */
    @Test
    public void emptyConstructorTest() {
        NaturalNumber conTest = this.constructorTest();
        NaturalNumber conRef = this.constructorRef();

        assertTrue(conTest.compareTo(conRef) == 0);
    }

    /**
     * Tests the multiplyBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void multiplyBy10ConTest() {
        NaturalNumber conTest = this.constructorTest(2);
        NaturalNumber conRef = this.constructorRef(2);
        conTest.multiplyBy10(2);
        conRef.multiplyBy10(2);
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the multiplyBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void multiplyBy10ConTest2() {
        NaturalNumber conTest = this.constructorTest(3556);
        NaturalNumber conRef = this.constructorRef(3556);
        conTest.multiplyBy10(4);
        conRef.multiplyBy10(4);
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the multiplyBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void multiplyBy10ConTest3() {
        NaturalNumber conTest = this.constructorTest(10);
        NaturalNumber conRef = this.constructorRef(10);
        conTest.multiplyBy10(1);
        conRef.multiplyBy10(1);
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the divideBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void divideBy10Test() {
        NaturalNumber conTest = this.constructorTest(20);
        NaturalNumber conRef = this.constructorRef(20);
        conTest.divideBy10();
        conRef.divideBy10();
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the divideBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void divideBy10Test2() {
        NaturalNumber conTest = this.constructorTest(100000);
        NaturalNumber conRef = this.constructorRef(100000);
        conTest.divideBy10();
        conRef.divideBy10();
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the divideBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void divideBy10Test3() {
        NaturalNumber conTest = this.constructorTest(3);
        NaturalNumber conRef = this.constructorRef(3);
        conTest.divideBy10();
        conRef.divideBy10();
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the divideBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void divideBy10Test4() {
        NaturalNumber conTest = this.constructorTest(47);
        NaturalNumber conRef = this.constructorRef(47);
        conTest.divideBy10();
        conRef.divideBy10();
        assertTrue((conRef.equals(conTest)));

    }

    /**
     * Tests the divideBy10 method in NaturalNumber3, comparing it to a
     * reference method.
     *
     * @ensures conTest and conRef are equal after multiplication
     */
    @Test
    public void isZero() {
        NaturalNumber conTest = this.constructorTest(0);
        NaturalNumber conRef = this.constructorRef(0);
        conTest.isZero();
        conRef.isZero();
        assertTrue((conRef.equals(conTest)));

    }
}
