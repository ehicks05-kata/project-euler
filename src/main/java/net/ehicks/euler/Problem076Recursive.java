package net.ehicks.euler;
/*
It is possible to write five as a sum in exactly six different ways:

4 + 1
3 + 2
3 + 1 + 1
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1

How many different ways can one hundred be written
as a sum of at least two positive integers?
 */

import java.math.BigInteger;

public class Problem076Recursive
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        Timer innerTimer = new Timer();

        for (int i = 2; i < 10001; i++)
        {
            BigInteger combos = test(i);
            String message = String.format("%4d: %16d combos %s", i, combos, innerTimer.getDuration(true));
            System.out.println(message);
        }

        timer.printDuration();
    }

    private static BigInteger test(int n)
    {
        long i, j;
        BigInteger[] way = new BigInteger[n + 1];

        way[0] = BigInteger.ONE;
        for (i = n - 1; i >= 1; i--)
            for (j = i; j <= n; j++)
            {
                BigInteger first = way[(int) j] == null ? BigInteger.ZERO : way[(int) j];
                BigInteger second = way[(int) (j - i)] == null ? BigInteger.ZERO : way[(int) (j - i)];
                way[(int) j] = first.add(second);
            }

        return way[n];
    }

    private static long findCombos(int limit, int number)
    {
        long totalCombos = 0;

        if (number == 1)
            return 0;

        for (int second = 1; second < number; second++)
        {
            int first = number - second;

            if (number <= limit && first >= second && first <= limit && second <= limit)
                totalCombos++;

            totalCombos += findCombos(Math.min(first, limit), second);
        }
        
        return totalCombos;
    }
}
