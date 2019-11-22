package net.ehicks.euler;

/*
The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
* */
public class Problem033
{
    public static void main(String[] args)
    {
        int min = 10;
        int max = 99;
        boolean foundCuriousFraction = false;
        int resultantNumerator = 0;
        int resultantDenominator = 0;

        for (int denominator = min; denominator < max; denominator++)
        {
            for (int numerator = min; numerator < denominator; numerator++)
            {
                String num = String.valueOf(numerator);
                String n1 = num.substring(0, 1);
                String n2 = num.substring(1, 2);
                String den = String.valueOf(denominator);
                String d1 = den.substring(0, 1);
                String d2 = den.substring(1, 2);

                double actualQuotient = (double) numerator / denominator;
                double naiveQuotient = 0;

                if (n1.equals(d2) && n2.equals(d1))
                    continue;
                if (!n1.equals(d2) && !n2.equals(d1))
                    continue;

                if (n1.equals(d2)) // keep the outers
                    naiveQuotient = Double.parseDouble(n2) / Double.parseDouble(d1);
                if (n2.equals(d1)) // keep the inners
                    naiveQuotient = Double.parseDouble(n1) / Double.parseDouble(d2);

                if (naiveQuotient == actualQuotient)
                {
                    if (!foundCuriousFraction)
                    {
                        foundCuriousFraction = true;
                        resultantNumerator = numerator;
                        resultantDenominator = denominator;
                    }
                    else
                    {
                        resultantNumerator *= numerator;
                        resultantDenominator *= denominator;
                    }
                    System.out.println(numerator + " / " + denominator);
                }
            }
        }

        System.out.println("Product of the four fractions: " + resultantNumerator + " / " + resultantDenominator);
        System.out.println("Product of the four fractions in lowest common terms: " + resultantNumerator + " / " + resultantDenominator);
        System.out.println("Denominator of the product of the four fractions: "); // I copped out on reducing the fraction because I could eyeball it. todo: finish this when feeling inspired.
    }
}
