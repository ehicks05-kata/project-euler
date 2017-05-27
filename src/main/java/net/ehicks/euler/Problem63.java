package net.ehicks.euler;/*
The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit number, 134217728=8^9, is a ninth power.

How many n-digit positive integers exist which are also an nth power?
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem63
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        int powerfulIntegersCount = 0;

        for (int power = 1; power < 30; power++)
        {
            List<BigInteger> powerfulIntegers = getPowerNumbers(power);
            powerfulIntegersCount += powerfulIntegers.size();
        }

        System.out.println("How many n-digit positive integers exist which are also an nth power: " + powerfulIntegersCount);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static List<BigInteger> getPowerNumbers(int power)
    {
        List<BigInteger> powerNumbers = new ArrayList<>();
        BigInteger index = new BigInteger("1");
        while (true)
        {
            BigInteger powerNumber = index.pow(power);
            if (powerNumber.toString().length() == power) powerNumbers.add(powerNumber);
            if (powerNumber.toString().length() > power) break;
            index = index.add(BigInteger.ONE);
        }

        return powerNumbers;
    }
}
