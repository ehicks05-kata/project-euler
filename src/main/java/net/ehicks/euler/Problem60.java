package net.ehicks.euler;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

/*
The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the result will always be prime.

For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.

Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
*/
public class Problem60
{
    public static final List<Integer> primes = getPrimes(10000);

    public static void main(String[] args) throws Exception
    {
        long startTime = System.currentTimeMillis();

        int lowestSum = Integer.MAX_VALUE;

        System.out.println("testing prime sets...");
        List<Set<Integer>> primeSets = getQualifyingPrimeSets();

        System.out.println("# of qualifying prime sets: " + primeSets.size());
        System.out.println("determining which of the qualifying prime sets has the lowest sum...");
        for (Set<Integer> primeSet : primeSets)
        {
            if (getPrimeSetSum(primeSet) < lowestSum)
            {
                lowestSum = getPrimeSetSum(primeSet);
                for (Integer prime : primeSet)
                    System.out.print(prime + ",");
                System.out.println();
            }
        }

        System.out.println("Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime: " + lowestSum);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static int getPrimeSetSum(Set<Integer> primeSet)
    {
        int sum = 0;
        for (Integer prime : primeSet)
            sum += prime;
        return sum;
    }

    private static List<Set<Integer>> getQualifyingPrimeSets() throws InterruptedException, ExecutionException
    {
        List<Set<Integer>> primeSets = new ArrayList<>();

        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        List<Future<List<Set<Integer>>>> list = new ArrayList<>();
        for (int i = 0; i < primes.size(); i++)
        {
            Callable<List<Set<Integer>>> worker = new Problem60Worker(i, primes);
            Future<List<Set<Integer>>> submit = executor.submit(worker);
            list.add(submit);
        }

        // now retrieve the result
        for (Future<List<Set<Integer>>> future : list)
        {
            primeSets.addAll(future.get());
        }
        executor.shutdown();

        return primeSets;
    }

    private static List<Integer> getPrimes(int maxTestNum)
    {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        for (int testNum = 3; testNum < maxTestNum; testNum++)
            if (isPrime(testNum))
                primes.add(testNum);

        return primes;
    }

    private static boolean isPrime(int testNum)
    {
        if (testNum == 2) return true;
        if (testNum == 3) return true;
        if (testNum == 4) return false;

        for (int divisor = 2; divisor < (testNum / 2) + 1; divisor++)
            if (testNum % divisor == 0)
                return false;

        return true;
    }
}