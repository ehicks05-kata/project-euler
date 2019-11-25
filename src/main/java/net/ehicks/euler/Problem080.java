package net.ehicks.euler;

/*
It is well known that if the square root of a natural number is not an integer, then it is irrational.
The decimal expansion of such square roots is infinite without any repeating pattern at all.

The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.

For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits
for all the irrational square roots.
*/

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Problem080
{
    public static void main(String[] args)
    {
        int total = IntStream.range(2, 101)
                .map(i -> {
                    if (Arrays.asList(4, 9, 16, 25, 36, 49, 64, 81, 100).contains(i))
                        return 0;

                    BigDecimal sqrt = new BigDecimal(i).sqrt(new MathContext(100, RoundingMode.DOWN));
                    return getDigitalSum(sqrt.toString().replace(".", ""));
                }).sum();

        System.out.println(total);
    }

    private static int getDigitalSum(String number)
    {
        return Arrays.stream(number.split(""))
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
