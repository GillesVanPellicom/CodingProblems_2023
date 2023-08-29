package LeetCode.algorithms.problem2;

import java.util.ArrayList;
import java.util.List;

public class ProblemTwo {
    /***
     * Adds two numbers stored backwards in a linked list datatype.
     * @param l1 list one head
     * @param l2 list two head
     * @return two numbers added and outputted backwards as a linked list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Since the list is not doubly-linked, take out all values and place in java native list datatype.
        List<Integer> operandA = new ArrayList<>();
        List<Integer> operandB = new ArrayList<>();

        // As long as no nullptr is hit, keep reading out values from l1 and place them in operandA
        while (true) {
            operandA.add(l1.val);
            if (l1.next == null) {
                // End of list
                break;
            }
            // Go to next node
            l1 = l1.next;
        }
        // As long as no nullptr is hit, keep reading out values from l2 and place them in operandB
        while (true) {
            operandB.add(l2.val);
            if (l2.next == null) {
                // End of list
                break;
            }
            // Go to next node

            l2 = l2.next;
        }
        // List used to store computed result
        List<Integer> resultArr = new ArrayList<>();
        // Adding algorithm used is basic ripple-carry adder.
        boolean carry = false;

        // For the largest operand
        for (int i = 0; i < Math.max(operandA.size(), operandB.size()); i++) {
            // Execute ripple carry starting from least significant digit.
            int temp;
            if (i > operandA.size() - 1) {
                temp = operandB.get(i);
            } else if (i > operandB.size() - 1) {
                temp = operandA.get(i);
            } else {
                temp = operandA.get(i) + operandB.get(i);

            }
            if (carry) {
                temp++;
                carry = false;
            }
            if (temp >= 10) {
                temp -= 10;

                carry = true;
            }
            resultArr.add(temp);
        }

        if (carry) {
            resultArr.add(1);
        }

        // Create empty list for result
        ListNode result = null;

        for (int i = resultArr.size(); i > 0; i--) {
            // Place result in list backwards
            result = new ListNode(resultArr.get(i-1), result);
        }

        return result;
    }
}
