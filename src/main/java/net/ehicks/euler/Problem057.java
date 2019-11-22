package net.ehicks.euler;

import java.math.BigInteger;

/*
It is possible to show that the square root of two can be expressed as an infinite continued fraction.

√ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

By expanding this for the first four iterations, we get:

1 + 1/2 = 3/2 = 1.5
1 + 1/(2 + 1/2) = 7/5 = 1.4
1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where
the number of digits in the numerator exceeds the number of digits in the denominator.

In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
*/
public class Problem057
{
    public static void main(String[] args) throws Exception
    {
        long startTime = System.currentTimeMillis();

        int numberOfSolutions = 0;
        String expansion = "1/2";
        for (int i = 0; i < 1000; i++)
        {
            expansion = getNextExpansion(expansion);

            String simplifiedExpansion = simplifyExpansion(expansion);
            int slashIndex = simplifiedExpansion.indexOf("/");
            int numLength = simplifiedExpansion.substring(0, slashIndex).length();
            int denLength = simplifiedExpansion.substring(slashIndex + 1).length();

            if (numLength > denLength)
                numberOfSolutions++;
        }

        System.out.println("In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?: " + numberOfSolutions);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static String getNextExpansion(String currentExpansion)
    {
        StringBuilder b = new StringBuilder(currentExpansion);
        int charToReplace = currentExpansion.lastIndexOf("2");
        b.replace(charToReplace, charToReplace + 1, "(2+1/2)");
        currentExpansion = b.toString();
        return currentExpansion;
    }

    private static String simplifyExpansion(String expansion)
    {
        while (expansion.contains("("))
        {
            int openParen = expansion.lastIndexOf("(");
            int closeParen = expansion.indexOf(")");
            String workFraction = expansion.substring(openParen + 1, closeParen);
            workFraction = workFraction.substring(workFraction.indexOf("+") + 1);

            int slash = workFraction.indexOf("/");
            BigInteger numerator = new BigInteger(workFraction.substring(0, slash));
            BigInteger denominator = new BigInteger(workFraction.substring(slash + 1));
            BigInteger firstTerm = new BigInteger("2");
            BigInteger addToNumerator = firstTerm.multiply(denominator);
            numerator = numerator.add(addToNumerator);

            String newFraction = denominator + "/" + numerator; // denom over num to account for 1/x...

            String pre = expansion.substring(0, openParen - 2);
            String post = expansion.substring(closeParen + 1);

            expansion = pre + newFraction + post;
        }

        int slash = expansion.indexOf("/");
        BigInteger numerator = new BigInteger(expansion.substring(0, slash));
        BigInteger denominator = new BigInteger(expansion.substring(slash + 1));
        BigInteger addToNumerator = BigInteger.ONE.multiply(denominator);
        numerator = numerator.add(addToNumerator);

        expansion = numerator + "/" + denominator;

        return expansion;
    }
}