package CodeWars.problem7;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemSeven {
    public static void main(String[] args) {
        ProblemSeven p = new ProblemSeven();
        System.out.println(p.calculate("(2 / (2 + 3.33) * 4)--6"));
    }

    /**
     * Calculate mathematical expression
     * @param expression Expression to be calculated.
     * @return Result as double.
     */
    public double calculate(String expression) {
        return this.postfixEvaluator(this.shuntingYard(this.lexer(expression)));
    }

    // Lexeme type to regex string
    private Map<Type, String> tokenDefinitions = new HashMap<>();

    /**
     * Lexeme types
     */
    enum Type {
        // "[0-9]+(\.[0-9]+)?" E.g. 12, 3.14, 9999
        NUMBER,
        // "+" E.g. a + b
        PLUS,
        // "-" E.g. a - b
        MIN,
        // "*" E.g. a * b
        MUL,
        // "/" E.g. a / b
        DIV,
        // "^" E.g. a ^ b
        POW,
        // "-" E.g. -b (Unary minus)
        UMIN,
        // "(" E.g. (12)
        LBRAC,
        // ")" E.g. (12)
        RBRAC

    }

    /**
     * Splits up expression string into Lexemes.
     * Lex = <type, value>
     * @param input Expression string.
     * @return Lexemes as list
     */
    public List<Lexeme> lexer(String input) {
        // If this is first lexer() call, initialize lexer rules
        if (tokenDefinitions.isEmpty()) {
            initLexer();
        }

        // Divide string into tokens
        Pattern pattern = Pattern.compile("([0-9]+(\\.[0-9]+)?)|[-+*/()^]");
        Matcher matcher = pattern.matcher(input);

        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        // Process tokens into lexemes
        List<Lexeme> lexemes = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            String curr = tokens.get(i);

            for (Type t : tokenDefinitions.keySet()) {
                String regex = tokenDefinitions.get(t);
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(curr);

                if (matcher.find()) {
                    if (t == Type.NUMBER) {
                        lexemes.add(new Lexeme(t, Double.parseDouble(curr)));
                    } else {
                        lexemes.add(new Lexeme(t));
                    }
                    break;
                }
            }
        }

        // Mark unary operators
        // If first lexeme is MIN
        if (lexemes.get(0).type == Type.MIN) {
            // Unary
            lexemes.get(0).type = Type.UMIN;
        }

        for (int i = 1; i < lexemes.size(); i++) {
            // If previous lexeme is an operator and current lexeme is a MIN operator
            if (isOperator(lexemes.get(i-1).type) && lexemes.get(i).type == Type.MIN) {
                // If operator is right bracket then operator is binary
                if (lexemes.get(i-1).type != Type.RBRAC) {
                    // Unary
                    lexemes.get(i).type = Type.UMIN;
                }
            }
        }

        return lexemes;
    }

    /**
     * Returns true if type is element of operators
     * @param type Type to be checked
     * @return True if operator
     */
    public boolean isOperator(Type type) {
        return type != Type.NUMBER;
    }

    /**
     * Define token definitions.
     * Lexer calls this method once if no token definitions are defined.
     */
    private void initLexer() {
        this.addTokenDefinition("([0-9]+(\\.[0-9]+)?)", Type.NUMBER);
        this.addTokenDefinition("\\+", Type.PLUS);
        this.addTokenDefinition("\\-", Type.MIN);
        this.addTokenDefinition("\\*", Type.MUL);
        this.addTokenDefinition("\\/", Type.DIV);
        this.addTokenDefinition("\\^", Type.POW);
        this.addTokenDefinition("\\(", Type.LBRAC);
        this.addTokenDefinition("\\)", Type.RBRAC);

    }

    /**
     * Adds a definition for the lexer.
     * @param regex Regex string to be added
     * @param type Type enum
     */
    private void addTokenDefinition(String regex, Type type) {
        tokenDefinitions.put(type, regex);
    }

    /**
     * Converts suffix to postfix using the shunting yard algorithm.
     * Takes in and outputs lexemes.
     * @param infixLexemes List of infix lexemes
     * @return List of postfix lexemes
     */
    public List<Lexeme> shuntingYard(List<Lexeme> infixLexemes) {
        List<Lexeme> output = new ArrayList<>();
        Stack<Lexeme> opStack = new Stack<>();

        for (Lexeme curr : infixLexemes) {
            // Push numbers to output
            if (curr.type == Type.NUMBER) {
                output.add(curr);
                continue;
            }
            // From here all lexemes must be operators or dividers

            // Push left brackets to opStack
            if (curr.type == Type.LBRAC) {
                opStack.add(curr);
                continue;
            }

            // Push contents op opStack to output until right bracket then remove right bracket
            if (curr.type == Type.RBRAC) {
                while (!opStack.empty() && opStack.peek().type != Type.LBRAC) {
                    output.add(opStack.pop());
                }
                opStack.pop();
                continue;
            }
            // From here all lexemes must be operators


            // If opStack is empty, push current operator to opStack
            if (opStack.empty()) {
                opStack.push(curr);
                continue;
            }

            // While (there are operators in the opStack with higher or equal precedence to curr)
            // or (equal precedence and curr is left associative)
            while (!opStack.empty() && ((getPrecedence(opStack.peek().type) > getPrecedence(curr.type)) ||
                    ((getPrecedence(opStack.peek().type) == getPrecedence(curr.type)) && isLeftAssociative(curr.type)))) {
                output.add(opStack.pop());
            }
            opStack.add(curr);
        }

        // Cleanup
        while (!opStack.empty()) {
            output.add(opStack.pop());
        }


        return output;
    }


    /**
     * Is operator left associative
     * E.g. a - b - c = (a - b) - c. Left to right.
     * @param operator Operator to be checked.
     * @return boolean
     */
    private boolean isLeftAssociative(Type operator) {
        return switch (operator) {
            case PLUS, MIN, MUL, DIV -> true;
            default -> false;
        };
    }



    /**
     * What is the precedence for the current operator.
     * 0 <= precedence <= 3
     * @param operator Operator
     * @return int precedence.
     */
    int getPrecedence(Type operator) {
        return switch (operator) {
            case PLUS, MIN -> 1;
            case MUL, DIV -> 2;
            case POW, UMIN -> 3;
            default -> 0;
        };
    }

    /**
     * Evaluates an expression formatted as postfix lexemes.
     * @param postfixLexemes List of postfix lexemes as input
     * @return Result as double.
     */
    double postfixEvaluator(List<Lexeme> postfixLexemes) {
        Stack<Lexeme> evalStack = new Stack<>();

        // For all lexemes, left to right
        for (int i = 0; i < postfixLexemes.size(); i++) {
            Lexeme curr = postfixLexemes.get(i);

            // If current lexeme is a number
            if (curr.type == Type.NUMBER) {
                // Push to stack
                evalStack.push(curr);
                continue;
            }

            // If current lexeme is an operator
            if (isOperator(curr.type)) {
                // And the operator is a unary minus
                if (curr.type == Type.UMIN) {
                    // Switch signum for number at top of stack
                    evalStack.push(new Lexeme(Type.NUMBER, -evalStack.pop().value));
                    continue;
                }

                // Else pop a and b from stack. Minding the order of operations and naming.
                double b = evalStack.pop().value;
                double a = evalStack.pop().value;
                // Push result of operation to stack
                evalStack.push(operate(curr.type, a, b));
            }
        }
        // Return final element on stack as result.
        return evalStack.pop().value;
    }

    /**
     * Applies correct operation by enum type.
     * @param operator Type of operation. Enum.
     * @param operandA Operand a, double.
     * @param operandB Operand b, double.
     * @return Result as Lexeme.
     */
    Lexeme operate(Type operator, double operandA, double operandB) {

        switch (operator) {
            case PLUS -> {
                return new Lexeme(Type.NUMBER, operandA + operandB);
            }
            case MIN -> {
                return new Lexeme(Type.NUMBER, operandA - operandB);

            }
            case MUL -> {
                return new Lexeme(Type.NUMBER, operandA * operandB);

            }
            case DIV -> {
                return new Lexeme(Type.NUMBER, operandA / operandB);

            }
            case POW -> {
                return new Lexeme(Type.NUMBER, Math.pow(operandA, operandB));

            }
            default -> {
                return null;
            }
        }
    }
}

/**
 * Lexeme object.
 */
class Lexeme {
    ProblemSeven.Type type;
    double value = -1;

    public Lexeme(ProblemSeven.Type type, double value) {
        this.type = type;
        this.value = value;
    }
    public Lexeme(ProblemSeven.Type type) {
        this.type = type;
    }
}

