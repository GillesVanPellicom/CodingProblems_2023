package CodeWars.problem3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemThree {
    public static void main(String[] args) {
        //              2
        //          8       9
        //        1   3   4   5
        // Expected: [2,8,9,1,3,4,5]
//        Node n = new Node(new Node(new Node(null, null, 1), new Node(null, null, 3), 8),
//                new Node(new Node(null, null, 4), new Node(null, null, 5), 9), 2);
//        Node n = new Node(new Node(null, new Node(null, null, 4), 2),
//                new Node(new Node(null, null, 5), new Node(null, null, 6), 3), 1);
        Node n = new Node(null, new Node(null, new Node(null, new Node(null, null, 1), 2), 3), 4);
        List<Integer> r = treeByLevels(n);
        System.out.println("Woo");
    }

    public static HashMap<Integer, List<Integer>> levels = new HashMap<>(); // values by level. Let set A_n ordered by n.
    public static int maxLevel = 0;

    public static List<Integer> treeByLevels(Node n) {
        List<Integer> result = new ArrayList<>();

        if (n == null) {
            return result;
        }

        postOrderTraversal(n, 0);

        for (Map.Entry<Integer, List<Integer>> entry : levels.entrySet()) {
            System.out.println(entry.getKey()+" -> "+entry.getValue());
        }

        for (int i = 0; i < maxLevel + 1; i++) {
            result.addAll(levels.get(i));
        }
        return result;
    }

    public static void postOrderTraversal(Node n, int currentLevel) {
        if (maxLevel < currentLevel) {
            maxLevel++;
        }
        if (!levels.containsKey(currentLevel)) {
            levels.put(currentLevel, new ArrayList<>());
        }

        levels.get(currentLevel).add(n.value);

        if (n.left != null) {
            postOrderTraversal(n.left, currentLevel + 1);
        }
        if (n.right != null) {
            postOrderTraversal(n.right, currentLevel + 1);
        }
    }

}
