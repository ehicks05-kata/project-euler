package net.ehicks.euler;/*
The first known prime found to exceed one million digits was discovered in 1999,
and is a Mersenne prime of the form 2^6972593 − 1; it contains exactly 2,098,960 digits.
Subsequently other Mersenne primes, of the form 2^p − 1, have been found which contain more digits.

However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×2^7830457 + 1.

Find the last ten digits of this prime number.
 */

import java.math.BigInteger;

public class Problem097
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();

        naiveWay();

        fasterWay();

        timer.printDuration();
    }

    private static void fasterWay()
    {
        long i = 28433;
        for (int j = 1; j <= 7830457; j++)
        {
            i *= 2;
            i = i % 10_000_000_000L;
        }
        i += 1;

        System.out.println("Find the last ten digits of this prime number: " + i);
    }

    private static void naiveWay()
    {
        BigInteger prime = BigInteger.valueOf(2);
        prime = prime.pow(7830457);
        prime = prime.multiply(BigInteger.valueOf(28433));
        prime = prime.add(BigInteger.ONE);

        String primeString = prime.toString();

        String lastTenDigits = primeString.substring(primeString.length() - 10);
        System.out.println("Find the last ten digits of this prime number: " + lastTenDigits);

    }
}
