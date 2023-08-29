package LeetCode.algorithms.problem5;

import java.util.ArrayList;
import java.util.List;

class ProblemFive {
    public static void main(String[] args) {
        ProblemFive pf = new ProblemFive();
        ListNode[] lists = {
                new ListNode(5, new ListNode(7, new ListNode(18, new ListNode(30, null)))),
                new ListNode(6, new ListNode(9, new ListNode(12, new ListNode(17, null)))),
                new ListNode(3, new ListNode(4, new ListNode(11, new ListNode(20, null)))),
                new ListNode(1, new ListNode(2, new ListNode(90, new ListNode(100, null))))
        };
//        ListNode[] lists = {new ListNode(null, null)};
        ListNode ln = pf.mergeKLists(lists);
        System.out.println("woo");
    }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null) {
            return null;
        } else if (lists.length == 0) {
            return null;
        }

        List<Integer>[] arr = new ArrayList[lists.length];
        List<Integer> resultArr = new ArrayList<>();

        for (int i = 0; i < lists.length; i++) {
            arr[i] = this.listNodeToList(lists[i]);
        }


        for (int i = 0; i < arr.length; i++) {
            resultArr = this.combineSortedArrays(arr[i], resultArr);
        }
        return this.listToListNode(resultArr);
    }

    public ListNode listToListNode(List<Integer> arr) {
        if (arr == null) {
            return null;
        }
        // Create empty list for result
        ListNode result = null;


        for (int i = arr.size(); i > 0; i--) {
            // Place result in list backwards
            result = new ListNode(arr.get(i - 1), result);
        }
        return result;
    }


    /***
     * Appends the tail of appender starting from n to appendee on index.
     * Code taken from problemFour, modified to work with List.
     * @param appendee List to be appended to. Will be mutated.
     * @param appender List from where the appended data is taken.
     * @param index Where to start appending
     * @param n Where does the tail of the appender start?
     * @return Appended array
     */
    public List<Integer> subAppendArray(List<Integer> appendee, List<Integer> appender, int index, int n) {

        for (int i = n; i < appender.size(); i++) {

            appendee.add(appender.get(i));

            index++;

        }
        return appendee;

    }

    /***
     * Combines two sorted arrays into one array.
     * Code taken from ProblemFour, modified to work with List.
     * @param arr1 First array to be combined.
     * @param arr2 Second array to be combined
     * @return Combined array
     */
    public List<Integer> combineSortedArrays(List<Integer> arr1, List<Integer> arr2) {
        if (arr2 == null) {
            return arr1;
        } else if (arr1 == null) {
            return null;
        }
        // Create new array with the combined size
        List<Integer> solution = new ArrayList<>();
        // Indices to keep track of what numbers we are comparing in both arrays
        int nIndex = 0;
        int mIndex = 0;

        // For the the length of both arrays combined
        for (int i = 0; i < arr1.size() + arr2.size(); i++) {
            if (nIndex == arr1.size()) {
                // If the nIndex is the same as the length of arr1, use subAppendArray() to finish.
                return this.subAppendArray(solution, arr2, i, mIndex);

            } else if (mIndex == arr2.size()) {
                // If the mIndex is the same as the length of arr2, use subAppendArray() to finish.
                return this.subAppendArray(solution, arr1, i, nIndex);

            } else if (arr1.get(nIndex) >= arr2.get(mIndex)) {
                // If the current number in arr1 is GTE than current arr2 number
                // Append the arr2 number to the array and increment it's index
                solution.add(arr2.get(mIndex));
                mIndex++;

            } else {
                // If the current number in arr1 is LT than current arr2 number
                // Append the arr1 number to the array and increment it's index
                solution.add(arr1.get(nIndex));

                nIndex++;

            }

        }
        // Error
        return null;

    }

    /***
     * Parses a linked list from the ListNode class to java.util.ArrayList
     * Code taken from ProblemTwo
     * @param l Head node from the linked list
     * @return ArrayList result
     */
    public List<Integer> listNodeToList(ListNode l) {

        List<Integer> result = new ArrayList<>();
        if (l == null) {
            return result;
        }
        while (true) {
            result.add(l.val);
            if (l.next == null) {
                // End of list
                break;
            }
            // Go to next node
            l = l.next;
        }
        return result;
    }
}
