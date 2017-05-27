package net.ehicks.euler;

/*  In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

    1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
    It is possible to make £2 in the following way:

    1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
    How many different ways can £2 be made using any number of coins?

    How many of each coin are possible in a solution:
    coin:   # used      combinations
    2L:     0-1         2
    1L:     0-2         3
    50p:    0-4         5
    20p:    0-10        11
    10p:    0-20        21
    5p:     0-40        41
    2p:     0-100       101
    1p:     0-200       201

    brute force combinations:
    2,560,000,000,000,000,000

    improved brute force combinations:
    5,768,123,130 
    
    dynamic solution lifted from http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
    */
public class Problem31
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();

        int targetValue = 200;

        int[] coins = new int[]{1, 2, 5, 10, 20, 50, 100, 200};
        int solutions =  count(coins, coins.length, targetValue);

        System.out.println("Number of different combinations to make " + targetValue + "p: " + solutions);
        timer.printDuration();
    }

    private static int count(int[] coins, int m, int n)
    {
        int i;
        int j;
        int x;
        int y;

        // We need n+1 rows as the table is constructed in bottom up manner using
        // the base case 0 value case (n = 0)
        int[][] table = new int[n + 1][m];

        // Fill the entries for 0 value case (n = 0)
        for (i = 0; i < m; i++)
            table[0][i] = 1;

        // Fill rest of the table entries in bottom up manner
        for (i = 1; i < n + 1; i++)
        {
            for (j = 0; j < m; j++)
            {
                // Count of solutions including S[j]
                x = (i - coins[j] >= 0) ? table[i - coins[j]][j] : 0;

                // Count of solutions excluding S[j]
                y = (j >= 1) ? table[i][j - 1] : 0;

                // total count
                table[i][j] = x + y;
            }
        }
        return table[n][m - 1];
    }
}