package net.ehicks.euler;//Find the first ten digits of the sum of one-hundred 50-digit numbers.
import java.io.*;
import java.util.Scanner;

public class Problem013
{
    public static void main(String[] args) throws IOException
    {
        double temp;
        double accumulator = 0;
        String input;

        //open file
        File file = new File("src/data/Problem013.txt");
        Scanner inputFile = new Scanner(file);


        while(inputFile.hasNext())
        {
            //read a line from the file
            input = inputFile.nextLine();
            temp = Double.parseDouble(input);

            System.out.println(temp);

            //add to the accumulator
            accumulator += temp;
        }


        //print results
        System.out.println("\n"+accumulator);

        //close file
        inputFile.close();

    }
}