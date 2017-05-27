package net.ehicks.euler;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
 * <p/>
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 * <p/>
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
 * <p/>
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class Problem26
{
    private static final int LARGEST_DENOMINATOR = 10;
    private static final int DECIMAL_PLACES = 10;

    public static void main(String[] args)
    {
        // Create a list of quotients
        ArrayList<String> quotients = generateQuotients(LARGEST_DENOMINATOR, DECIMAL_PLACES);

        // Create subStrings for each quotient
        ArrayList<ArrayList<String>> subStrings = generateSubStrings(quotients);

        // Test each subString against equally sized pieces of the quotient
        ArrayList<ArrayList<Boolean>> subStringsResults = examineQuotients(subStrings, quotients);

        printResults(subStringsResults);
    }

    private static ArrayList<String> generateQuotients(int largestDenominator, int decimalPlaces)
    {
        BigDecimal numerator = new BigDecimal("1.00");
        BigDecimal denominator = new BigDecimal("2.00");
        ArrayList<String> quotients = new ArrayList<String>();
        for (int i = 2; i < largestDenominator; i++) //start at 1/2
        {
            BigDecimal quotient;
            if(i < 11)                      quotient = numerator.divide(denominator, decimalPlaces, BigDecimal.ROUND_HALF_UP);
            else if(i >= 11 && i < 101)     quotient = numerator.divide(denominator, decimalPlaces + 1, BigDecimal.ROUND_HALF_UP);
            else if(i >= 101 && i < 1001)   quotient = numerator.divide(denominator, decimalPlaces + 2, BigDecimal.ROUND_HALF_UP);
            else if(i >= 1001)              quotient = numerator.divide(denominator, decimalPlaces + 3, BigDecimal.ROUND_HALF_UP);
            else                            quotient = numerator.divide(denominator, decimalPlaces + 4, BigDecimal.ROUND_HALF_UP);

            String trimmedQuotient = quotient.toString().substring(2); // remove leading zero and decimal point
            int startIndex = 0;
            if (trimmedQuotient.charAt(0) > 48) startIndex = 0;
            else if (trimmedQuotient.charAt(1) > 48) startIndex = 1;
            else if (trimmedQuotient.charAt(2) > 48) startIndex = 2;
            else startIndex = 3;
            trimmedQuotient = trimmedQuotient.substring(startIndex);

            quotients.add(trimmedQuotient);
            denominator = denominator.add(numerator);
            System.out.println("1/" + i + ": " + quotients.get(i - 2));
        }
        return quotients;
    }

    private static ArrayList<ArrayList<String>> generateSubStrings(ArrayList<String> quotients)
    {
        ArrayList<ArrayList<String>> subStrings = new ArrayList<ArrayList<String>>();
        for (String quotient : quotients)
        {
            ArrayList<String> oneQuotientSubStrings = new ArrayList<String>();

            for (int i = 0; i < quotient.length() / 2; i++)
            {
                String fractionSubstring = quotient.substring(0, i + 1);
                oneQuotientSubStrings.add(i, fractionSubstring);
//                System.out.println(fractionSubstring);
            }
            subStrings.add(oneQuotientSubStrings);
        }
        return subStrings;
    }

    private static ArrayList<ArrayList<Boolean>> examineQuotients(ArrayList<ArrayList<String>> subStrings, ArrayList<String> quotients)
    {
        ArrayList<ArrayList<Boolean>> subStringsResults = new ArrayList<ArrayList<Boolean>>();
        for (int i = 0; i < quotients.size(); i++) //go through each quotient
        {
            for (int j = 0; j < subStrings.get(i).size(); j++) // go through each substring
            {
                examineQuotient(i, subStrings, quotients, subStringsResults);
            }
        }
        return subStringsResults;
    }

    private static void examineQuotient(int currentQuotient, ArrayList<ArrayList<String>> subStrings, ArrayList<String> quotients, ArrayList<ArrayList<Boolean>> subStringsResults)
    {
        ArrayList<Boolean> currentQuotientSubStringResults = new ArrayList<Boolean>();
        ArrayList<String> currentQuotientSubStrings = subStrings.get(currentQuotient);
        for (int i = 0; i < currentQuotientSubStrings.size(); i++) // go through each substring
        {
            int substringLength = currentQuotientSubStrings.get(i).length();
            boolean isAPattern = true;

            for (int j = 0; j < (Math.floor(DECIMAL_PLACES / substringLength)); j++) // test each substring against a chunk of quotient
            {
                String quotientToCheckAgainst = quotients.get(currentQuotient).substring(j * substringLength, ((j * substringLength) + substringLength));
                if (!currentQuotientSubStrings.get(i).equals(quotientToCheckAgainst))
                {
                    isAPattern = false;
                }
            }
            if (isAPattern) currentQuotientSubStringResults.add(i, Boolean.TRUE);
            if (!isAPattern) currentQuotientSubStringResults.add(i, Boolean.FALSE);
        }
        subStringsResults.add(currentQuotientSubStringResults);
    }

    private static void printResults(ArrayList<ArrayList<Boolean>> subStringsResults)
    {
        for (int i = 2; i < subStringsResults.size(); i++)
        {
            System.out.print("1/" + i + ": ");
            for (int j = 1; j < subStringsResults.get(i).size(); j++)
            {
                if (subStringsResults.get(i).get(j))
                {
                    System.out.print(j + " digits...");
                }
            }
            System.out.println();
        }
    }
}