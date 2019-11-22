package net.ehicks.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
A googol (10^100) is a massive number: one followed by one-hundred zeros;
100^100 is almost unimaginably large: one followed by two-hundred zeros.
Despite their size, the sum of the digits in each number is only 1.

Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
*/
public class Problem056
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        int maximalDigitalSum = 0;
        for (int a = 1; a < 100; a++)
        {
            for (int b = 1; b < 100; b++)
            {
                int digitalSum = getDigitalSum(a, b);
                System.out.println(a + "^" + b + ": " + digitalSum  + ": " + getPower(a, b));

                if (digitalSum > maximalDigitalSum)
                    maximalDigitalSum = digitalSum;
            }
        }

        System.out.println("Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum: " + maximalDigitalSum);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static String getPower(int a, int b)
    {
        BigInteger power = BigInteger.valueOf(a).pow(b);
        return power.toString();
    }

    private static int getDigitalSum(int a, int b)
    {
        int digitalSum = 0;

        List<String> digits = new ArrayList<>(Arrays.asList(getPower(a, b).split("")));
        digits.remove(0);
        for (String digit : digits)
            digitalSum += Integer.valueOf(digit);

        return digitalSum;
    }
}