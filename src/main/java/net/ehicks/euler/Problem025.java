package net.ehicks.euler;/*
The first 12 terms of the Fibonacci sequence:

    F_(1) = 1
    F_(2) = 1
    F_(3) = 2
    F_(4) = 3
    F_(5) = 5
    F_(6) = 8
    F_(7) = 13
    F_(8) = 21
    F_(9) = 34
    F_(10) = 55
    F_(11) = 89
    F_(12) = 144

The 12th term, F_(12), is the first term to contain three digits.

What is the first term in the Fibonacci sequence to contain 1000 digits?
*/
import java.math.BigInteger;

public class Problem025
{
    public static void main(String[] args)
    {
        BigInteger n = new BigInteger("1");
        BigInteger m = new BigInteger("1");
        BigInteger temp = new BigInteger("1");
        String tempString;
        int digits;

        System.out.println("F_(1) = 1");

        for(int i = 2; i < 10000; i++)
        {
            digits = n.toString().length();

            System.out.println("F_(" + i + ") = " + n + " Digits: " + digits);

            tempString = n.toString();
            temp = new BigInteger(tempString);
            n = n.add(m);
            m = new BigInteger(tempString);

        }
    }
}