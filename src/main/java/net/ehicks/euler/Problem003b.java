package net.ehicks.euler;

//The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime factor of the number 600851475143 ?
public class Problem003b
{
    public static void main(String[] args)
    {
        factorize(600851475143L);
        System.out.println(answer);
    }

    private static long answer = 0;

    private static void factorize(long n)
    {
        if (!isPrime(n))
        {
            long smallestPrimeFactor = getSmallestPrimeFactor(n);
            long pair = n / smallestPrimeFactor;

            if (smallestPrimeFactor > answer)
                answer = smallestPrimeFactor;
            if (isPrime(pair) && pair > answer)
                answer = pair;

            factorize(pair);
        }
    }

    private static long getSmallestPrimeFactor(long n)
    {
        for (long i = 2; i < n; i++)
            if (n % i == 0 && isPrime(i))
                return i;

        return 0;
    }

    public static boolean isPrime (long test)
    {
        if (test == 2)
            return true;

        for (long i = 3; i < test; i++)
            if (test % i == 0)
                return false;

        return true;
    }
}
