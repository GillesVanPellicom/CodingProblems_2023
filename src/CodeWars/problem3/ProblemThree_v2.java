package CodeWars.problem3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ProblemThree_v2 {

    public static void main(String[] args) {
        Node n = new Node(null, new Node(null, new Node(null, new Node(null, null, 1), 2), 3), 4);
        List<Integer> res = treeByLevels(n);
        System.out.println("Woo");

    }

    /***
     * Binary tree sort by level in preorder traversal.
     * @param n root node
     * @return List<Integer> node values sorted by level (Preorder)
     */
    public static List<Integer> treeByLevels(Node n) {

        // Create result list
        List<Integer> result = new ArrayList<>();
        // If there is no root, return empty list
        if (n == null) {
            return result;
        }

        // Create empty double-ended queue
        Queue<Node> queue = new ArrayDeque<>();
        // Enqueue root
        queue.add(n);


        Node current;
        // For the entire queue
        while (!queue.isEmpty()) {
            // Get current node from the queue
            current = queue.poll();
            // Add value of current node to result list
            result.add(current.value);

            // If there is a left child
            if (current.left != null) {
                // Enqueue left child
                queue.add(current.left);
            }

            // If there is a right child
            if (current.right != null) {
                // Enqueue right child
                queue.add(current.right);
            }
        }
        return result;
    }



}
