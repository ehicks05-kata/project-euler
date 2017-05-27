package net.ehicks.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
The prime 41, can be written as the sum of six consecutive primes:

41 = 2 + 3 + 5 + 7 + 11 + 13
This is the longest sum of consecutive primes that adds to a prime below one-hundred.

The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.

Which prime, below one-million, can be written as the sum of the most consecutive primes?
*/
public class Problem50
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        List<Integer> primes = getPrimes(1, 300_000);
        System.out.println("Done building primes...wall time: " + (System.currentTimeMillis() - startTime) + " ms.");

        Map<Integer, String> primesMadeFrom20OrMoreConsecutivePrimes = getPrimesMadeFrom20OrMoreConsecutivePrimes(primes); // value is number of consecutive primes added
        System.out.println("Done building primes2...wall time: " + (System.currentTimeMillis() - startTime) + " ms.");

        int mostPrimes = 0;
        int primeWithMostPrimes = 0;
        int indexOfFirstPrime = 0;
        for (Integer prime : primesMadeFrom20OrMoreConsecutivePrimes.keySet())
        {
            String[] values = primesMadeFrom20OrMoreConsecutivePrimes.get(prime).split(":");
            int startPoint = Integer.parseInt(values[0]);
            int consecutiveCount = Integer.parseInt(values[1]);
            if (consecutiveCount > mostPrimes)
            {
                indexOfFirstPrime = startPoint;
                mostPrimes = consecutiveCount;
                primeWithMostPrimes = prime;
            }
        }

        System.out.println("Which prime, below one-million, can be written as the sum of the most consecutive primes: " +
                primeWithMostPrimes + " with " + mostPrimes + " consecutive primes. Index of first prime=" + indexOfFirstPrime);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static List<Integer> getPrimes(int start, int end)
    {
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i < end; i++)
            if (BigInteger.valueOf(i).isProbablePrime(8))
                primes.add(i);
        return primes;
    }

    private static Map<Integer, String> getPrimesMadeFrom20OrMoreConsecutivePrimes(List<Integer> primes)
    {
        Map<Integer, String> primesMadeFrom20OrMoreConsecutivePrimes = new HashMap<>();
        int primesCount = primes.size();
        int largestSpan = primesCount / 21;

        for (int spanSize = 500; spanSize < largestSpan; spanSize++)
        {
            for (int startPoint = 0; startPoint < primesCount - spanSize; startPoint++)
            {
                // go through every possible span that has 'spansize' elements...
                int sum = 0;
                for (int i = startPoint; i < spanSize + startPoint; i++)
                    sum += primes.get(i);
                if (sum < 1_000_000 && BigInteger.valueOf(sum).isProbablePrime(8))
                {
                    primesMadeFrom20OrMoreConsecutivePrimes.put(sum, startPoint + ":" + spanSize);
                }
            }
        }

        return primesMadeFrom20OrMoreConsecutivePrimes;
    }

    private static int getSumOfPrimesInRange(List<Integer> primes, int startIndex, int endIndex)
    {
        int sum = 0;

        for (int i = startIndex; i < endIndex; i++)
            sum += primes.get(i);

        return sum;
    }
}
