package CodeWars.problem8;

public class ProblemEight {
    public static char triangle(final String row) {
        calculateRow(row.toCharArray());
        return output;
    }

    public static char getColor(char lColor, char rColor) {
        if (lColor == rColor) {
            return lColor;
        }
        boolean hasR = lColor == 'R' || rColor == 'R';
        boolean hasG = lColor == 'G' || rColor == 'G';

        if (!hasR) {
            return 'R';
        } else if (!hasG) {
            return 'G';
        } else {
            return 'B';
        }
    }

    static char output = '/';

    public static void calculateRow(char[] row) {
        if (row.length == 1) {
            output = row[0];
            return;
        }
        char[] nextRow = new char[row.length - 1];
        for (int i = 0, n = nextRow.length; i < n; ++i) {
            nextRow[i] = getColor(row[i], row[i + 1]);
        }
        calculateRow(nextRow);
    }
}
