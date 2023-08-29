package LeetCode.algorithms.problem1;

import java.util.ArrayList;
import java.util.List;

public class ProblemOne {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
    static public int lengthOfLongestSubstring(String s) {
        int currentlongest = 0;

        // Try every char as begin
        for (int i = 0; i < s.length(); i++) {
            // The longest string found starting from this char
            int tempLongest = 0;
            List<Character> found = new ArrayList<>();

            // Starting from current char, find longest unique string
            for (int j = i; j < s.length(); j++) {
                // If duplicate found, stop
                if (found.contains(s.charAt(j))) {break;}
                // Add new unique character to list and increment longest.
                found.add(s.charAt(j));
                tempLongest++;
            }
            // If new longest has been found, change master record
            if (tempLongest > currentlongest) { currentlongest = tempLongest;}
        }
        return currentlongest;
    }

}
