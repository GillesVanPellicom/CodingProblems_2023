package CodeWars.problem2;

public class ProblemTwo {
    public static void main(String[] args) {
        System.out.println(fromRoman("MCMXCIX"));
        System.out.println(toRoman(1999));
    }

    /***
     * Turns an arabic numeral (E.g 1999) into it's roman counterpart (E.g MCMXCIX).
     * Constraints: 1 <= n < 4000 (not enforced)
     * @param n arabic numeral.
     * @return roman numeral as String.
     */
    public static String toRoman(int n) {
        StringBuilder result = new StringBuilder();

        // As long as the number is > 0:
        while (true) {
            // See if this letter can be subtracted
            if (n - 1000 >= 0) {
                // If so, subtract letter's value from the int
                n -= 1000;
                // And append the letter to the solution
                result.append("M");
                // Subtractive notation is treated in the same way
                // Repeat until 0
            } else if (n - 900 >= 0) {
                n -= 900;
                result.append("CM");
            } else if (n - 500 >= 0) {
                n -= 500;
                result.append("D");
            } else if (n - 400 >= 0) {
                n -= 400;
                result.append("CD");
            } else if (n - 100 >= 0) {
                n -= 100;
                result.append("C");
            } else if (n - 90 >= 0) {
                n -= 90;
                result.append("XC");
            } else if (n - 50 >= 0) {
                n -= 50;
                result.append("L");
            } else if (n - 40 >= 0) {
                n -= 40;
                result.append("XL");
            } else if (n - 10 >= 0) {
                n -= 10;
                result.append("X");
            } else if (n - 9 >= 0) {
                n -= 9;
                result.append("IX");
            } else if (n - 5 >= 0) {
                n -= 5;
                result.append("V");
            } else if (n - 4 >= 0) {
                n -= 4;
                result.append("IV");
            } else if (n - 1 >= 0) {
                n -= 1;
                result.append("I");
            } else {
                // Int == 0, result has been fully constructed.
                break;
            }
        }
        return result.toString();
    }

    /***
     * Turns a roman numeral (E.g MCMXCIX) into it's arabic counterpart (1999)
     * No syntax protection. E.g XXXXX will be seen as 50 though 50 is written as L.
     * @param s roman numeral string
     * @return int arabic
     */
    public static int fromRoman(String s) {
        // int to store result
        int result = 0;
        // For the length of the input
        for (int i = 0; i < s.length(); i++) {
            // If the remaining string is at least 2 characters long:
            if (i + 1 < s.length()) {
                // and if the first numeral is smaller compared to the second one:
                if (romanToArabic(s.charAt(i)) < romanToArabic(s.charAt(i + 1))) {
                    // Minus operation. E.g IX = 9 because 10-1.
                    result += romanToArabic(s.charAt(i + 1)) - romanToArabic(s.charAt(i));
                    // Increment by one since 2 chars have been processed. The for loop also increments so that makes 2.
                    i++;
                    continue;
                }
            }
            // If there is no subtractive notation, simply translate and add to the result.
            result += romanToArabic(s.charAt(i));

        }
        return result;
    }

    /***
     * Takes a single roman numeral and turns it into it's arabic equivalent (E.g V -> 5)
     * @param c char to be turned into arabic numeral.
     * @return arabic numeral.
     * @throws IllegalArgumentException If a letter not used in roman numerals is inputted.
     */
    public static int romanToArabic(char c) {
        String temp = String.valueOf(c);
        c = temp.toUpperCase().charAt(0);
        switch (c) {
            case 'I' -> {
                return 1;
            }
            case 'V' -> {
                return 5;
            }
            case 'X' -> {
                return 10;
            }
            case 'L' -> {
                return 50;
            }
            case 'C' -> {
                return 100;
            }
            case 'D' -> {
                return 500;
            }
            case 'M' -> {
                return 1000;
            }
            default ->
                    throw new IllegalArgumentException("\"" + c + "\" is not a valid character for a roman numeral.");
        }
    }
}
