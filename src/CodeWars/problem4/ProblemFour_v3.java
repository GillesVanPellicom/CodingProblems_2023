package CodeWars.problem4;

import java.math.BigInteger;

public class ProblemFour_v3 {
    public static void main(String[] args) {
        // Too slow
        BigInteger tmp = fib(BigInteger.valueOf(2000000));

        System.out.println("Fib = " + tmp);

//        BigInteger[][] A = {
//                {BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3)},
//                {BigInteger.valueOf(4), BigInteger.valueOf(5), BigInteger.valueOf(6)}};
//        BigInteger[][] B = {
//                {BigInteger.valueOf(7), BigInteger.valueOf(8)},
//                {BigInteger.valueOf(9), BigInteger.valueOf(10)},
//                {BigInteger.valueOf(11), BigInteger.valueOf(12)}};


    }

    /**
     * Calculate Fibonacci sequence quickly using matrix multiplications.
     * F(n) = F(n-1) + F(n-2)
     * @param n nth Fibonacci number
     * @return F(n) as BigInteger
     */
    public static BigInteger fib(BigInteger n) {
        // F(O) := 0 ∧ F(1) := 1
        BigInteger[][] startMatrix = {
                {BigInteger.ZERO, BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ONE}};
        BigInteger[][] currentMatrix = startMatrix;

        // Iterate from i = 0 to n
        for (BigInteger i = BigInteger.ZERO; i.compareTo(n.subtract(BigInteger.ONE)) <= -1; i = i.add(BigInteger.ONE)) {
            currentMatrix = multiplyBigMatrix(currentMatrix, startMatrix);
        }
        return currentMatrix[0][1];
    }

    /**
     * Multiplies BigInteger matrix A with matrix B and returns result.
     * A[m x n] * B[n x p] = C[m x p]
     * @param A matrix A containing BigInteger
     * @param B matrix B containing BigInteger
     * @return Resulting matrix C or null if A or B are null or if A[m x n] * B[n x p] != C[m x p]
     */
    public static BigInteger[][] multiplyBigMatrix(BigInteger[][] A, BigInteger[][] B) {
        // A[m x n] * B[n x p] != C[m x p]. A # columns doesn't match B # rows.

        if (A == null || B == null || A[0].length != B.length) {
            return null;
        }

        // Create variables to convert array notation to mathematical matrix notation
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;

        // Create result matrix C[m x p]
        BigInteger[][] C = new BigInteger[m][p];
        // Calculate result
        // C[i][j] = (A[i][1] * B[1][j]) + (A[i][2] * B[2][j]) + ... + (A[i][n] * B[n][j])
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                BigInteger temp = BigInteger.ZERO;
                for (int k = 0; k < n; k++) {
                    temp = temp.add(A[i][k].multiply(B[k][j]));
                }
                C[i][j] = temp;
            }
        }
        return C;
    }

    /**
     * Takes in a matrix of BigInteger and prints the contents.
     * @param A matrix A containing BigInteger
     */
    public static void printBigMatrix(BigInteger[][] A) {
        // If A is null, throw nullptr.
        if (A == null) {
            throw new NullPointerException("printBigMatrix(): The provided matrix is null.");
        }
        // Print matrix
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
