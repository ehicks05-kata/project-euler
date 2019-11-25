package net.ehicks.euler;

/*
The proper divisors of a number are all the divisors excluding the number itself.
For example, the proper divisors of 28 are 1, 2, 4, 7, and 14.
As the sum of these divisors is equal to 28, we call it a perfect number.

Interestingly the sum of the proper divisors of 220 is 284 and the sum of the proper divisors of 284 is 220,
forming a chain of two numbers. For this reason, 220 and 284 are called an amicable pair.

Perhaps less well known are longer chains. For example, starting with 12496, we form a chain of five numbers:

12496 ? 14288 ? 15472 ? 14536 ? 14264 (? 12496 ? ...)

Since this chain returns to its starting point, it is called an amicable chain.

Find the smallest member of the longest amicable chain with no element exceeding one million.
* */

import java.util.*;

public class Problem095
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();

        List<Integer> longestChain = new ArrayList<>();
        Map<Integer, Integer> testedNumbers = new HashMap<>();
        Set<List<Integer>> chains = new HashSet<>();

        for (int i = 1; i < 1_000_000; i++)
        {
            if (testedNumbers.containsKey(i))
                continue;
            List<Integer> chain = new ArrayList<>(Arrays.asList(i));

            int temp = i;
            while (true)
            {
                int sumOfDivisors = sumOfDivisors(temp);
                testedNumbers.put(sumOfDivisors, sumOfDivisors);

                if (sumOfDivisors > 1_000_000)
                    break;
                if (sumOfDivisors == temp)
                    break;
                if (chain.contains(sumOfDivisors))
                {
                    chain = chain.subList(chain.indexOf(sumOfDivisors), chain.size());
                    rotateChain(chain);

                    boolean newLongest = false;
                    if (chain.size() > longestChain.size())
                    {
                        longestChain = chain;
                        newLongest = true;
                    }

                    if (!chains.contains(chain))
                    {
                        chains.add(chain);
                        System.out.println("new " + (newLongest ? "longest " : "") + "chain: " + chain);
                    }

                    break;
                }

                chain.add(sumOfDivisors);
                temp = sumOfDivisors;
            }

            testedNumbers.put(i, i);
        }

        chains.stream()
                .max(Comparator.comparingInt(List::size))
                .ifPresent(list -> System.out.println(list.get(0)));

        timer.printDuration();
    }

    // rotate chain until smallest value is in front
    private static void rotateChain(List<Integer> chain)
    {
        int smallestValue = Integer.MAX_VALUE;
        int smallestValueIndex = 0;
        for (int i = 0; i < chain.size(); i++)
        {
            if (chain.get(i) < smallestValue)
            {
                smallestValue = chain.get(i);
                smallestValueIndex = i;
            }
        }

        while (smallestValueIndex > 0)
        {
            Collections.rotate(chain, -1);
            smallestValueIndex--;
        }
    }

    private static Map<Integer, Integer> divisorSums = new HashMap<>();
    private static int sumOfDivisors(int n)
    {
        if (divisorSums.containsKey(n))
            return divisorSums.get(n);

        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++)
        {
            if (n % i == 0)
            {
                sum += i;

                int conjugate = n / i;
                if (conjugate != i)
                    sum += conjugate;
            }
        }

        divisorSums.put(n, sum);
        return sum;
    }
}
