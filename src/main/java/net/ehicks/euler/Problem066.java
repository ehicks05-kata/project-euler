package net.ehicks.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
Consider quadratic Diophantine equations of the form:

x^2 - Dy^2 = 1

For example, when D=13, the minimal solution in x is 649^2 - 13*180^2 = 1.

It can be assumed that there are no solutions in positive integers when D is square.

By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:

3^2 - 2*2^2 = 1
2^2 - 3*1^2 = 1
9^2 - 5*4^2 = 1
5^2 - 6*2^2 = 1
8^2 - 7*3^2 = 1

Hence, by considering minimal solutions in x for D <= 7, the largest x is obtained when D=5.

Find the value of D <= 1000 in minimal solutions of x for which the largest value of x is obtained.
*/
public class Problem066
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        List<Integer> squares = getSquares();
        int dWithLargestX = 0;
        BigInteger largestX = BigInteger.ZERO;
        BigInteger x;
        for (int d = 2; d < 1001; d++)
        {
            if (squares.contains(d)) continue;
            x = getMinimalSolutionX(d);
            if (x.compareTo(largestX) > 0)
            {
                largestX = x;
                dWithLargestX = d;
            }
        }

        System.out.println("Find the value of D <= 1000 in minimal solutions of x for which the largest value of x is obtained: " + dWithLargestX);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static BigInteger getMinimalSolutionX(int d)
    {
        PellSolver pellSolver = new PellSolver(d, 1);
        BigInteger[] solution = pellSolver.getNext();
//        String x = solution[0].toString();
//        String y = solution[1].toString();
//        System.out.println(x +"^2 - " + d + "*" + y + "^2 = 1");

        return solution[0];
    }

    private static List<Integer> getSquares()
    {
        List<Integer> squares = new ArrayList<>();
        for (int i = 2; i < 32; i++) squares.add(i*i);
        return squares;
    }
}