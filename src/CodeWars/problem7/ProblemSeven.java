package CodeWars.problem7;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemSeven {
    public static void main(String[] args) {
        ProblemSeven p = new ProblemSeven();
        System.out.println(Arrays.toString(p.shuntingYard(p.tokenize("6 + -(- 4)"))));
    }


    /**
     * Tokenizes mathematical expression.
     *
     * @param expr Expression to be tokenized.
     * @return Array of tokens, datatype String.
     */
    public String[] tokenize(String expr) {
        // Unlimited numbers or any of these characters: -+*/()^
        String pattern = "([0-9]+(\\.[0-9]+)?|[-+*/()^])";

        // Declare and init regex matcher
        Matcher matcher = Pattern.compile(pattern).matcher(expr);
        // Token list
        List<String> tokens = new ArrayList<>();

        // Find and add the captured components to the list
        while (matcher.find()) {
            tokens.add(matcher.group(1));
        }
        // Return result as array of strings
        return tokens.toArray(new String[0]);
    }

    public String[] removeDoubleNegationTokens(String[] infixTokens) {

        List<String> tokenList = new ArrayList<>(Arrays.stream(infixTokens).toList());
        List<Integer> toRemove = new ArrayList<>();

        for (int i = 0; i < infixTokens.length - 1; i++) {
            if (tokenList.get(i).equals("-") && tokenList.get(i + 1).equals("-")) {
                tokenList.set(i, "+");
                toRemove.add(i + 1);
                i++;
            }
        }

        for (int i = toRemove.size() - 1; i > -1; i--) {
            int toRemoveIndex = toRemove.get(i);
            tokenList.remove(toRemoveIndex);
        }
        return tokenList.toArray(new String[0]);
    }

    /**
     * Converts suffix tokens to postfix tokens using the shunting yard algorithm.
     *
     * @param infixTokens Infix tokens as input
     * @return Array of postfix tokens, datatype String
     */


    public String[] shuntingYard(String[] infixTokens) {
        List<String> postfix = new ArrayList<>();
        Stack<String> opStack = new Stack<>();

        for (int i = 0; i < infixTokens.length; i++) {
            String curr = infixTokens[i];

            if (curr.equals("(")) {
                // If open brackets, push to result queue
                opStack.push("(");

            } else if (curr.equals(")")) {
                // If close brackets, pop operators and if not ( push to result queue.
                // Repeat until ( found
                boolean found = false;
                while (!found) {
                    String currOp = opStack.pop();
                    if (currOp.equals("(")) {
                        found = true;
                    } else {
                        postfix.add(currOp);
                    }
                }

            } else if (isOperator(curr)) {
                while (!opStack.isEmpty()) {
                    String topOperator = opStack.peek();


                    // Determine if we should pop 'topOperator' based on associativity and precedence.
                    if ((isLeftAssociative(curr) && getPrecedence(curr) <= getPrecedence(topOperator))
                            || (isRightAssociative(curr) && getPrecedence(curr) < getPrecedence(topOperator))) {
                        // Pop 'topOperator' from the operator stack and add it to the output queue.
                        postfix.add(opStack.pop());
                    } else {
                        // Break the loop if the conditions are not met.
                        break;
                    }
                }

                // Push 'curr' onto the operator stack.
                opStack.push(curr);
            } else {
                // Add number to postfix
                postfix.add(curr);
            }
        }

        while (!opStack.isEmpty()) {
            postfix.add(opStack.pop());
        }
        return postfix.toArray(new String[0]);
    }

    private boolean isOperator(String token) {
        return ("+-*/^".contains(token));
    }

    /**
     * Is operator left associative
     * E.g. a - b - c = (a - b) - c. Left to right.
     *
     * @param operator Operator to be checked.
     * @return boolean
     */
    private boolean isLeftAssociative(String operator) {
        return "+-*/".contains(operator);
    }

    /**
     * Is operator right associative
     * E.g. a ^ b ^ c = a ^ (b ^ c). Right to left.
     *
     * @param operator Operator to be checked.
     * @return boolean
     */
    private boolean isRightAssociative(String operator) {
        return "^".contains(operator);
    }

    /**
     * What is the precedence for the current operator.
     * 0 <= precedence <= 3
     *
     * @param operator Operator
     * @return int precedence.
     */
    int getPrecedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }

    double postfixEvaluator(String[] postfixTokens) {

        return 1;
    }
}

