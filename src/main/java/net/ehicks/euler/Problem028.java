package net.ehicks.euler;/*
Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

21 22 23 24 25
20  7  8  9 10
19  6  1  2 11
18  5  4  3 12
17 16 15 14 13

It can be verified that the sum of the numbers on the diagonals is 101.

What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
*/

public class Problem028
{
    public static void main(String[] args)
    {
        // Configuration
        int length = 1001;

        // Initialization
        int[][] spiral = new int[length][length];

        int midway = length / 2;
        int counter = 1;
        spiral[midway][midway] = counter++;

        String direction = "east";
        int row = midway;
        int column = midway + 1;

        buildSpiral(spiral, counter, direction, row, column);

        printSpiral(spiral);
        int result = addDiagonals(spiral);
        System.out.println("\r\nAnswer: " + String.valueOf(result));
    }

    private static void buildSpiral(int[][] spiral, int counter, String direction, int row, int column)
    {
        while (column >= 0 && column < spiral.length && row >= 0 && row < spiral.length)
        {
            spiral[row][column] = counter++;

            switch (direction)
            {
                case "east":
                {
                    // can we turn right?
                    if (spiral[row + 1][column] == 0)
                    {
                        direction = "south";
                        row++;
                    }
                    else
                        column++;

                    break;
                }
                case "south":
                {
                    // can we turn right?
                    if (spiral[row][column - 1] == 0)
                    {
                        direction = "west";
                        column--;
                    }
                    else
                        row++;

                    break;
                }
                case "west":
                {
                    // can we turn right?
                    if (spiral[row - 1][column] == 0)
                    {
                        direction = "north";
                        row--;
                    }
                    else
                        column--;

                    break;
                }
                case "north":
                {
                    // can we turn right?
                    if (spiral[row][column + 1] == 0)
                    {
                        direction = "east";
                        column++;
                    }
                    else
                        row--;

                    break;
                }
            }
        }
    }

    private static void printSpiral(int[][] spiral)
    {
        for (int row = 0; row < spiral.length; row++)
        {
            for (int column = 0; column < spiral.length; column++)
            {
                String value = String.valueOf(spiral[row][column]);
                String diagonalSignifier = " ";
                if (isADiagonal(row, column, spiral)) diagonalSignifier = "D";
                System.out.print(padToLength(value) + diagonalSignifier + " ");
            }
            System.out.println();
        }
    }

    private static int addDiagonals(int[][] spiral)
    {
        int total = 0;

        for (int row = 0; row < spiral.length; row++)
        {
            for (int column = 0; column < spiral.length; column++)
            {
                boolean diagonal = isADiagonal(row, column, spiral);
                if (diagonal)
                {
                    total += spiral[row][column];
                }
            }
        }

        return total;
    }

    private static boolean isADiagonal(int row, int column, int[][] spiral)
    {
        boolean answer = false;

        if (row == column) answer = true;
        if ((spiral.length - 1) - column == row) answer = true;
        return answer;
    }

    private static String padToLength(String input)
    {
        while (input.length() < 6)
        {
            input = " " + input;
        }
        return input;
    }
}
