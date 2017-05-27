package net.ehicks.euler;/*
By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

3
7 4
2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'), a 15K text file containing a triangle with one-hundred rows.

NOTE: This is a much more difficult version of Problem 18.
It is not possible to try every route to solve this problem, as there are 299 altogether!
If you could check one trillion (1012) routes every second it would take over twenty billion years to check them all. There is an efficient algorithm to solve it. ;o)*/

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Problem67
{
    public static void main(String[] args) throws IOException
    {
        String path = "src/data/Problem67Triangle.txt";

        int levels = getLevels(path);
        int elements = getElements(levels);
        long routes = getRoutes(levels);

        int[][] tree2D = readTreeFile(path, levels);
        //print results
        System.out.println("Triangle PathFinder\n\nLevels: " + levels + "\nElements: " + elements + "\nRoutes: " + routes + "\n");

        printArray(tree2D, levels);
        printBestPath(tree2D, levels);
    }

    //count how many levels are in the triangle
    public static int getLevels(String path) throws IOException
    {
        int levels = 0;
        Scanner lengthScanner = new Scanner(new File(path));
        //count how many levels are in the triangle
        while (lengthScanner.hasNext())
        {
            lengthScanner.nextLine();
            levels++;
        }

        lengthScanner.close();
        return levels;
    }

    //tell me how many elements are in the triangle
    public static int getElements(int levels)
    {
        int elements = 0;

        for (int n = 0; n < levels; n++)
            elements += (n + 1);

        return elements;
    }

    //tell me how many routes are in the triangle
    public static int getRoutes(int levels)
    {
        int routes = (int) Math.pow(2, levels);
        routes /= 2; //remember, this triangle is only half of a rectangle

        return routes;
    }

    //read tree file and create 2d tree array
    public static int[][] readTreeFile(String path, int levels) throws IOException
    {
        int[][] tree2D = new int[levels][levels];

        //read tree file contents
        Scanner inputFile = new Scanner(new File(path));
        int lineNumber = 0;
        while (inputFile.hasNext())
        {
            String line = inputFile.nextLine();

            String[] tokens = line.split(" ");
            for (int i = 0; i < tokens.length; i++)
                tree2D[lineNumber][i] = Integer.parseInt(tokens[i]);

            lineNumber++;
        }
        //close file
        inputFile.close();

        return tree2D;
    }

    //print a view of the triangle
    public static void printArray(int[][] tree2D, int levels)
    {
        int count = 1;
        int thingsPrintedThisLevel;

        DecimalFormat f = new DecimalFormat("0000");

        for (int j = 0; j < levels; j++)
        {
            thingsPrintedThisLevel = 0;
            System.out.print("");

            for (int k = 0; k < levels; k++)
            {
                boolean thisElementHasPrinted = false;

                while (thingsPrintedThisLevel < count && !thisElementHasPrinted)
                {
                    System.out.print(f.format(tree2D[j][k]) + " ");
                    thingsPrintedThisLevel++;
                    thisElementHasPrinted = true;
                }
            }
            System.out.println();
            count++;
        }
        System.out.println();
    }

    public static void printBestPath(int[][] tree2D, int levels)
    {
        //array to mark the best path
        int[][] path = new int[levels][levels];

        for (int row = 99; row < 100; row++)
            System.arraycopy(tree2D, 0, path, 0, tree2D.length);

        for (int row = 99; row > 0; row--)
        {
            for (int column = 0; column < levels - 1; column++)
            {
                if (tree2D[row][column] > tree2D[row][column + 1])
                    path[row - 1][column] = tree2D[row - 1][column] + path[row][column];
                else
                    path[row - 1][column] = tree2D[row - 1][column] + path[row][column + 1];
            }
        }

        printArray(path, levels);

        int sum = tree2D[0][0];
        int column = 0;

        for (int row = 1; row < levels; row++)
        {
            if (path[row][column] > path[row][column + 1])
            {
                sum += tree2D[row][column];
                System.out.print(tree2D[row][column] + ", ");
            }
            else
            {
                sum += tree2D[row][column + 1];
                System.out.print(tree2D[row][column + 1] + ", ");
                column++;
            }
        }

        System.out.println("\nSum: " + sum);
    }
}
