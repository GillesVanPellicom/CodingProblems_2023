package CodeWars.problem8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProblemEight_v2 {
    public static void main(String[] args) {
        String t1 = "RBRGBRBGGRRRBGBBBGG";

//        System.out.println("Expected:\nRBRGBRB\nGot:\n" + calculateRow(t1));

        System.out.println(triangle(t1));
    }

    /**
     * Triangle game solver.
     * Input the top layer of a triangle as a string s with alphabet Σ = {R, G, B}
     * Placement rules: RG = B, GB = R, BR = G, RR = R, GG = G, BB = B
     * So e.g.: RGGB
     *          BR
     *          G
     * Answer: G
     * @param inputRow
     * @return
     */
    public static char triangle(final String inputRow) {
        // Convert the input string to a character array
        char[] row = inputRow.toCharArray();

        // Generate a list of powers of 3 (3^i + 1) less than or equal to 100,000 in reverse order
        List<Integer> reduce = new ArrayList<>();
        for (int i = 0; ; i++) {
            int powerOf3 = (int) Math.pow(3, i);

            // Check if the power of 3 plus 1 exceeds 100,000
            if (powerOf3 + 1 <= 100000) {
                reduce.add(powerOf3 + 1);
            } else {
                // If we exceed the limit, exit the loop
                break;
            }
        }

        // Iterate through the powers of 3 in reverse order
        for (int i = reduce.size() - 1; i >= 0; i--) {
            int length = reduce.get(i);

            // Process the input string in segments of the current length
            while (row.length >= length) {
                char[] newRow = new char[row.length - length + 1];
                for (int j = 0; j < newRow.length; j++) {
                    // Compare characters at positions j and j + length - 1 in the original 'row'
                    if (row[j] == row[j + length - 1]) {
                        // If characters are the same, keep the character
                        newRow[j] = row[j];
                    } else {
                        // If characters are different, determine the replacement character
                        Set<Character> colors = new HashSet<>(Arrays.asList('R', 'G', 'B'));
                        colors.remove(row[j]);
                        colors.remove(row[j + length - 1]);
                        newRow[j] = colors.iterator().next();
                    }
                }
                // Update the 'row' with the processed 'newRow'
                row = newRow;
            }
        }

        // Return the final character remaining in the 'row' array
        return row[0];
    }
}


