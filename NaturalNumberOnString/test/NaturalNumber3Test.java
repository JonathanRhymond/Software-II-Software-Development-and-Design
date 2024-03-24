import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber1L();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber1L(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber1L(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        // TODO - fill in body

        // This line added just to make the component compilable.
        return new NaturalNumber1L(n);
    }

}
