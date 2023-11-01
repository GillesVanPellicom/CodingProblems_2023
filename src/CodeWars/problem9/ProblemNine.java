package CodeWars.problem9;

import CodeWars.problem7.ProblemSeven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemNine {

    public static void main(String[] args) {

        ProblemNine p = new ProblemNine();

//        System.out.println(p.tokenize("(2x+4y)^1"));
//        p.expandBinomial(p.tokenize("(2x+4y)^1"));
    }

    public List<String> tokenize(String s) {
        Pattern pattern = Pattern.compile("([0-9]+(\\.[0-9]+)?)|[-+*/()^]|[a-zA-Z]+");
        Matcher matcher = pattern.matcher(s);

        List<String> tokens = new ArrayList<>();

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    private String expandBinomial(List<String> tokens) {
        int n = Integer.parseInt(tokens.get(tokens.size() - 1));
        StringBuilder res = new StringBuilder();

        // Base cases:
        // (a + b)^0 = 1
        if (n == 0) {
            return "1";
        }
        // (a + b)^1 = a + b
        if (n == 1) {
            for (int i = 1; i < tokens.size() - 3; i++) {
                res.append(tokens.get(i));
            }
            return res.toString();
        }
        // Find split index for operands A and B
        for (int i = tokens.size()-3; i >= 1 ; i--) {

        }

        // Binomial
        for (int k = 0; k < n; k++) {

        }

        return "";
    }

    private String binomialCoefficient(int n, int k) {
        return Long.toString(calcBinomialCoefficient(n, k));
    }

    private static final Map<String, Long> binomialMemoization = new HashMap<>();

    private long calcBinomialCoefficient(int n, int k) {
        // Base cases
        if (k < 0 || k > n) {
            return 0;
        }

        if (k == 0 || k == n) {
            return 1;
        }

        String key = n + "," + k;

        if (binomialMemoization.containsKey(key)) {
            return binomialMemoization.get(key);
        }

        long result = calcBinomialCoefficient(n - 1, k - 1) + calcBinomialCoefficient(n - 1, k);
        binomialMemoization.put(key, result);

        return result;
    }
}
