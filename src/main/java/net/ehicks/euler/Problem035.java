package net.ehicks.euler;

import java.util.ArrayList;
import java.util.List;

/*
The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

How many circular primes are there below one million?
* */
public class Problem035
{
    public static void main(String[] args)
    {
        int circularPrimeCount = 2; // lets just say 2 and 3 have already been accounted for.
        for (int i = 5; i < 1_000_000; i++)
        {
            if (!isPrime(i)) continue;

            String number = String.valueOf(i);
            if (isCircularPrime(number))
            {
                circularPrimeCount++;
                System.out.println(i);
            }
        }

        System.out.println("Number of Circular Primes below one million: " + circularPrimeCount);
    }

    private static boolean isCircularPrime(String number)
    {
        List<String> rotations = getRotations(number);
        for (String rotation : rotations)
            if (!isPrime(Integer.parseInt(rotation))) return false;

        return true;
    }

    private static List<String> getRotations(String numberToRotate)
    {
        List<String> rotations = new ArrayList<>();
        rotations.add(numberToRotate);
        for (int i = 1; i < numberToRotate.length(); i++)
        {
            String firstChar = numberToRotate.substring(0, 1);
            String remainder = numberToRotate.substring(1, numberToRotate.length());
            numberToRotate = remainder + firstChar;
            rotations.add(numberToRotate);
        }

        return rotations;
    }

    private static boolean isPrime(int num)
    {
        for (int i = 2; i < num / 2; i++)
            if (num % i == 0) return false;

        return true;
    }
}

