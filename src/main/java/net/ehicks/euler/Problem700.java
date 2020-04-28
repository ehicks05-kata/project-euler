package net.ehicks.euler;

/*
Leonhard Euler was born on 15 April 1707.

Consider the sequence 1504170715041707n mod 4503599627370517.

An element of this sequence is defined to be an Eulercoin if it is strictly smaller than all previously found Eulercoins.

For example, the first term is 1504170715041707 which is the first Eulercoin.
The second term is 3008341430083414 which is greater than 1504170715041707 so is not an Eulercoin.
However, the third term is 8912517754604 which is small enough to be a new Eulercoin.

The sum of the first 2 Eulercoins is therefore 1513083232796311.

Find the sum of all Eulercoins.
* */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem700
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();

        List<BigInteger> eulerCoins = new ArrayList<>();

        // todo: this is too slow
        for (BigInteger i = BigInteger.ONE; i.compareTo(firstTerm) < 0; i = i.add(BigInteger.ONE))
        {
            BigInteger nthTerm = getNthTerm(i);

            if (eulerCoins.isEmpty() || nthTerm.compareTo(eulerCoins.get(eulerCoins.size() - 1)) < 0)
            {
                eulerCoins.add(nthTerm);
                System.out.println(timer.getDuration() + " : " + "i=" + i + " : " + nthTerm);
            }
        }

        BigInteger sum = eulerCoins.stream().reduce(BigInteger::add).orElseThrow();
        System.out.println("Sum: " + sum);
        timer.printDuration();
    }

    private static final BigInteger firstTerm = new BigInteger("1504170715041707");
    private static final BigInteger secondTerm = new BigInteger("4503599627370517");
    private static BigInteger getNthTerm(BigInteger n)
    {
        return firstTerm.multiply(n).mod(secondTerm);
    }
}
