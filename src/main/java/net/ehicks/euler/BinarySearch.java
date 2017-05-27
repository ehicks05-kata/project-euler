package net.ehicks.euler;

import java.util.Random;

public class BinarySearch
{
    public static void main(String[] args)
    {
        int dataSize = 10000000;
        int[] testData = new int[dataSize];
        int increment = 1;
        int j = 0;
        int requestNumber;
        Random randGen = new Random();
        do
        {
            requestNumber = randGen.nextInt(dataSize * increment);
        } while (requestNumber % increment != 0);
        System.out.println("Number to find: " + requestNumber);
        System.out.println("Each value in the array is incremented by: " + increment);
        System.out.println("Cell the number should be found in: " + (requestNumber / increment));
        System.out.println("Generating Test Data: ");
        for(int i = 0; i < dataSize; i++)
        {
            testData[i] = j;
            //System.out.print(j+"@"+i+"  ");
            j+=increment;
        }

        int answer = binarySearch(testData, requestNumber);
        System.out.println("Answer Found: "+answer);
    }

    public static int binarySearch(int[] input, int request)
    {
        int answer = 0;
        boolean answerFound = false;
        int cellsRemaining = input.length;
        int cellToCheck;
        int begin = 0;
        int end = input.length;
        System.out.println("From: "+begin+"  To "+end+"  Cells Remaining: "+cellsRemaining);

        while(!answerFound && cellsRemaining > 0)
        {
            cellToCheck = (end+begin)/2;
            Math.floor(cellToCheck);
            System.out.println("Checking: "+cellToCheck);
            if(input[cellToCheck] > request)
            {
                end = cellToCheck;
                cellsRemaining = cellToCheck - begin;
                System.out.println("From: "+begin+"  To: "+end+"  Cells Remaining: "+cellsRemaining);
            }
            else if(input[cellToCheck] < request)
            {
                begin = cellToCheck;
                cellsRemaining = end - cellToCheck;
                System.out.println("From: "+begin+"  To "+end+"  Cells Remaining: "+cellsRemaining);
            }
            else if(input[cellToCheck] == request)
            {
                answer = cellToCheck;
                answerFound = true;
            }

            if(cellsRemaining == 1)
            {
                cellsRemaining = 0;
            }
        }
        if(cellsRemaining == 0)
        {
            System.out.println("Sorry. Number not found.");
        }
        return answer;
    }
}