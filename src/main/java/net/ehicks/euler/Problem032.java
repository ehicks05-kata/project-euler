package net.ehicks.euler;

import java.util.ArrayList;
import java.util.List;

/*
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
* */
public class Problem032
{
    public static void main(String[] args)
    {
        List<Integer> products = new ArrayList<>();

        int minTestValue = 1;
        int maxTestValue = 123_456_789 / 100; // quick excel testing showed that 123456789 / 1 through 100 was not pandigital.

        for (int multiplicand = minTestValue; multiplicand < maxTestValue; multiplicand++)
        {
            for (int multiplier = minTestValue; multiplier < maxTestValue; multiplier++)
            {
                int product = multiplicand * multiplier;
                String testDigits = String.valueOf(multiplicand) + String.valueOf(multiplier) + String.valueOf(product);

                // THIS OPTIMIZATION DID THE TRICK
                if (testDigits.length() > 9)
                    break;

                if (isPandigitalIdentity(testDigits) && !products.contains(product))
                    products.add(product);
            }
        }

        long sumOfAllProducts = 0;
        for (int product : products)
            sumOfAllProducts += product;

        System.out.println("Sum of all products: " + sumOfAllProducts);
    }

    private static boolean isPandigitalIdentity(String testDigits)
    {
        // each digit 1-9 must occur once and only once
        for (int i = 1; i <= 9; i++)
        {
            String digit = String.valueOf(i);
            int occurrences = testDigits.length() - testDigits.replace(digit, "").length();
            if (occurrences != 1)
                return false;
        }

        return true;
    }
}
