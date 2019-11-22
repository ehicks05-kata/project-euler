package net.ehicks.euler;

/*
Starting in the top left corner of a 3x3 grid, there are 6 routes (without backtracking) to the bottom right corner.
How many routes are there through a 21x21 grid?

2x2: 2
3x3: 6
4x4: 20
*/
public class Problem015
{
    public static void main(String[] args)
    {
        final int SIZE = 21;

        //create 2-D array
        long[][] pascalTriangle = new long[SIZE][SIZE];

        //initialize first row and column to 1
        for(int n = 0; n < SIZE; n++)
        {
            pascalTriangle[0][n] = 1;
            pascalTriangle[n][0] = 1;
        }

        //assign values to the rest of the array
        for(int c = 1; c < SIZE; c++)
        {
            for(int r = 1; r < SIZE; r++)
            {
                pascalTriangle[r][c] = (pascalTriangle[r][c-1] + pascalTriangle[r-1][c]);
            }
        }

        System.out.println("Grid Size: "+SIZE+" x "+SIZE);
        System.out.println("\nPossible Routes: "+pascalTriangle[SIZE-1][SIZE-1]);
    }
}