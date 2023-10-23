package CodeWars.problem7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class ProblemSevenTests {
    @Test
    void tokenizerAssertEquals1() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"1", "-", "1"};
        String[] actual = p.tokenize("1-1");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }

    @Test
    void tokenizerAssertEquals2() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"1", "-", "1"};
        String[] actual = p.tokenize("1 -1");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }    @Test
    void tokenizerAssertEquals3() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"1", "-", "1"};
        String[] actual = p.tokenize("1 - 1");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }    @Test
    void tokenizerAssertEquals4() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"1", "-", "-", "1"};
        String[] actual = p.tokenize("1- -1");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }    @Test
    void tokenizerAssertEquals5() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"1", "-","-", "1"};
        String[] actual = p.tokenize("1 - -1");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }    @Test
    void tokenizerAssertEquals6() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"1", "-","-", "1"};
        String[] actual = p.tokenize("1--1");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }    @Test
    void tokenizerAssertEquals7() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"6", "+", "-", "(", "4", ")"};
        String[] actual = p.tokenize("6 + -(4)");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }    @Test
    void tokenizerAssertEquals8() {
        ProblemSeven p = new ProblemSeven();

        String[] expected = new String[]{"6", "+", "-", "(", "-", "4", ")"};
        String[] actual = p.tokenize("6 + -( -4)");

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Arrays do not match: Expected " + Arrays.toString(expected) + " but was " + Arrays.toString(actual));
        }
    }

}
