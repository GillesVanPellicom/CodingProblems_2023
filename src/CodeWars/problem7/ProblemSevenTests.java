package CodeWars.problem7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class ProblemSevenTests {
    @Test
    void lexerAssertEquals() {
        ProblemSeven p = new ProblemSeven();

        List<Lexeme> expected = Arrays.asList(
                new Lexeme(ProblemSeven.Type.NUMBER, 6),
                new Lexeme(ProblemSeven.Type.PLUS),
                new Lexeme(ProblemSeven.Type.UMIN),
                new Lexeme(ProblemSeven.Type.LBRAC),
                new Lexeme(ProblemSeven.Type.UMIN),
                new Lexeme(ProblemSeven.Type.NUMBER, 4),
                new Lexeme(ProblemSeven.Type.RBRAC)
        );
        List<Lexeme> actual = p.lexer("6 + -( -4)");


        String error = lexerCmp(actual, expected);
        if (!error.isEmpty()) {
            throw new AssertionError(error);
        }
    }

    static String lexerCmp(List<Lexeme> actual, List<Lexeme> expected) {
        String error = "";
        ProblemSeven.Type failTypeAct;
        ProblemSeven.Type failTypeExp;
        double failDoubleAct;
        double failDoubleExp;


        for (int i = 0; i < actual.size(); i++) {
            Lexeme act = actual.get(i);
            Lexeme exp = expected.get(i);
            if (act.value != exp.value) {
                failDoubleAct = act.value;
                failDoubleExp = exp.value;
                error = "Expected and actual values do not match. Actual: " + failDoubleAct + " Expected: " + failDoubleExp + ".";
                break;
            }

            if (act.type != exp.type) {
                failTypeAct = act.type;
                failTypeExp = exp.type;
                error = "Expected and actual values do not match. Actual: " + failTypeAct + " Expected: " + failTypeExp + ".";
                break;
            }
        }
        return error;
    }

}
