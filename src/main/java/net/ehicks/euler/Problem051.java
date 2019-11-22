package net.ehicks.euler;

import java.util.ArrayList;
import java.util.List;

/*
 By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

 By replacing the 3rd and 4th digits of 56**3 with the same digit,
 this 5-digit number is the first example having seven primes among the ten generated numbers,
 yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993.
 Consequently 56003, being the first member of this family, is the smallest prime with this property.

 Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
 */
public class Problem051
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        int answer = 0;

        EulerUtil.generatePrimes(1_000_000);
        List<Integer> primes = EulerUtil.getPrimesInRangeMemoized(2, 200_000);

        outer:
        for (Integer prime : primes)
        {
            System.out.println("checking " + prime);
            String primeString = String.valueOf(prime);

            // create every possible mask for our prime by replacing digits with '*'.
            List<String> maskedPrimes = getMasksForPrime(primeString);

            for (String maskedPrime : maskedPrimes)
            {
                List<Integer> primesFromMaskedPrime = new ArrayList<>();
                int startingIndex = maskedPrime.startsWith("*") ? 1 : 0; // prevents *3 -> 03 being identified as a match
                for (int i = startingIndex; i < 10; i++)
                {
                    String test = maskedPrime.replaceAll("\\*", Integer.toString(i));
                    int testNum = Integer.parseInt(test);
                    if (EulerUtil.isPrimeMemoized(testNum))
                        primesFromMaskedPrime.add(testNum);
                }
                if (primesFromMaskedPrime.size() >= 8)
                {
                    answer = primesFromMaskedPrime.get(0);
                    break outer;
                }
            }
        }

        System.out.println(answer);
        timer.printDuration();
    }

    private static List<String> getMasksForPrime(String primeString)
    {
        List<String> maskedPrimes = new ArrayList<>();

        int loopIterations = ((int) Math.pow(2, primeString.length()));
        for (int i = 1; i < loopIterations - 1; i++)
        {
            String binaryString = convertToBinaryString(primeString.length(), i);

            char[] mask = binaryString.toCharArray();
            String maskedPrime = buildMaskedPrime(primeString, mask);
            if (!maskedPrimes.contains(maskedPrime)) maskedPrimes.add(maskedPrime);
        }
        return maskedPrimes;
    }

    private static String convertToBinaryString(int digits, int i)
    {
        String binaryString = Integer.toString(i, 2);
        while (binaryString.length() < digits)
            binaryString = "0" + binaryString;
        return binaryString;
    }

    private static String buildMaskedPrime(String primeString, char[] mask)
    {
        String workPrime = primeString;
        for (int j = 0; j < mask.length; j++)
        {
            char maskChar = mask[j];
            if (maskChar == '1')
            {
                if (j == 0) workPrime = "*" + workPrime.substring(1, workPrime.length());
                if (j == mask.length - 1) workPrime = workPrime.substring(0, workPrime.length() - 1) + "*";
                else workPrime = workPrime.substring(0, j) + "*" + workPrime.substring(j + 1, workPrime.length());
            }
        }
        return workPrime;
    }
}
