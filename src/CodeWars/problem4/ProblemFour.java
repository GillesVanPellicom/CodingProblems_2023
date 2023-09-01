package CodeWars.problem4;

import java.math.BigInteger;

public class ProblemFour {
    public static void main(String[] args) {
        System.out.println("0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181\n");
        System.out.println("-0 1, -1, 2, -3, 5, -8, 13, -21, 34");
//        System.out.println(fib(BigInteger.valueOf(4)));
//        System.out.println(pow(4,2));
        System.out.println(longFib(-1));
    }

    public static BigInteger fib(BigInteger n) {
        long nn = n.longValue();
        if (nn >= -70 && nn <= 70 ) {
            //F_n = ( (1 + √5)^n - (1 - √5)^n ) / (2^n × √5)
            return BigInteger.valueOf(longFib(nn));
        }
        if (n.compareTo(BigInteger.ZERO) == 0) {
            // If n == 0, F_n = 0
            return BigInteger.ZERO;
        } else if (n.compareTo(BigInteger.ZERO) > 0) {
            // If n > 0, F_n = F_(n-1) + F_(n-2) where F_0 := 0 ∧ F_1 := 1
            BigInteger result1 = BigInteger.ZERO;
            BigInteger result2 = BigInteger.ONE;
            // Iterate from i = 0 to n
            for (BigInteger i = BigInteger.ZERO; i.compareTo(n.subtract(BigInteger.ONE)) <= -1; i = i.add(BigInteger.ONE)) {
                BigInteger temp = result1.add(result2);
                result1 = result2;
                result2 = temp;
            }
            return result2;
        } else {
            n = n.negate();
            // If n < 0, F_n = F_(n-1) - F_(n-2) where F_0 := 0 ∧ F_-1 := -1
            BigInteger result1 = BigInteger.ZERO;
            BigInteger result2 = BigInteger.valueOf(-1);
            // Iterate from i = 0 to n
            for (BigInteger i = BigInteger.ZERO; i.compareTo(n.subtract(BigInteger.ONE)) <= -1; i = i.add(BigInteger.ONE)) {
                BigInteger temp = result1.subtract(result2);
                result1 = result2;
                result2 = temp;
            }
            return result2.negate();
        }
    }

    public static long longFib(long n) {
        boolean signed = n < 0;
        long prev = 0;
        long curr;

        if (signed) {
            if (n == -1) {
                return 1;
            }
            curr = -1;
            for (long i = 2; i <= n * -1L; i++) {
                long temp = prev - curr;
                prev = curr;
                curr = temp;
            }
            curr = curr * -1L;
        } else {
            if (n == 0) {
                return 0;
            } else if (n == 1) {
                return 1;
            }
            curr = 1;

            for (long i = 2; i <= n; i++) {
                long temp = prev + curr;
                prev = curr;
                curr = temp;
            }
        }
        return curr;
    }
}
