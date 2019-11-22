package net.ehicks.euler;

//Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
public class Problem030
{
    private static final int depth = 200000;
    private static int[] fifthPowNumbers = new int[100];
    private static int numQualified = 0;

    public static void main(String[] args)
    {
        int finalSum = 0;
        for(int i = 2; i < depth; i++) //loop through to check every number
        {
            checkProgress(i);
            checkNumber(i);
        }
        for(int j = 0; j < numQualified; j++)
        {
            System.out.println(fifthPowNumbers[j]);
            finalSum = finalSum + fifthPowNumbers[j];
        }
        System.out.println(finalSum);
    }
    public static void checkProgress(int i)
    {
        if(i % (depth/10) == 0)
        {
            System.out.println(i/(depth/100) + "%\nNumbers Processed: "+i);
        }
    }
    public static void checkNumber(int i)
    {
        int tempNum;
        String temp = String.valueOf(i); //convert to string
        int singleSum = 0;

        for(int j = 0; j < temp.length(); j++)
        {
            String tempString = temp.substring(j, j + 1);
            tempNum = Integer.parseInt(tempString);
            tempNum = (int)Math.pow(tempNum, 5);

            singleSum = singleSum + tempNum;
        }
        if(singleSum == i)
        {
            fifthPowNumbers[numQualified] = singleSum;
            numQualified++;
        }
    }
}