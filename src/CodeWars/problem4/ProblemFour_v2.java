package CodeWars.problem4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class ProblemFour_v2 {
    public static void main(String[] args) {
        System.out.println(fib(BigInteger.valueOf(200000)));
    }
    public static BigInteger fib(BigInteger nn) {
        int n = nn.intValue();
        if (n == 2) {
            return BigInteger.valueOf(1);
        }
        BigDecimal one = new BigDecimal(1);
        BigDecimal sqrtFive = bigSqrt(new BigDecimal(5));
        BigDecimal two = new BigDecimal(2);


        BigDecimal x1 = one.add(sqrtFive).divide(two, RoundingMode.HALF_DOWN);
        BigDecimal x2 = one.subtract(sqrtFive).divide(two, RoundingMode.HALF_DOWN);
        BigDecimal phi;
        BigDecimal phi_;
        if (n >= 0) {
            phi = x1.pow(n);
            phi_ = x2.pow(n);
        } else {
            phi = BigDecimal.ONE.divide(x1.pow(n*-1),
                    RoundingMode.HALF_DOWN);
            phi_ = BigDecimal.ONE.divide(x2.pow(n*-1),
                    RoundingMode.HALF_DOWN);
        }

        BigDecimal result = phi.subtract(phi_).divide(sqrtFive, RoundingMode.HALF_DOWN);

        return result.toBigInteger();
    }
    public static BigDecimal bigSqrt(BigDecimal c){
        return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
    }
    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1){
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }
    private static final BigDecimal SQRT_DIG = new BigDecimal(150);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());
}
