package CodeWars.problem4;

import java.math.BigInteger;

public class ProblemFour_v4 {
    public static void main(String[] args) {
        BigInteger n = new BigInteger("-4"); // Replace with the desired Fibonacci number
        BigInteger res = fib(n);
        System.out.println("F(" + n + ") = " + res);
    }

    public static BigInteger[] calc(BigInteger num) {
        // F(0) := 0
        if (num.equals(BigInteger.ZERO)) {
            return new BigInteger[]{BigInteger.ZERO, BigInteger.ONE};
        }
        // F(1) := 1
        if (num.equals(BigInteger.ONE)) {
            return new BigInteger[]{BigInteger.ONE, BigInteger.ONE};
        }
        // Recursively calculate Fibonacci
        BigInteger[] ab = calc(num.divide(BigInteger.TWO));
        BigInteger a = ab[0];
        BigInteger b = ab[1];
        // Matrix exponentiation method
        BigInteger p = a.multiply(b.multiply(BigInteger.valueOf(2)).subtract(a));
        BigInteger q = b.multiply(b).add(a.multiply(a));
        // If n odd, return F(n) and F(n-1)
        if (num.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return new BigInteger[]{p, q};
        } else {
            return new BigInteger[]{q, p.add(q)};
        }

    }

    public static BigInteger fib(BigInteger num) {
        // If num is ℤ⁺, calculate F(num)
        if (num.compareTo(BigInteger.ZERO) >= 0) {
            return calc(num)[0];
        }
        // Num is negative. Calculate Fib(-num) and handle signum
        BigInteger[] result = calc(num.negate());
        if (num.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return result[0].negate();
        } else {
            return result[0];
        }
    }
}
