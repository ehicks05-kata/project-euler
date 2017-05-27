package net.ehicks.euler;/*
Euler's Totient function, φ(n) [sometimes called the phi function],
is used to determine the number of numbers less than n which are relatively prime to n.
For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.

n	Relatively Prime	φ(n)	n/φ(n)
2	1					1		2
3	1,2					2		1.5
4	1,3					2		2
5	1,2,3,4				4		1.25
6	1,5					2		3
7	1,2,3,4,5,6			6		1.1666...
8	1,3,5,7				4		2
9	1,2,4,5,7,8			6		1.5
10	1,3,7,9				4		2.5
It can be seen that n=6 produces a maximum n/φ(n) for n ≤ 10.

Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum.
 */

public class Problem69
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();

        int answer = Integer.MAX_VALUE;
        double maxNOverPhi = 0;

        for (int i = 2; i < 1_000_000; i++)
        {
            // At the end of the day all I had to do was multiply primes in order (2 * 3 * 5 * ...) until the next prime would take you over 1,000,000
            if (i % 2 != 0 || i % 3 != 0 || i % 5 != 0 || i % 7 != 0 || i % 11 != 0 || i % 13 != 0)
                continue;

            int phi = getPhi(i, maxNOverPhi);
            double nOverPhi = (double) i / phi;
            if (nOverPhi > maxNOverPhi)
            {
                answer = i;
                maxNOverPhi = nOverPhi;
                System.out.println(i + ", nOverPhi: " + maxNOverPhi);
            }
        }

        System.out.println("Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum: " + answer + " (" + maxNOverPhi + ")");
        timer.printDuration();
    }

    private static int getPhi(int n, double maxNOverPhi)
    {
        int phi = 0;
        for (int i = 1; i < n; i++)
        {
            if (EulerUtil.getGCD(i, n) == 1)
            {
                phi++;
                double nOverPhi = (double) n / phi;
                if (nOverPhi <= maxNOverPhi)
                    return Integer.MAX_VALUE;
            }
        }
        return phi;
    }
}
