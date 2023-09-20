package CodeWars.problem5;
public class Test {

    // Define the 3x3 board as a 2D array
    private static final char[][] board = {
            {'A', 'B', 'C'},
            {'D', 'E', 'F'},
            {'G', 'H', 'I'}
    };

    // Helper function to check if a position is valid on the board
    private static boolean isValid(int row, int col, boolean[][] visited) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && !visited[row][col];
    }

    // Recursive function to count the number of patterns
    private static int countPatterns(char[][] board, int row, int col, int length, boolean[][] visited) {
        if (length == 0) {
            return 1; // We have reached the desired pattern length
        }

        visited[row][col] = true;
        int count = 0;

        // Define possible moves (adjacent, diagonal, and knight)
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            // Check if the new position is valid and not visited
            if (isValid(newRow, newCol, visited)) {
                int newRowPos = newRow - 'A';
                int newColPos = newCol - 'A';
                count += countPatterns(board, newRow, newCol, length - 1, visited);
            }
        }

        visited[row][col] = false; // Backtrack
        return count;
    }

    // Public function to calculate combinations
    public static int calculateCombinations(char startPosition, int patternLength) {
        boolean[][] visited = new boolean[3][3]; // Initialize visited array

        // Find the starting position on the board
        int startRow = -1;
        int startCol = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == startPosition) {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }

        if (startRow == -1 || startCol == -1) {
            // Starting position not found on the board
            return 0;
        }

        // Calculate the number of patterns
        return countPatterns(board, startRow, startCol, patternLength, visited);
    }

    public static void main(String[] args) {
        char startPosition = 'A'; // Desired starting position
        int patternLength = 2; // Desired pattern length

        // Calculate the number of combinations
        int numCombinations = calculateCombinations(startPosition, patternLength);
        System.out.println("Number of possible combinations: " + numCombinations);
    }
}


