package net.ehicks.euler;/*
The series, 1^(1) + 2^(2) + 3^(3) + ... + 10^(10) = 10405071317.

Find the last ten digits of the series, 1^(1) + 2^(2) + 3^(3) + ... + 1000^(1000).
*/
import java.math.BigInteger;

public class Problem48
{
    public static void main(String[] args)
    {
        BigInteger num2;
        BigInteger sum = new BigInteger("0");

        for(int i = 1; i < 1001; i++)
        {
            Integer int1 = i;
            BigInteger num1 = new BigInteger(int1.toString());

            num2 = num1.pow(i);

            sum = sum.add(num2);
        }

        String answer = sum.toString();

        int length = answer.length();

        String answer2 = answer.substring(length - 10);

        System.out.println(answer2);
    }
}