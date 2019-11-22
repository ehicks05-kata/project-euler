package net.ehicks.euler;//Find the maximum sum travelling from the top of the triangle to the base.
import java.io.*;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Problem018
{
    public static void main(String[] args) throws IOException
    {
        int count = 0;
        int levels = getLevels(), elements = getElements(levels), routes = getRoutes(levels);

        int[][] tree2D = readTreeFile(levels);
        //print results
        System.out.println("Triangle PathFinder\n\n"+"Levels: "+levels+"\n"+
                "Elements: "+elements+"\n"+"Routes: "+routes+"\n");

        printArray(tree2D, levels, elements);
        printBestPath(tree2D, levels, routes);
    }
    //count how many levels are in the triangle
    public static int getLevels() throws IOException
    {
        int levels = 0;
        //open file
        File file = new File("src/data/Problem18.txt");
        Scanner lengthScanner = new Scanner(file);
        //count how many levels are in the triangle
        while(lengthScanner.hasNext())
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
        int elements= 0;

        for(int n = 0; n < levels; n++)
        {
            elements += (n + 1);
        }

        return elements;
    }
    //tell me how many routes are in the triangle
    public static int getRoutes(int levels)
    {
        int routes;

        routes = (int)Math.pow(2, levels);
        //remember, this triangle is only half of a rectangle
        routes /= 2;

        return routes;
    }
    //read tree file and create 2d tree array
    public static int[][] readTreeFile(int levels) throws IOException
    {
        int length, count, testNum, m = 0;
        String sub, input;
        //open file
        File file = new File("src/data/Problem18.txt");
        Scanner inputFile = new Scanner(file);
        //create int array for the tree
        int[][] tree2D = new int[levels][levels];
        //read tree file contents
        while(inputFile.hasNext())
        {
            //read a line from the file
            input = inputFile.nextLine();
            //prepare for substring extraction
            length = input.length();

            count = 0;
            //extract each substring
            for(int n = 0; n < length; n += 3)
            {
                sub = input.substring(n, n+2);
                testNum = Integer.parseInt(sub);

                tree2D[m][count] = testNum;

                count++;
            }
            m++;
        }
        //close file
        inputFile.close();

        return tree2D;
    }
    //print a view of the triangle
    public static void printArray(int[][] tree2D, int levels, int elements)
    {
        int count = 1;
        int thingsPrintedThisLevel;

        DecimalFormat f = new DecimalFormat("000");

        for(int j = 0; j < levels; j++)
        {
            thingsPrintedThisLevel = 0;
            System.out.print("");

            for(int k = 0; k < levels; k++)
            {
                boolean thisElementHasPrinted = false;

                while (thingsPrintedThisLevel < count && !thisElementHasPrinted)
                {
                    System.out.print(f.format(tree2D[j][k])+" ");
                    thingsPrintedThisLevel++;
                    thisElementHasPrinted = true;
                }
            }
            System.out.println();
            count++;
        }
        System.out.println();
    }
    //
    public static void printBestPath(int[][] tree2D, int levels, int routes)
    {
        //array to mark the best path
        int[][] path = new int[levels][levels];

        for(int row = 0; row < levels; row++)
        {
            for(int column = 0; column < levels; column++)
            {
                path[row][column] = 0;
            }
        }

        for(int row = 14; row < 15; row++)
        {
            System.arraycopy(tree2D, 0, path, 0, tree2D.length);
//            for(int column = 0; column < levels; column++)
//            {
//                path[row][column] = tree2D[row][column];
//            }
        }

        for(int row = 14; row > 0; row--)
        {
            for(int column = 0; column < levels-1; column++)
            {
                if(tree2D[row][column] > tree2D[row][column+1])
                {
                    path[row-1][column] = tree2D[row-1][column] + path[row][column];
                }
                else
                {
                    path[row-1][column] = tree2D[row-1][column] + path[row][column+1];
                }
            }
        }

        printArray(path, levels, 16384);

        int sum = tree2D[0][0];
        int column = 0;

        for(int row = 1; row < levels; row++)
        {
            if(path[row][column] > path[row][column+1])
            {
                sum += tree2D[row][column];
                System.out.print(tree2D[row][column]+", ");
            }
            else
            {
                sum += tree2D[row][column+1];
                System.out.print(tree2D[row][column+1]+", ");
                column++;
            }
        }

        System.out.println("\nSum: "+sum);
    }
}