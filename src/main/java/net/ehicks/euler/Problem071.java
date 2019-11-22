package net.ehicks.euler;/*
Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

It can be seen that 2/5 is the fraction immediately to the left of 3/7.

By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7.
 */

public class Problem071
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();

        double threeSevenths = (double) 3/7;
        int[] closestFraction = {2,5};
        double closestFractionValue = (double) closestFraction[0] / closestFraction[1];

        for (int denominator = 1; denominator <= 1_000_000; denominator++)
        {
            int startingNumerator = (int) (denominator * closestFractionValue);
            int endingNumerator = (int) (denominator * threeSevenths) + 1;
            for (int numerator = startingNumerator; numerator < endingNumerator; numerator++)
            {
                double value = (double) numerator / denominator;
                if (value <= closestFractionValue || value >= threeSevenths)
                    continue;

                closestFraction = new int[]{numerator, denominator};
                closestFractionValue = value;
            }
        }

        int[] closestFractionReduced = getReducedFraction(closestFraction[0], closestFraction[1]);

        System.out.println("find the numerator of the fraction immediately to the left of 3/7: " + closestFractionValue + "(" + closestFractionReduced[0] + "/" + closestFractionReduced[1] + ")");
        timer.printDuration();
    }

    public static int[] getReducedFraction(int numerator, int denominator)
    {
        int gcd = EulerUtil.getGCD(numerator, denominator);
        if (gcd > 1)
            return new int[]{numerator / gcd, denominator / gcd};
        else
            return new int[]{numerator, denominator};
    }
}
