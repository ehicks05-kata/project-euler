package net.ehicks.euler;//By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

public class Problem02
{
    public static void main(String[] args)
    {
        int term1 = 1;
        int term2 = 2;
        int temp;

        int total = 0;

        while (term2 <= 4000000)
        {
            if (term2 % 2 == 0)
            {
                total += term2;
            }
            temp = term2;
            term2 += term1;
            term1 = temp;
        }

        System.out.println("The sum is : " + total);
    }
}