package net.ehicks.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways:
(i) each of the three terms are prime, and,
(ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
*/
public class Problem049
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        List<Integer> primes = getPrimes(1000, 10_000);
        System.out.println("Building primes took " + (System.currentTimeMillis() - startTime) + " ms.");

        String answer = "";

        outermost:
        for (Integer p1 : primes)
        {
            for (Integer p2 : primes)
            {
                if (!isPermutation(p1, p2)) continue;
                if (p1.equals(p2)) continue;
                for (Integer p3 : primes)
                {
                    if (!isPermutation(p1, p3) || !isPermutation(p2, p3)) continue;
                    if (p1.equals(p3) || p2.equals(p3)) continue;
                    // at this point they are all distinct permutations

                    // cant use the example
                    if (p1 == 1487 || p1 == 4817 || p1 == 8147) continue;

                    int diffBetween1And2 = p2 - p1;
                    int diffBetween2And3 = p3 - p2;
                    if (diffBetween1And2 == diffBetween2And3)
                    {
                        answer = "" + p1 + p2 + p3 + " : " + p1 + ", " + p2 + ", " + p3 + ", " ;
                        break outermost;
                    }
                }
            }
        }

        System.out.println("What 12-digit number do you form by concatenating the three terms in this sequence?: " + answer);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static List<Integer> getPrimes(int start, int end)
    {
        List<Integer> primes = new ArrayList<>();

        if (start < 5)
        {
            start = 5;
            primes.add(2);
            primes.add(3);
        }
        for (int i = start; i < end; i++)
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

    private static boolean isPermutation(int number1, int number2)
    {
        String n1String = String.valueOf(number1);
        String n2String = String.valueOf(number2);
        List<String> n1Chars = new ArrayList<>(Arrays.asList(n1String.split("")));
        List<String> n2Chars = new ArrayList<>(Arrays.asList(n2String.split("")));
        Collections.sort(n1Chars);
        Collections.sort(n2Chars);
        for (int i = 0; i < n1Chars.size(); i++)
            if (!n1Chars.get(i).equals(n2Chars.get(i))) return false;

        return true;
    }
}
