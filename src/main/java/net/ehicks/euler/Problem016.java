package net.ehicks.euler;/*
2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000?
*/
import java.math.*;

public class Problem016
{
    public static void main(String[] args)
    {
        String st;
        String subSt;

        int stLength;
        int currentDigit;
        int sum = 0;

        BigInteger bigNumber = new BigInteger("2");

        bigNumber = bigNumber.pow(1000);

        st = bigNumber.toString();

        stLength = st.length();

        for(int n = 0; n < stLength; n++)
        {
            subSt = st.substring(n, n+1);
            currentDigit = Integer.parseInt(subSt);
            sum += currentDigit;
        }

        System.out.println("Sum of Digits = "+sum);
    }
}