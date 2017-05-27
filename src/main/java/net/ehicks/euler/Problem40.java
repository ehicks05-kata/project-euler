package net.ehicks.euler;

/*
An irrational decimal fraction is created by concatenating the positive integers:

0.123456789101112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d[1] x d[10] x d[100] x d[1000] x d[10000] x d[100000] x d[1000000]
 */
public class Problem40
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        String decimalFraction = "";
        for (int i = 1; decimalFraction.length() < 1_100_000; i++)
            decimalFraction += i;

        int product = Integer.valueOf(decimalFraction.substring(0, 1)) * Integer.valueOf(decimalFraction.substring(9, 10)) *
                Integer.valueOf(decimalFraction.substring(99, 100)) * Integer.valueOf(decimalFraction.substring(999, 1000)) *
                Integer.valueOf(decimalFraction.substring(9999, 10000)) * Integer.valueOf(decimalFraction.substring(99999, 100000)) *
                Integer.valueOf(decimalFraction.substring(999999, 1_000_000));

        System.out.println("Answer:" + product);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}