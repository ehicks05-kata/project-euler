package net.ehicks.euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Take the number 192 and multiply it by each of 1, 2, and 3:

192 × 1 = 192
192 × 2 = 384
192 × 3 = 576
By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
* */
public class Problem038
{
    public static void main(String[] args)
    {
        List<String> pandigitalsFromConcatenatedProducts = new ArrayList<>();
        for (int i = 1; i < 10_000; i++)
        {
            for (int multipliers = 2; multipliers <= 9; multipliers++)
            {
                String concatenatedProduct = String.valueOf(i);
                for (int j = 2; j <= multipliers; j++)
                    concatenatedProduct += i * j;

                if (concatenatedProduct.length() == 9)
                {
                    if (isPandigital(Integer.parseInt(concatenatedProduct)))
                    {
                        pandigitalsFromConcatenatedProducts.add(concatenatedProduct);
                        System.out.println(concatenatedProduct);
                    }
                }
            }
        }

        Collections.sort(pandigitalsFromConcatenatedProducts);
        String answer = pandigitalsFromConcatenatedProducts.get(pandigitalsFromConcatenatedProducts.size() - 1);
        System.out.println("The largest pandigital: " + answer);
    }

    private static boolean isPandigital(int number)
    {
        String digitString = String.valueOf(number);

        // each digit 1-9 must occur once and only once
        for (int i = 1; i <= 9; i++)
        {
            String digit = String.valueOf(i);
            int occurrences = digitString.length() - digitString.replace(digit, "").length();
            if (occurrences != 1)
                return false;
        }

        return true;
    }
}
