package net.ehicks.euler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The first two consecutive numbers to have two distinct prime factors are:

14 = 2 × 7
15 = 3 × 5

The first three consecutive numbers to have three distinct prime factors are:

644 = 2² × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19.

Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?
*/
public class Problem047
{
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        List<Integer> primes = getPrimes(700);
        System.out.println("Building primes took " + (System.currentTimeMillis() - startTime) + " ms.");

        Set<Integer> composites = new HashSet<>();

        for (Integer pf1 : primes)
        {
            if (pf1 * 2 * 3 * 5 > answer) break;
            for (Integer pf2 : primes)
            {
                if (pf1 * pf2 * 2 * 3 > answer) continue;
                for (Integer pf3 : primes)
                {
                    if (pf1 * pf2 * pf3 * 2 > answer) continue;
                    for (Integer pf4 : primes)
                    {
                        if (pf1 * pf2 * pf3 * pf4 > answer) continue;
                        if (!areFactorsDistinct(pf1, pf2, pf3, pf4)) continue ;

                        for (int i = 1; i < 3; i++)
                        {
                            if (i > 1 && pf1 > 3) continue;
                            for (int j = 1; j < 3; j++)
                            {
                                if (j > 1 && pf2 > 3) continue;
                                for (int k = 1; k < 3; k++)
                                {
                                    if (k > 1 && pf3 > 3) continue;
                                    for (int l = 1; l < 3; l++)
                                    {
                                        if (l > 1 && pf4 > 3) continue;
                                        int composite = (int) (Math.pow(pf1, i) * Math.pow(pf2, j) * Math.pow(pf3, k) * Math.pow(pf4, l));
                                        if (composite > answer) continue;

                                        if (!composites.contains(composite))
                                            addComposite(composites, composite);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?: " + answer);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static void addComposite(Set<Integer> composites, int composite)
    {
        composites.add(composite);
        if (composites.size() % 1_000_000 == 0) System.out.println(composites.size());

        int spreeSize = 1;
        int possibleConsecutive = composite;
        String message = String.valueOf(composite);
        while (composites.contains(--possibleConsecutive))
        {
            spreeSize++;
            message = possibleConsecutive + ", " + message;
        }
        possibleConsecutive = composite;
        while (composites.contains(++possibleConsecutive))
        {
            spreeSize++;
            message += ", " + possibleConsecutive;
        }
        if (spreeSize > 3)
        {
            int possibleAnswer = Integer.parseInt(message.substring(0, message.indexOf(",")));
            if (possibleAnswer < answer) answer = possibleAnswer;
            System.out.println(message + "....best answer so far: " + answer);
        }
    }

    private static boolean areFactorsDistinct(Integer pf1, Integer pf2, Integer pf3, Integer pf4)
    {
        return !(pf1.compareTo(pf2) == 0 || pf1.compareTo(pf3) == 0 || pf1.compareTo(pf4) == 0 ||
                pf2.compareTo(pf3) == 0 || pf2.compareTo(pf4) == 0 || pf3.compareTo(pf4) == 0);
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
}
