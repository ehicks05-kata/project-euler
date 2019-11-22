package net.ehicks.euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.

37 36 35 34 33 32 31
38 17 16 15 14 13 30
39 18  5  4  3 12 29
40 19  6  1  2 11 28
41 20  7  8  9 10 27
42 21 22 23 24 25 26
43 44 45 46 47 48 49

It is interesting to note that the odd squares lie along the bottom right diagonal,
but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.

If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed.
If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?
 */
public class Problem058
{
    public static void main(String[] args) throws Exception
    {
        long startTime = System.currentTimeMillis();

        int sideLength = 0;
        int spiralSize = 30001;
        int[][] spiral = buildSpiral(spiralSize);

        // using arrays
        int primeCount = 0;
        int notPrimeCount = 0;
        for (int distanceToCenter = 0; distanceToCenter < spiralSize / 2 + 1; distanceToCenter++)
        {
            int[] result = testSpiral(distanceToCenter, spiral);
            primeCount += result[0];
            notPrimeCount += result[1];

            int sum = primeCount + notPrimeCount;
            double percentPrime = (double) primeCount / (double) sum;

            int size = distanceToCenter * 2 + 1;
            System.out.println("size: " + size + "  " + percentPrime);
            if (percentPrime < 0.1 && distanceToCenter > 2)
            {
                sideLength = size;
                break;
            }
        }

        System.out.println("what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%: " + sideLength);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static int[] testSpiral(int distanceFromCenter, int[][] spiral)
    {
        int prime = 0;
        int notPrime = 0;

        int center = spiral[0].length / 2;

        if (distanceFromCenter == 0)
        {
            BigInteger bigOne = new BigInteger(String.valueOf(spiral[center][center]));
            if (bigOne.isProbablePrime(8))
                prime++;
            else
                notPrime++;

            return new int[] {prime, notPrime};
        }

        int upLeft    = spiral[center - distanceFromCenter][center - distanceFromCenter];
        int upRight   = spiral[center - distanceFromCenter][center + distanceFromCenter];
        int downLeft  = spiral[center + distanceFromCenter][center - distanceFromCenter];
        int downRight = spiral[center + distanceFromCenter][center + distanceFromCenter];
        List<Integer> cellsToCheck = new ArrayList<>(Arrays.asList(upLeft, upRight, downLeft, downRight));

        for (Integer cellToCheck : cellsToCheck)
        {
            BigInteger bigOne = new BigInteger(String.valueOf(cellToCheck));
            if (bigOne.isProbablePrime(8))
                prime++;
            else
                notPrime++;
        }

        return new int[] {prime, notPrime};
    }

    private static int[][] buildSpiral(int size)
    {
        int[][] spiral = new int[size][size];
        int rowIndex = size / 2;
        int colIndex = size / 2;
        String direction = "right";

        spiral[rowIndex][colIndex] = 1;
        colIndex++;

        int nextNumber = 2;
        while (true)
        {
            spiral[rowIndex][colIndex] = nextNumber++;

            if (direction.equals("right") && spiral[rowIndex-1][colIndex] == 0)
                direction = "up";
            if (direction.equals("up") && spiral[rowIndex][colIndex-1] == 0)
                direction = "left";
            if (direction.equals("left") && spiral[rowIndex+1][colIndex] == 0)
                direction = "down";
            if (direction.equals("down") && spiral[rowIndex][colIndex+1] == 0)
                direction = "right";

            if (direction.equals("right")) colIndex++;
            if (direction.equals("up")) rowIndex--;
            if (direction.equals("left")) colIndex--;
            if (direction.equals("down")) rowIndex++;

            if (colIndex > size - 1) break;
        }
        return spiral;
    }
}