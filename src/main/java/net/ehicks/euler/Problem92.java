package net.ehicks.euler;

/*
A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.

For example,

44 → 32 → 13 → 10 → 1 → 1
85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89

Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.

How many starting numbers below ten million will arrive at 89?
 */
public class Problem92
{
    public static void main(String[] args)
    {
        Timer t = new Timer();
        int arriveAt1 = 0;
        int arriveAt89 = 0;
        for (int i = 1; i < 10_000_000; i++)
        {
            int numberToProcess = i;
            while (numberToProcess != 1 && numberToProcess != 89)
            {
                numberToProcess = processNumber(numberToProcess);
            }
            if (numberToProcess == 1) arriveAt1++;
            if (numberToProcess == 89) arriveAt89++;
        }

        System.out.println("Arrived at 1: " + arriveAt1);
        System.out.println("Arrived at 89: " + arriveAt89);
        t.printDuration();
    }

    private static int processNumber(int input)
    {
        int[] digits = new int[String.valueOf(input).length()];
        int numberToSplit = input;
        int i = digits.length - 1;
        while (numberToSplit > 0)
        {
            digits[i] = numberToSplit % 10;
            numberToSplit /= 10;
            i--;
        }

        int sum = 0;
        for (int digit : digits)
            sum += (digit * digit);

        return sum;
    }
}