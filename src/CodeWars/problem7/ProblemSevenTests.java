package CodeWars.problem7;

import java.util.*;
import java.util.regex.*;
import java.util.function.*;
import java.io.*;

import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.*;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ProblemSevenTests {

    // TESTS WERE ADDED AFTER THE KATA WAS COMPLETED
    // This was done to troubleshoot an issue with the tests,
    // not to give me an unfair advantage.
    @RunWith(Parameterized.class)
    public static class MathEvaluatorTest {

        private static final Object[][] tests = {
                {"12*-1", -12d},
                {"12* 123/-(-5 + 2)", 492d},
                {"((80 - (19)))", 61d},
                {"(1 - 2) + -(-(-(-4)))", 3d},
                {"1 - -(-(-(-4)))", -3d},
                {"12* 123/(-5 + 2)", -492d},
                {"(123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) - (123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) + (13 - 2)/ -(-11) ", 1d},
                {"1+1", 2d},
                {"1 - 1", 0d},
                {"1* 1", 1d},
                {"1 /1", 1d},
                {"-123", -123d},
                {"123", 123d},
                {"2 /2+3 * 4.75- -6", 21.25},
                {"12* 123", 1476d},
                {"12 * -123", -1476d},
                {"2 / (2 + 3) * 4.33 - -6", 7.732},
                {"((2.33 / (2.9+3.5)*4) - -6)", 7.45625},
                {"123.45*(678.90 / (-2.5+ 11.5)-(80 -19) *33.25) / 20 + 11", -12042.760875},
        };

        @Parameters
        public static Iterable<Object[]> tests() {
            return Arrays.asList(tests);
        }

        private final String expression;
        private final Double result;

        public MathEvaluatorTest(String expression, Double result) {
            this.expression = expression;
            this.result = result;
        }

        @Test
        public void test() {
            ProblemSeven evaluator = new ProblemSeven();
            assertEquals(result, evaluator.calculate(expression), 0.01);
        }
    }


    public static class RestrictedApiTest {
        @Test
        public void restrictedApisTest() {

            String SOLUTION_PATH = "src/CodeWars/problem7/testbuffer.txt";
            Set<String> DISALLOWED_STRINGS = new HashSet<>(Arrays.asList("reflect", "runtime", "process", "script", "jshell"));
            Pattern p = Pattern.compile("(?<=^|[^\\\\])(?:\\\\{2})*+\\\\u+([\\da-fA-F]{4})");

            UnaryOperator<String> unescape = s-> {
                Matcher m = p.matcher(s);
                StringBuffer sb = new StringBuffer();
                while(m.find()) {
                    String seq = m.group(1);
                    char c = (char)Integer.parseInt(seq, 16);
                    m.appendReplacement(sb, "");
                    sb.append(c);
                }
                m.appendTail(sb);
                return sb.toString();
            };

            try(FileReader fr = new FileReader(SOLUTION_PATH);
                BufferedReader reader = new BufferedReader(fr)) {

                Optional<String> incorrectLine = reader.lines().map(unescape).filter(l -> { String line = l.toLowerCase(); return DISALLOWED_STRINGS.stream().anyMatch(d -> line.contains(d));}).findAny();
                if(incorrectLine.isPresent()) {
                    String disallowedWord = DISALLOWED_STRINGS.stream().filter(word -> incorrectLine.get().toLowerCase().contains(word)).findAny().get();
                    fail(String.format("Not allowed string [%s] found in line [%s]", disallowedWord, incorrectLine.get()));
                }

            } catch(Exception ex) {
                ex.printStackTrace();
                fail("Test for disallowed APIs failed with an exception. Please raise a kata issue and provide the exception stack trace.");
            }
        }
    }
}