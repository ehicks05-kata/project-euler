package net.ehicks.euler;

/*
The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)
* */
public class Problem036
{
    public static void main(String[] args)
    {
        int sumOfPalindromics = 0;
        for (int i = 1; i < 1_000_000; i++)
        {
            String number = String.valueOf(i);
            if (!isPalindrome(number)) continue;

            if (isPalindrome(convertToBase2(i)))
            {
                sumOfPalindromics += i;
                System.out.println(i + ":" + convertToBase2(i));
            }
        }

        System.out.println("Sum of all numbers, less than one million, which are palindromic in base 10 and base 2: " + sumOfPalindromics);
    }

    private static boolean isPalindrome(String number)
    {
        String reversedNumber = new StringBuilder(number).reverse().toString();
        return number.equals(reversedNumber);
    }

    private static String convertToBase2(int number)
    {
        return Integer.toString(number, 2);
    }
}