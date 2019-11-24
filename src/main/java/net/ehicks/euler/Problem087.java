package net.ehicks.euler;

/*
The smallest number expressible as the sum of a prime square, prime cube, and prime fourth power is 28.
In fact, there are exactly four numbers below fifty that can be expressed in such a way:

28 = 2^2 + 2^3 + 2^4
33 = 3^2 + 2^3 + 2^4
49 = 5^2 + 2^3 + 2^4
47 = 2^2 + 3^3 + 2^4

How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?
*/

import java.util.*;

public class Problem087
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        int upperLimit = 50_000_000;
        Set<Integer> answers = new HashSet<>();

        List<Integer> primes = getPrimes(maxLegalBase(2, upperLimit));
        primes.forEach(prime1 -> {
            int num1 = (int) Math.round(Math.pow(prime1, 2));

            primes.stream()
                    .takeWhile(prime -> prime < maxLegalBase(3, upperLimit - num1))
                    .forEach(prime2 -> {
                        int num2 = (int) Math.round(Math.pow(prime2, 3));

                        primes.stream()
                                .takeWhile(prime -> prime < maxLegalBase(4, upperLimit - num1 - num2))
                                .forEach(prime3 -> {
                                    int num3 = (int) Math.round(Math.pow(prime3, 4));
                                    int sum = num1 + num2 + num3;

                                    if (sum < upperLimit)
                                        answers.add(sum);
                                });
                    });
        });

        System.out.println(answers.size());
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int maxLegalBase(int exp, int limit)
    {
        return (int) Math.ceil(Math.pow(limit, 1.0 / exp));
    }

    private static List<Integer> getPrimes(int maxValue)
    {
        if (maxValue < 2)
            return new ArrayList<>();

        List<Integer> primes = new ArrayList<>(Collections.singletonList(2));

        outer:
        for (int i = 3; i < maxValue; i++)
        {
            for (int j = 0; j < primes.size(); j++)
            {
                int divisor = primes.get(j);

                if (i % divisor == 0)
                    continue outer;

                if (divisor > Math.sqrt(i))
                {
                    primes.add(i);
                    break;
                }
            }
        }

        return primes;
    }
}
