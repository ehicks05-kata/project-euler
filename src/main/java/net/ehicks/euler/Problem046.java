package net.ehicks.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

9 = 7 + 2×12
15 = 7 + 2×22
21 = 3 + 2×32
25 = 7 + 2×32
27 = 19 + 2×22
33 = 31 + 2×12

It turns out that the conjecture was false.

What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
*/
public class Problem046
{
    private static List<Integer> primes = new ArrayList<>();
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        primes = getPrimes(10_000);

        int answer = 0;
        for (int i = 7; i < 2_000_000; i++)
        {
            if (i % 2 == 1 && !isPrime(i))
            {
                if (meetsCriteria(i))
                {
                    answer = i;
                    break;
                }
            }
        }

        System.out.println("The smallest odd composite that cannot be written as the sum of a prime and twice a square: " + answer);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static boolean meetsCriteria(int i)
    {
        // can it be written as the sum of a prime and twice a square?
        for (Integer prime : primes)
        {
            for (int square = 1; square < 1000; square++)
            {
                if (i == prime + (2 * (square * square)))
                    return false;
            }
        }

        return true;
    }

    private static List<Integer> getPrimes(int limit)
    {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        primes.add(3);

        for (int i = 5; i < limit; i++)
        {
            boolean prime = true;
            for (int j = 2; j < i / 2; j++)
            {
                if (i % j == 0) prime = false;
            }
            if (prime) primes.add(i);
        }

        return primes;
    }

    private static boolean isPrime(int test)
    {
        return BigInteger.valueOf(test).isProbablePrime(8);
    }
}
