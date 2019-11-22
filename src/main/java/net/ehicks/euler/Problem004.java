package net.ehicks.euler;

//Find the largest palindrome made from the product of two 3-digit numbers.
public class Problem004
{
    public static void main(String[] args)
    {
        System.out.println(findHighestPal());
    }

    public static int findHighestPal()
    {
        int highestPal = 0, testNum;
        boolean isPalindrome; //declare boolean

        for(int outerCount = 100; outerCount < 1000; outerCount++)
        {
            for(int innerCount = 100; innerCount < 1000; innerCount++)
            {
                testNum = (outerCount * innerCount); //calculate test number
                isPalindrome = isPal(testNum);

                if(isPalindrome)
                {
                    if(testNum > highestPal)
                    {
                        highestPal = testNum;
                    }
                }
            }
        }
        return highestPal;
    }

    public static boolean isPal(int testNum)
    {
        String testStr, revStr;
        boolean isPalindrome = false; //initialize to false

        testStr = Integer.toString(testNum); //convert int to string
        StringBuilder str = new StringBuilder(testStr); //create and initialize stringbuilder

        str = str.reverse(); //use str's reverse method
        revStr = str.toString(); //use str's tostring method

        if(testStr.equals(revStr)) //see if strings are equal
            isPalindrome = true;

        return isPalindrome;
    }
}