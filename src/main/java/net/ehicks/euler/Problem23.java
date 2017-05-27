package net.ehicks.euler;

/*
A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24.
By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers.
However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot
be expressed as the sum of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
*/
public class Problem23
{
    public static void main(String[] args)
    {
        final int N = 28123;
        int abundantNumbers[] = new int[N];

        int count = 0;
        int sum;
        int temp;

        //generate array of abundant numbers
        for(int i = 1; i < N; i++)
        {
            sum = 0;

            for(int j = 1; j < i; j++)
            {
                if(i % j == 0)
                {
                    sum += j;
                }
            }

            if(sum > i)
            {
                abundantNumbers[count] = i;
                count++;
            }
        }

        int possibleNumbers[] = new int[N];

        //fill array with 1-28123
        for(int i = 0; i < 28123; i++)
        {
            possibleNumbers[i] = i;
        }

        //step through each possible sum of abundant numbers.
        //this represents a number that can be expressed as the sum of two abundant numbers.
        //therefore it is NOT a number we are interested in.
        //take this sum and find its place in the possibleNum array.
        //set that corresponding value to zero.
        for(int i = 0; i < count; i++)
        {
            for(int j = 0; j < count; j++)
            {
                temp = (abundantNumbers[i] + abundantNumbers[j]);

                if(temp < 28123)
                {
                    possibleNumbers[temp] = 0;
                }
            }
        }

        sum = 0;

        for(int i = 0; i < N; i++)
        {
            sum += possibleNumbers[i];
            System.out.println(possibleNumbers[i]);
        }
        System.out.println(sum);
    }
}