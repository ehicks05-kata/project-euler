package net.ehicks.euler;

//What is the value of the first triangle number to have over five hundred divisors?
public class Problem012
{
    //PARAMETERS
    final static int PRIMES_NEEDED = 1500;
    final static int DIVISORS_NEEDED = 500;

    //Create an array of primes
    static int[] primes = createPrimeArray(PRIMES_NEEDED);
    //Create an array for the number of each prime factor
    static int[] primeCount;

    public static void main(String[] args)
    {
        //current triangle number
        int triNum;
        //this is what the triangle numbers are incremented by
        int triAccum = 1;
        //the program ends when this gets high enough
        int numOfDivisors = 0;

        //increment by triangle number, loop until we have a triNum with enough divisors
        for(triNum = 1; numOfDivisors <= DIVISORS_NEEDED; triNum += triAccum)
        {
            //increment the number that increments the triangle number
            triAccum++;

            System.out.println("Tri number = "+triNum);

            primeCount = makeFactorArray(triNum);

            numOfDivisors = deriveDivisors(primeCount);

            System.out.println("# of divisors = "+numOfDivisors+"\n");
        }
    }

    public static int[] makeFactorArray(int triangleNum)
    {
        //holds the temporary value as the triangle number under scrutiny is broken into factors
        int testTriNum;
        int count = 0;

        //Create an array for the number of each prime factor
        primeCount = new int[PRIMES_NEEDED];

        //initialize our test number
        testTriNum = triangleNum;

        do
        {
            if(testTriNum % primes[count] == 0)
            {
                testTriNum = testTriNum / primes[count];
                primeCount[count] = primeCount[count] + 1;
                count--;
            }
            count++;
        } while(testTriNum > 1);

        return primeCount;
    }

    public static int deriveDivisors(int[] factors)
    {
        int numDiv = 1;
        for(int count = 0; count < PRIMES_NEEDED; count++)
        {
            if(factors[count] > 0)
            {
                numDiv = numDiv * (primeCount[count] + 1);
            }
        }
        return numDiv;
    }

    private static int[] createPrimeArray(int size)
    {
        int[] primes = new int[size];
        int primeCount = 0;

        for(int count = 2; primeCount < size; count++)
        {
            if(isPrime(count))
            {
                primes[primeCount] = count;
                primeCount++;
            }
        }
        return primes;
    }

    private static boolean isPrime(int testNum)
    {
        for (int count = 2; count < ((int) Math.sqrt(testNum) + 1); count++)
            if (testNum % count == 0)
                return false;

        return true;
    }
}