package CodeWars.problem5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemFive {
    public static void main(String[] args) {
        ProblemFive pf = new ProblemFive();

        System.out.println(pf.calculateCombinations('E', 3));
        System.out.println("Wooo");
    }

    public char[] allChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

    public int calculateCombinations(char startPosition, int patternLength) {
        if (patternLength == 0) {
            return 0;
        } else if (patternLength == 1) {
            return 1;
        } else if (patternLength > 8) {
            return 0;
        }

        calculateCombinationsRecursive(startPosition, 0, patternLength-1, null);
        return totalPaths;
    }

    public int totalPaths = 0;

    public void calculateCombinationsRecursive(char startPos, int currentDepth, int max, List<Character> charsInUse) {
        if (charsInUse == null) {
            charsInUse = new ArrayList<>();
        }
        List<Character> possible = possibleMoves(startPos, charsInUse);
        for (int i = 0; i < possible.size(); i++) {
            List<Character> newCharsInUse = new ArrayList<>(charsInUse);
            newCharsInUse.add(possible.get(i));
            if (currentDepth <= max) {
                calculateCombinationsRecursive(possible.get(i), currentDepth+1, max, newCharsInUse);

            }
        }

        if (currentDepth == max) {
            totalPaths++;
        }
    }

    public List<Character> possibleMoves(char c, List<Character> charsInUse) {
        switch (c) {
            case 'A': {
                return possibleMovesHelper('A',
                        new ArrayList<>(Arrays.asList('B', 'D', 'H', 'F', 'E')), // Static possible moves
                        new char[]{'B', 'E', 'D'}, // Diagonal requirements
                        new char[]{'C', 'I', 'G'},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'B': {
                return possibleMovesHelper('B',
                        new ArrayList<>(Arrays.asList('A', 'D', 'G', 'C', 'F', 'I', 'E')), // Static possible moves
                        new char[]{'E'}, // Diagonal requirements
                        new char[]{'H'},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'C': {
                return possibleMovesHelper('C',
                        new ArrayList<>(Arrays.asList('B', 'D', 'E', 'H', 'F')), // Static possible moves
                        new char[]{'B', 'F', 'E'}, // Diagonal requirements
                        new char[]{'A', 'I', 'G'},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'D': {
                return possibleMovesHelper('D',
                        new ArrayList<>(Arrays.asList('A', 'B', 'C', 'G', 'H', 'I', 'E')), // Static possible moves
                        new char[]{'E'}, // Diagonal requirements
                        new char[]{'F'},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'E': {
                return possibleMovesHelper('E',
                        new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'F', 'G', 'H', 'I')), // Static possible moves
                        new char[]{}, // Diagonal requirements
                        new char[]{},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'F': {
                return possibleMovesHelper('F',
                        new ArrayList<>(Arrays.asList('A', 'B', 'C', 'E', 'G', 'H', 'I')), // Static possible moves
                        new char[]{'E'}, // Diagonal requirements
                        new char[]{'D'},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'G': {
                return possibleMovesHelper('G',
                        new ArrayList<>(Arrays.asList('B', 'D', 'E', 'F', 'H')), // Static possible moves
                        new char[]{'D', 'H', 'E'}, // Diagonal requirements
                        new char[]{'A', 'I', 'C'},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'H': {
                return possibleMovesHelper('H',
                        new ArrayList<>(Arrays.asList('G', 'D', 'A', 'I', 'F', 'C', 'E')), // Static possible moves
                        new char[]{'E'}, // Diagonal requirements
                        new char[]{'B'},
                        charsInUse); // Corresponding possible diagonal moves.
            }
            case 'I': {
                return possibleMovesHelper('I',
                        new ArrayList<>(Arrays.asList('D', 'H', 'B', 'F', 'E')), // Static possible moves
                        new char[]{'H', 'F', 'E'}, // Diagonal requirements
                        new char[]{'G', 'C', 'A'},
                        charsInUse); // Corresponding possible diagonal moves.
            }


        }
        return null;
    }

    public List<Character> possibleMovesHelper(char c, List<Character> staticPossible, char[] diagReqs, char[] diagPotents, List<Character> charsInUse) {
        this.removeCharsInUse(staticPossible, charsInUse);
        if (diagReqs.length > 0) {
            this.addDiagonals(staticPossible, diagReqs, diagPotents, charsInUse);
        }
        return staticPossible;
    }

    public void removeCharsInUse(List<Character> possible, List<Character> charsInUse) {
        List<Character> toRemove = new ArrayList<>();
        for (Character value : possible) {
            for (Character character : charsInUse) {
                if (value.equals(character)) {
                    toRemove.add(value);
                    break;
                }
            }

        }
        // Remove all items
        possible.removeAll(toRemove);
    }

    public void addDiagonals(List<Character> possible, char[] reqs, char[] moves, List<Character> charsInUse) {
        for (int i = 0; i < reqs.length; i++) {
            for (Character character : charsInUse) {
                if (!charsInUse.contains(moves[i]) && reqs[i] == character) {
                    possible.add(moves[i]);
                    break;
                }
            }
        }
    }

}
