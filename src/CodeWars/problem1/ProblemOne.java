package CodeWars.problem1;

public class ProblemOne {
    public static void main(String[] args) {
        System.out.println(breakChocolate(3,3));
    }
    public static long breakChocolate(long n, long m) {
        // n-1 breaks for n long chocolate to break into strips
        // m-1 breaks per strip so n times m-1
        return (n-1)+n*(m-1);
    }
}
