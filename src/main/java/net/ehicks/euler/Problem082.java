package net.ehicks.euler;/*
NOTE: This problem is a more challenging version of Problem 81.

The minimal path sum in the 5 by 5 matrix below,
by starting in any cell in the left column and finishing in any cell in the right column,
and only moving up, down, and right, is indicated in red and bold; the sum is equal to 994.

Find the minimal path sum, in matrix.txt (right click and "Save Link/Target As..."),
a 31K text file containing a 80 by 80 matrix, from the left column to the right column.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Problem082
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
        // We will run the simulation once for every cell in the first column
        int overallMinimalSum = Integer.MAX_VALUE;
        for (int startFromRow = 0; startFromRow < matrix.length; startFromRow++)
        {
            int minimalSum = findBestPathFromCell(matrix, startFromRow, 0);
            if (minimalSum < overallMinimalSum) overallMinimalSum = minimalSum;
        }

        return overallMinimalSum;
    }

    private static int findBestPathFromCell(int[][] matrix, int startRow, int startCol)
    {
        int[][] sumMatrix = new int[matrix.length][matrix[0].length];

        sumMatrix[startRow][startCol] = matrix[startRow][startCol];

        // Populate first column of sumMatrix
        // get blank neighbors (up or down)
        List<Point> blankNeighbors = getBlankNeighbors(sumMatrix, startRow, startCol);
        while (!isColumnFull(sumMatrix, 0))
        {
            // fill in their sumMatrix value
            List<Point> newBlankNeighbors = new ArrayList<>();
            for (Point blankNeighbor : blankNeighbors)
            {
                sumMatrix[blankNeighbor.row][blankNeighbor.col] = matrix[blankNeighbor.row][blankNeighbor.col] + sumMatrix[blankNeighbor.callingRow][blankNeighbor.callingCol];
                newBlankNeighbors.addAll(getBlankNeighbors(sumMatrix, blankNeighbor.row, blankNeighbor.col));
            }
            blankNeighbors = newBlankNeighbors;
        }

        // process a column at a time
        for (int processingCol = 1; processingCol < matrix[0].length; processingCol++)
        {
            // go from first row to last row, for each one, find the cell in the previous column that forms the smallest path.
            for (int processingRow = 0; processingRow < matrix[0].length; processingRow++)
            {
                int minimalSubPath = Integer.MAX_VALUE;
                for (int subPathStartingRow = 0; subPathStartingRow < matrix[0].length; subPathStartingRow++)
                {
                    int tempRow = subPathStartingRow;
                    int subPath = sumMatrix[tempRow][processingCol - 1];
                    subPath += matrix[tempRow][processingCol];

                    while (tempRow != processingRow)
                    {
                        if (tempRow > processingRow)
                            subPath += matrix[--tempRow][processingCol];
                        if (tempRow < processingRow)
                            subPath += matrix[++tempRow][processingCol];
                    }

                    if (subPath < minimalSubPath) minimalSubPath = subPath;
                }

                sumMatrix[processingRow][processingCol] = minimalSubPath;
            }
        }

        int minimalPath = Integer.MAX_VALUE;
        // check entire last column for smallest value
        for (int row = 0; row < sumMatrix.length; row++)
        {
            int path = sumMatrix[row][sumMatrix[0].length - 1];
            if (path < minimalPath) minimalPath = path;
        }

        return minimalPath;
    }

    private static boolean isColumnFull(int[][] matrix, int testCol)
    {
        for (int row = 0; row < matrix.length; row++)
            if (matrix[row][testCol] == 0)
                return false;
        return true;
    }

    private static List<Point> getBlankNeighbors(int[][] matrix, int row, int col)
    {
        List<Point> blankNeighbors = new ArrayList<>();
        if (row > 0)
            if (matrix[row - 1][col] == 0) blankNeighbors.add(new Point(row - 1, col, row, col));
        if (row < matrix.length - 1)
            if (matrix[row + 1][col] == 0) blankNeighbors.add(new Point(row + 1, col, row, col));

        return blankNeighbors;
    }

    private static void printMatrix(int[][] matrix)
    {
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix[0].length; col++)
                System.out.print(matrix[row][col] + "," + (col == matrix[0].length - 1 ? "\r\n" : ""));
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

    static class Point
    {
        int row;
        int col;

        int callingRow;
        int callingCol;

        public Point(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int callingRow, int callingCol)
        {
            this.row = row;
            this.col = col;
            this.callingRow = callingRow;
            this.callingCol = callingCol;
        }
    }
}
