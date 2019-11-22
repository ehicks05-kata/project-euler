package net.ehicks.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem068
{
    // nodes in the ring are numbered going left to right.
    private static final List<List<Integer>> CHAINS = new ArrayList<>(
            Arrays.asList(
                    Arrays.asList(0, 1, 5)
                    , Arrays.asList(2, 5, 7)
                    , Arrays.asList(8, 7, 6)
                    , Arrays.asList(9, 6, 3)
                    , Arrays.asList(4, 3, 1)
            )
    );

    private static BigInteger maximumString = BigInteger.ZERO;
    private static List<Integer> maximumStringRing = new ArrayList<>();

    public static void main(String[] args)
    {
        while (true) solve();
    }

    private static void solve()
    {
        long start = System.currentTimeMillis();
        maximumString = BigInteger.ZERO;
        maximumStringRing = new ArrayList<>();

        List<Integer> ring = new ArrayList<>(
                Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

        List<Integer> availableNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        buildRing(ring, availableNumbers, 0);

        System.out.println("answer: " + maximumString + "\t\tring: " + maximumStringRing + "\t\ttime: " + (System.currentTimeMillis() - start));
    }

    public static void buildRing(List<Integer> ring, List<Integer> availableNumbers, int index)
    {
        for (Integer availableNumber : availableNumbers)
        {
            ring.set(index, availableNumber);
            List<Integer> updatedAvailableNumbers = new ArrayList<>(availableNumbers);
            updatedAvailableNumbers.remove(availableNumber);

            if (index == 9)
            {
                BigInteger result = testRing(ring);
                if (result.toString().length() == 16 && result.compareTo(maximumString) > 0)
                {
                    maximumString = result;
                    maximumStringRing = ring;
                }
            }
            else
            {
                buildRing(ring, updatedAvailableNumbers, index + 1);
            }
        }
    }

    private static BigInteger testRing(List<Integer> ring)
    {
        if (!firstChainHasSmallestExternalNode(ring))
            return BigInteger.ZERO;

        if (!areAllChainsEqualValue(ring))
            return BigInteger.ZERO;

        StringBuilder stringBuilder = new StringBuilder();
        for (List<Integer> chain : CHAINS)
            for (Integer nodeIndex : chain)
                stringBuilder.append(ring.get(nodeIndex).toString());

        return new BigInteger(stringBuilder.toString());
    }

    private static boolean areAllChainsEqualValue(List<Integer> ring)
    {
        int chainSum = 0;
        for (List<Integer> chain : CHAINS)
        {
            int sum = chain.stream().mapToInt(ring::get).sum();
            if (chainSum == 0)
                chainSum = sum;
            if (sum != chainSum)
                return false;
        }
        return true;
    }

    private static boolean firstChainHasSmallestExternalNode(List<Integer> ring)
    {
        int smallestNodeIndex = 0;
        int smallestNodeValue = Integer.MAX_VALUE;
        for (List<Integer> chain : CHAINS)
        {
            int nodeIndex = chain.get(0);
            if (ring.get(nodeIndex) < smallestNodeValue)
            {
                smallestNodeIndex = nodeIndex;
                smallestNodeValue = ring.get(nodeIndex);
            }
        }

        return CHAINS.get(0).get(0) == smallestNodeIndex;
    }
}
