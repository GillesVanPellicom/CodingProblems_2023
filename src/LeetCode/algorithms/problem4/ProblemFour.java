package LeetCode.problem4;

public class ProblemFour {
    public static void main(String[] args) {
        int[] a1 = {1,4,8};
        int[] a2 = {2,3,7};
        ProblemFour pf = new ProblemFour();
        System.out.println(pf.findMedianSortedArrays(a1, a2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return findMedianSortedArray(combineSortedArrays(nums1, nums2));

    }

    /***
     * Finds median in given array
     * @param arr input array
     * @return median as double
     */
    public double findMedianSortedArray(int[] arr) {

        int length = arr.length;

        if (length % 2 == 0) {

            int middle1 = arr[length / 2 - 1];

            int middle2 = arr[length / 2];

            return (double) (middle1 + middle2) / 2;

        } else {

            return arr[length / 2];

        }

    }

    /***
     * Combines two sorted arrays into one array.
     * @param arr1 First array to be combined.
     * @param arr2 Second array to be combined
     * @return Combined array
     */
    public int[] combineSortedArrays(int[] arr1, int[] arr2) {
        // Calculate full length of both arrays to know max amount of compare operations
        int length = arr1.length + arr2.length;
        // Create new array with the combined size
        int[] solution = new int[length];
        // Indices to keep track of what numbers we are comparing in both arrays
        int nIndex = 0;
        int mIndex = 0;

        // For the the length of both arrays combined
        for (int i = 0; i < arr1.length + arr2.length; i++) {
            if (nIndex == arr1.length) {
                // If the nIndex is the same as the length of arr1, use subAppendArray() to finish.
                return subAppendArray(solution, arr2, i, mIndex);

            } else if (mIndex == arr2.length) {
                // If the mIndex is the same as the length of arr2, use subAppendArray() to finish.
                return subAppendArray(solution, arr1, i, nIndex);

            } else if (arr1[nIndex] >= arr2[mIndex]) {
                // If the current number in arr1 is GTE than current arr2 number
                // Append the arr2 number to the array and increment it's index
                solution[i] = arr2[mIndex];
                mIndex++;

            } else {
                // If the current number in arr1 is LT than current arr2 number
                // Append the arr1 number to the array and increment it's index
                solution[i] = arr1[nIndex];

                nIndex++;

            }

        }
        // Error
        return null;

    }

    /***
     * Appends the tail of appender starting from n to appendee on index.
     * @param appendee List to be appended to. Will be mutated.
     * @param appender List from where the appended data is taken.
     * @param index Where to start appending
     * @param n Where does the tail of the appender start?
     * @return Appended array
     */
    public int[] subAppendArray(int[] appendee, int[] appender, int index, int n) {

        for (int i = n; i < appender.length; i++) {

            appendee[index] = appender[i];

            index++;

        }
        return appendee;

    }

}
