package net.ehicks.euler;/*
In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,
by only moving to the right and down, is indicated in bold red and is equal to 2427.

Find the minimal path sum, in matrix.txt (right click and "Save Link/Target As..."),
a 31K text file containing a 80 by 80 matrix, from the top left to the bottom right by only moving right and down.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Problem81
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();

        int[][] matrix = getMatrix();

        int minimalSum = getMinimalPathSum(matrix);


        System.out.println("Find the minimal path sum: " + minimalSum + ", average cell value: " + minimalSum / 158);
        timer.printDuration();
    }

    private static int getMinimalPathSum(int[][] matrix)
    {
        int[][] sumMatrix = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[0].length; col++)
            {
                if (row == 0 && col == 0)
                {
                    sumMatrix[row][col] = matrix[row][col];
                    continue;
                }
                if (row == 0)
                {
                    sumMatrix[row][col] = matrix[row][col] + sumMatrix[row][col - 1];
                    continue;
                }
                if (col == 0)
                {
                    sumMatrix[row][col] = matrix[row][col] + sumMatrix[row - 1][col];
                    continue;
                }

                int smallestNeighborValue = sumMatrix[row - 1][col] <= sumMatrix[row][col - 1] ? sumMatrix[row - 1][col] : sumMatrix[row][col - 1];

                sumMatrix[row][col] = matrix[row][col] + smallestNeighborValue;
            }
        }

        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[0].length; col++)
            {
                System.out.print(sumMatrix[row][col] + ",");
            }
            System.out.println();
        }

        return sumMatrix[sumMatrix.length - 1][sumMatrix[0].length - 1];
    }

    private static int[][] getMatrix() throws IOException
    {
        int[][] matrix = new int[80][80];
        List<String> allLines = Files.readAllLines(Paths.get("web/euler/Problem81Matrix.txt"));
        int row = 0;
        for (String line : allLines)
        {
            String[] lineParts = line.split(",");
            int column = 0;
            for (String linePart : lineParts)
            {
                matrix[row][column] = Integer.valueOf(linePart);
                column++;
            }
            row++;
        }
        return matrix;
    }
}
