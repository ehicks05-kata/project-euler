package net.ehicks.euler;/*
Euler discovered the remarkable quadratic formula:

n² + n + 41

It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.

The incredible formula  n² - 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, -79 and 1601, is -126479.

Considering quadratics of the form:

n² + an + b, where |a| < 1000 and |b| < 1000

where |n| is the modulus/absolute value of n
e.g. |11| = 11 and |-4| = 4
Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
*/

import java.util.HashMap;
import java.util.Map;

public class Problem027
{
    public static void main(String[] args)
    {
        int n;

        // We will store our results in a map in the form of "a:b" : largest value of n we find using that a and b.
        Map<String, Integer> results = new HashMap<>();

        for (int a = -999; a < 1000; a++)
        {
            for (int b = -999; b < 1000; b++)
            {
                n = 0;
                results.put("" + a + ":" + b, n); // initialize this map key
                boolean discoveredANonPrime = false;
                while (!discoveredANonPrime)
                {
                    int quadraticResult = applyQuadraticFormula(n, a, b);
                    boolean prime = isPrime(quadraticResult);
                    if (prime)
                        results.put("" + a + ":" + b, n);
                    else
                        discoveredANonPrime = true;

                    n++;
                }
            }
        }

        String answer = "";
        int nValueOfAnswer = 0;
        for (String key : results.keySet())
        {
            int keyValue = results.get(key);
            if (keyValue > nValueOfAnswer)
            {
                answer = key;
                nValueOfAnswer = keyValue;
            }
        }

        System.out.println(answer + " -> " + nValueOfAnswer);
        String[] coefficients = answer.split(":");
        int productOfCoefficients = Integer.parseInt(coefficients[0]) * Integer.parseInt(coefficients[1]);

        System.out.println("Product of coefficients: " + productOfCoefficients);
    }

    private static int applyQuadraticFormula(int n, int a, int b)
    {
        return (n * n) + (a * n) + b;
    }

    private static boolean isPrime(int testNumber)
    {
        testNumber = Math.abs(testNumber);
        for (int i = 2; i < testNumber; i++)
        {
            if (testNumber % i == 0)
                return false;
        }
        return true;
    }
}
