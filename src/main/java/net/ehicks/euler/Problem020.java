package net.ehicks.euler;/*
Find the sum of the digits in the number 100!

Output:
9332621544
3944152681
6992388562
6670049071
5968264381
6214685929
6389521759
9993229915
6089414639
7615651828
6253697920
8272237582
5118521091
6864000000
0000000000
00000000

648
*/
import java.math.BigInteger;

public class Problem020
{
    public static void main(String[] args)
    {
        String testString = "100";
        String outStr;
        int testNum = Integer.parseInt(testString);
        String subStr;
        int tempInt;
        int accumulator = 0;
        int length;

        BigInteger big1 = new BigInteger(testString);
        BigInteger big2 = new BigInteger(testString);
        BigInteger big3 = new BigInteger("1");

        for(int n = testNum; n > 0; n--)
        {
            while(big2.longValue() > 1)
            {
                big2 = big2.subtract(big3);
                big1 = big1.multiply(big2);
            }
        }

        outStr = big1.toString();
        length = outStr.length();

        for(int n = 0; n < length-1; n++)
        {
            subStr = outStr.substring(n, n+1);
            tempInt = Integer.parseInt(subStr);
            accumulator += tempInt;
        }

        System.out.println(big1+"\n\n"+accumulator);


    }
}