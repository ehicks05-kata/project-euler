package net.ehicks.euler;/*By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13. What is the 10001st prime number?*/

public class Problem007
{
    public static void main(String[] args)
    {
        final int TEST_NUM = 10001;
        findNthPrime(TEST_NUM);
    }

    public static void findNthPrime(int n)
    {
        int primeCount = 0, num;
        boolean isPrime;

        for(num = 1; primeCount <= n; num++) //this loop will step through every number until we've found enough primes
        {
            isPrime = true; //start it out as true
            for(int count = 2; count < num; count++) //this loop will step through from 2 up to num
            {
                if(num % count == 0) //if num / count ever divides cleanly, then num is not prime and thus isPrime is false
                    isPrime = false;
            }
            if(isPrime) //If isPrime is not flagged as false, increment the counter of primes and print num because it is prime
            {
                primeCount++;
                System.out.println(num);
            }
        }
    }
}