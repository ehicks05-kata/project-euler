package net.ehicks.euler;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

/*
The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order the result will always be prime.

For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.

Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
*/
public class Problem060
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
            Callable<List<Set<Integer>>> worker = new Problem060Worker(i, primes);
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

    static class Problem060Worker implements Callable<List<Set<Integer>>>
    {
        private final int start;

        public Problem060Worker(int start, List<Integer> primes)
        {
            this.start = start;
        }

        public List<Set<Integer>> call()
        {
            List<Integer> primes = Problem060.primes;
            List<Set<Integer>> primeSets = new ArrayList<>();

            int size = primes.size();
            for (int i = start; i < start+1; i++)
            {
                for (int j = start; j < size; j++)
                {
                    if (i >= j) continue;
                    if (!isConcatenatable(new HashSet<>(Arrays.asList(primes.get(i), primes.get(j))))) continue;

                    for (int k = start; k < size; k++)
                    {
                        if (i >= k || j >= k) continue;
                        if (!isConcatenatable(new HashSet<>(Arrays.asList(primes.get(i), primes.get(j), primes.get(k))))) continue;

                        for (int l = start; l < size; l++)
                        {
                            if (i >= l || j >= l || k >= l) continue;
                            if (!isConcatenatable(new HashSet<>(Arrays.asList(primes.get(i), primes.get(j), primes.get(k), primes.get(l))))) continue;

                            for (int m = start; m < size; m++)
                            {
                                if (i >= m || j >= m || k >= m || l >= m) continue;

                                Set<Integer> primeSet = new HashSet<>(Arrays.asList(primes.get(i), primes.get(j), primes.get(k), primes.get(l), primes.get(m)));
                                if (isConcatenatable(primeSet))
                                {
                                    System.out.println("got one:" + primes.get(i) + "," + primes.get(j) + "," + primes.get(k) + "," + primes.get(l) + "," + primes.get(m));
                                    primeSets.add(primeSet);
                                }
                            }
                        }
                    }
                }
            }

            return primeSets;
        }

        private static boolean isConcatenatable(Set<Integer> primes)
        {
            for (Integer prime1 : primes)
            {
                for (Integer prime2 : primes)
                {
                    if (prime1.equals(prime2)) continue;
                    String p1p2 = prime1.toString() + prime2.toString();
                    String p2p1 = prime2.toString() + prime1.toString();
                    if (!isPrimeQuick(Integer.parseInt(p1p2)) || !isPrimeQuick(Integer.parseInt(p2p1))) return false;
                }
            }
            return true;
        }

        private static boolean isPrimeQuick(int testNum)
        {
            return new BigInteger(String.valueOf(testNum)).isProbablePrime(8);
        }
    }

}