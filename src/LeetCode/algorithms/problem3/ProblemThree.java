package LeetCode.algorithms.problem3;

import java.util.Arrays;

public class ProblemThree {
    public static void main(String[] args) {
        ProblemThree pt = new ProblemThree();
        System.out.println(Arrays.toString(pt.twoSum(new int[]{1, 3, 5, 8}, 9)));
    }
    public int[] twoSum(int[] nums, int target) {
        // For all numbers
        for (int i = 0; i < nums.length; i++) {
            // Try all other numbers
            for (int j = 0; j < nums.length; j++) {
                // Ignore self
                if (j == i) {
                    continue;
                }
                if (nums[i] + nums[j] == target) {
                    // Found
                    return new int[]{i, j};
                }
            }
        }
        // Error
        return null;
    }
}
