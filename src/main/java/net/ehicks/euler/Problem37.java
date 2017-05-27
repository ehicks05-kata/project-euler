package net.ehicks.euler;

import java.math.BigInteger;

/*
The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right,
and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
*/
public class Problem37
{
    // 1-10 = 748294
    public static int sum = 0;

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        int min = 11;
        int max = 1_000_000;
//        int cores = 4;
//        int workPerCore = max / cores;
//
//        for (int i = 0; i < cores; i++)
//        {
//            int start = i * workPerCore + 1;
//            if (start < 11) start = 11;
//            int end   = (i + 1) * workPerCore;
//            new Thread(new PrimeThread(start, end)).start();
//        }

        for (int i = min; i < max; i += 2)
        {
            if (isPrime(i) && isTruncatable(i))
            {
                System.out.println(i);
                Problem37.sum += i;
            }
        }

        System.out.println("Sum of the only eleven primes that are both truncatable from left to right and right to left: " + sum);
        System.out.println("Took " + getDuration(startTime));
    }

    private static String getDuration(long start)
    {
        long durationMillis = System.currentTimeMillis() - start;
        return durationMillis + " ms.";
    }

    public static boolean isTruncatable(long num)
    {
        String number = String.valueOf(num);
        while (number.length() > 1)
        {
            number = number.substring(0, number.length() - 1);
            if (!isPrime(Integer.valueOf(number))) return false;
        }

        number = String.valueOf(num);
        while (number.length() > 1)
        {
            number = number.substring(1, number.length());
            if (!isPrime(Integer.valueOf(number))) return false;
        }

        return true;
    }

    public static boolean isPrime(long num)
    {
        return BigInteger.valueOf(num).isProbablePrime(8);
    }
}
