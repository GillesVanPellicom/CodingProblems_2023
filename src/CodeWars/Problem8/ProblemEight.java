package CodeWars.Problem8;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProblemEight {
    public static void main(String[] args) {
        String t1 = "RBRGBRBGGRRRBGBBBGG";

//        System.out.println("Expected:\nRBRGBRB\nGot:\n" + calculateRow(t1));

        System.out.println(triangle(t1));
    }
    public static char triangle(final String inputRow) {

        char[] row = inputRow.toCharArray();

        // Generate a list of powers of 3 (3^i + 1) less than or equal to 100,000 in reverse order
        List<Integer> reduce = new ArrayList<>();
        for (int i = 0; ; i++) {
            int powerOf3 = (int) Math.pow(3, i);
            if (powerOf3 + 1 <= 100000) {
                reduce.add(powerOf3 + 1);
            } else {
                break;
            }
        }
        for (int i = reduce.size() - 1; i >= 0; i--) {
            int length = reduce.get(i);
            while (row.length >= length) {
                char[] newRow = new char[row.length - length + 1];
                for (int j = 0; j < newRow.length; j++) {
                    if (row[j] == row[j + length - 1]) {
                        newRow[j] = row[j];
                    } else {
                        Set<Character> colors = new HashSet<>(Arrays.asList('R', 'G', 'B'));
                        colors.remove(row[j]);
                        colors.remove(row[j + length - 1]);
                        newRow[j] = colors.iterator().next();
                    }
                }
                row = newRow;
            }
        }
        return row[0];
    }
}


