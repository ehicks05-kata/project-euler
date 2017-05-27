package net.ehicks.euler;//Add all the natural numbers below one thousand that are multiples of 3 or 5.

public class Problem01
{
    public static void main(String[] args)
    {
        int total = 0;

        for (int count = 1; count < 1000; count++)
        {
            if (count % 3 == 0 || count % 5 == 0)
            {
                total += count;
            }
        }

        System.out.println("The sum is : " + total);
    }
}