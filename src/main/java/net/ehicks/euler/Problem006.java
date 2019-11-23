package net.ehicks.euler;/*The difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025  385 = 2640.
Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.*/

public class Problem006
{
    public static void main(String[] args)
    {
        final int TEST_NUM = 100;
        findDifference(TEST_NUM);
    }

    public static void findDifference(int n)
    {
        int sumOfSquares = 0, sums = 0, squareOfSums = 0, difference;

        for(int num = 1; num <= n; num++)
        {
            sumOfSquares += (num*num);
            sums += num;
            squareOfSums = (sums*sums);
        }
        difference = (squareOfSums - sumOfSquares);
        System.out.println(squareOfSums+" - "+sumOfSquares+" = "+difference);
    }
}