package net.ehicks.euler;

//The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime factor of the number 600851475143 ?
public class Problem03
{
    public static void main(String[] args)
    {
        final double TARGET_NUM = 600851475143D;

        for (double outerCount = 2; outerCount < TARGET_NUM; outerCount++)
        {
            if (isPrime(outerCount) && isAFactor(outerCount, TARGET_NUM))
            {
                System.out.println(outerCount);
            }
        }
    }

    public static boolean isPrime (double test)
    {
        boolean primeTestResult = true;

        for (double count = 2; count < test; count++)
        {
            if (test % count == 0)
            {
                primeTestResult = false;
            }
        }
        return primeTestResult;
    }

    public static boolean isAFactor (double possibleFactor, double testNum)
    {
        boolean isAFactorTestResult = false;

        if (testNum % possibleFactor == 0)
        {
            isAFactorTestResult = true;
        }
        return isAFactorTestResult;
    }
}
