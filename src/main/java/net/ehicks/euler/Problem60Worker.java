package net.ehicks.euler;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;

public class Problem60Worker implements Callable<List<Set<Integer>>>
{
    private final int start;

    public Problem60Worker(int start, List<Integer> primes)
    {
        this.start = start;
    }

    public List<Set<Integer>> call()
    {
        List<Integer> primes = Problem60.primes;
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
