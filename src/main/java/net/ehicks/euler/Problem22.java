package net.ehicks.euler;/*
Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
So, COLIN would obtain a score of 938 � 53 = 49714.

What is the total of all the name scores in the file?
*/

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem22
{
    public static void main(String[] args) throws IOException
    {
        String temp = "";
        int tempNum;
        int sum;
        int total = 0;

        File inputFile = new File("Problem22Names.txt"); //input file
        Scanner inputFileNames = new Scanner(inputFile); //scanner to read the input file

        //read entire input file and add to String temp
        for(; inputFileNames.hasNext();)
        {
            temp += inputFileNames.nextLine();
        }

        String names[] = temp.split("\",\"");
        int nameScores[] = new int[names.length];

        names[0] = "MARY";

        Arrays.sort(names);

        names[146] = "ALONSO";


        for(int i = 0; i < names.length; i++)
        {
            sum = 0;
            char letters[] = new char[names[i].length()];

            for(int j = 0; j < names[i].length(); j++)
            {
                letters[j] = names[i].charAt(j);
            }

            for (char aLetter : letters)
            {
                tempNum = (int)aLetter - 64;
                sum += tempNum;
            }
            nameScores[i] = sum;
        }

        for(int i = 0; i < 5163; i++)
        {
            total += (i+1) * nameScores[i];
            System.out.println(names[i]);
        }

        System.out.println(total);

        inputFileNames.close();
    }
}