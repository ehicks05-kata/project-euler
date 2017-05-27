package net.ehicks.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EulerUtil
{
    public static List<Integer> primes;
    public static Map<Integer, Integer> primesMap = new HashMap<>();

    public static void generatePrimes(int maximumPrime)
    {
        primes = getPrimesInRange(2, maximumPrime);
        for (Integer prime : primes)
            primesMap.put(prime, prime);
    }

    public static List<Integer> getPrimesInRangeMemoized(int from, int to)
    {
        if (from < 2) from = 2;
        List<Integer> primesInRange = new ArrayList<>();

        for (Integer prime : primes)
            if (prime >= from && prime <= to)
                primesInRange.add(prime);

        return primesInRange;
    }

    public static boolean isPrimeMemoized(int testNum)
    {
        // if testNum is <= to the largest prime we know of, consult the map
        if (testNum <= primes.get(primes.size() - 1))
            return primesMap.get(testNum) != null;

        // if testNum is <= to twice the largest prime we know of, see if it is divisible by any primes in the list
        if (testNum <= primes.get(primes.size() - 1) * 2)
        {
            for (Integer prime : primes)
                if (testNum % prime == 0)
                    return false;
            return true;
        }

        //
        for (int count = primes.get(primes.size() - 1); count < testNum / 2; count++)
            if (testNum % count == 0)
                return false;

        return true;
    }

    // Internal Methods
    private static List<Integer> getPrimesInRange(int from, int to)
    {
        if (from < 2) from = 2;
        List<Integer> primesInRange = new ArrayList<>();

        for (int index = from; index < to; index++)
            if (isPrime(index))
                primesInRange.add(index);

        return primesInRange;
    }

    private static boolean isPrime(int testNum)
    {
        for (int count = 2; count < ((int) Math.sqrt(testNum) + 1); count++)
            if (testNum % count == 0)
                return false;

        return true;
    }

    public static int getGCD(int n1, int n2)
    {
        return BigInteger.valueOf(n1).gcd(BigInteger.valueOf(n2)).intValue();
    }
}