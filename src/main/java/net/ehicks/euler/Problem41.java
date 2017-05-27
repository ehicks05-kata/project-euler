package net.ehicks.euler;

import java.math.BigInteger;

/*
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.

What is the largest n-digit pandigital prime that exists?
 */
public class Problem41
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        int pandigitalPrime = 0;

        for (int i = 1; i < 1_000_000_000; i+=2)
        {
            if (isPrime(i) && isPandigital(i))
            {
                pandigitalPrime = i;
                System.out.println(i);
            }
        }

        System.out.println("The largest n-digit pandigital prime that exists:" + pandigitalPrime);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static boolean isPandigital(int number)
    {
        String digitString = String.valueOf(number);

        // each digit must occur once and only once
        for (int i = 1; i <= digitString.length(); i++)
        {
            String digit = String.valueOf(i);
            int occurrences = digitString.length() - digitString.replace(digit, "").length();
            if (occurrences != 1)
                return false;
        }

        return true;
    }

    private static boolean isPrime(long num)
    {
        return BigInteger.valueOf(num).isProbablePrime(8);
    }
}
