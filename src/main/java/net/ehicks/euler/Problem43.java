package net.ehicks.euler;

import java.util.*;

/*
The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

Let dsub1 be the 1st digit, dsub2 be the 2nd digit, and so on. In this way, we note the following:

dsub2dsub3dsub4=406 is divisible by 2
dsub3dsub4dsub5=063 is divisible by 3
dsub4dsub5dsub6=635 is divisible by 5
dsub5dsub6dsub7=357 is divisible by 7
dsub6dsub7dsub8=572 is divisible by 11
dsub7dsub8dsub9=728 is divisible by 13
dsub8dsub9dsub10=289 is divisible by 17

Find the sum of all 0 to 9 pandigital numbers with this property.
* */
public class Problem43
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        List<Integer> sub8To10s = new ArrayList<>();
        List<Integer> sub7To9s = new ArrayList<>();
        List<Integer> sub6To8s = new ArrayList<>();
        List<Integer> sub5To7s = new ArrayList<>();
        List<Integer> sub4To6s = new ArrayList<>();
        List<Integer> sub3To5s = new ArrayList<>();
        List<Integer> sub2To4s = new ArrayList<>();

        List<Integer> sub7To10s = new ArrayList<>();
        List<Integer> sub6To10s = new ArrayList<>();
        List<Integer> sub5To10s = new ArrayList<>();
        List<Integer> sub4To10s = new ArrayList<>();
        List<Integer> sub3To10s = new ArrayList<>();
        List<Integer> sub2To10s = new ArrayList<>();

        for (int i = 1; i < 1000; i++)
        {
            if (i % 17 == 0 && isPandigital2(i)) sub8To10s.add(i);
            if (i % 13 == 0 && isPandigital2(i)) sub7To9s.add(i);
            if (i % 11 == 0 && isPandigital2(i)) sub6To8s.add(i);
            if (i %  7 == 0 && isPandigital2(i)) sub5To7s.add(i);
            if (i %  5 == 0 && isPandigital2(i)) sub4To6s.add(i);
            if (i %  3 == 0 && isPandigital2(i)) sub3To5s.add(i);
            if (i %  2 == 0 && isPandigital2(i)) sub2To4s.add(i);
        }

        for (Integer sub7To9 : sub7To9s)
        {
            for (Integer sub8To10 : sub8To10s)
            {
                String s7To9 = String.valueOf(sub7To9);
                String s8To10 = String.valueOf(sub8To10);

                while (s7To9.length() < 3) s7To9 = "0" + s7To9;
                while (s8To10.length() < 3) s8To10 = "0" + s8To10;

                if (!isPandigital2(s7To9) || !isPandigital2(s8To10)) continue;

                if (s7To9.substring(1, 3).equals(s8To10.substring(0, 2)))
                {
                    int fourDigitNumberToAdd = Integer.parseInt(s7To9.substring(0, 1) + s8To10);
                    if (isPandigital2(fourDigitNumberToAdd))
                        sub7To10s.add(fourDigitNumberToAdd);
                }
            }
        }

        for (Integer sub6To8 : sub6To8s)
        {
            for (Integer sub7To10 : sub7To10s)
            {
                String s6To8 = String.valueOf(sub6To8);
                while (s6To8.length() < 3) s6To8 = "0" + s6To8;
                if (!isPandigital2(s6To8)) continue;

                String s7To10 = String.valueOf(sub7To10);
                while (s7To10.length() < 4) s7To10 = "0" + s7To10;
                if (!isPandigital2(s7To10)) continue;

                if (s6To8.substring(1, 3).equals(s7To10.substring(0, 2)))
                {
                    int fourDigitNumberToAdd = Integer.parseInt(s6To8.substring(0, 1) + s7To10);
                    if (isPandigital2(fourDigitNumberToAdd))
                        sub6To10s.add(fourDigitNumberToAdd);
                }
            }
        }

        for (Integer sub5To7 : sub5To7s)
        {
            for (Integer sub6To10 : sub6To10s)
            {
                String s5To7 = String.valueOf(sub5To7);
                while (s5To7.length() < 3) s5To7 = "0" + s5To7;
                if (!isPandigital2(s5To7)) continue;

                String s6To10 = String.valueOf(sub6To10);
                while (s6To10.length() < 5) s6To10 = "0" + s6To10;
                if (!isPandigital2(s6To10)) continue;

                if (s5To7.substring(1, 3).equals(s6To10.substring(0, 2)))
                {
                    int fourDigitNumberToAdd = Integer.parseInt(s5To7.substring(0, 1) + s6To10);
                    if (isPandigital2(fourDigitNumberToAdd))
                        sub5To10s.add(fourDigitNumberToAdd);
                }
            }
        }

        for (Integer sub4To6 : sub4To6s)
        {
            for (Integer sub5To10 : sub5To10s)
            {
                String s4To6 = String.valueOf(sub4To6);
                while (s4To6.length() < 3) s4To6 = "0" + s4To6;
                if (!isPandigital2(s4To6)) continue;

                String s5To10 = String.valueOf(sub5To10);
                while (s5To10.length() < 6) s5To10 = "0" + s5To10;
                if (!isPandigital2(s5To10)) continue;

                if (s4To6.substring(1, 3).equals(s5To10.substring(0, 2)))
                {
                    int fourDigitNumberToAdd = Integer.parseInt(s4To6.substring(0, 1) + s5To10);
                    if (isPandigital2(fourDigitNumberToAdd))
                        sub4To10s.add(fourDigitNumberToAdd);
                }
            }
        }

        for (Integer sub3To5 : sub3To5s)
        {
            for (Integer sub4To10 : sub4To10s)
            {
                String s3To5 = String.valueOf(sub3To5);
                while (s3To5.length() < 3) s3To5 = "0" + s3To5;
                if (!isPandigital2(s3To5)) continue;

                String s4To10 = String.valueOf(sub4To10);
                while (s4To10.length() < 7) s4To10 = "0" + s4To10;
                if (!isPandigital2(s4To10)) continue;

                if (s3To5.substring(1, 3).equals(s4To10.substring(0, 2)))
                {
                    int fourDigitNumberToAdd = Integer.parseInt(s3To5.substring(0, 1) + s4To10);
                    if (isPandigital2(fourDigitNumberToAdd))
                        sub3To10s.add(fourDigitNumberToAdd);
                }
            }
        }

        for (Integer sub2To4 : sub2To4s)
        {
            for (Integer sub3To10 : sub3To10s)
            {
                String s2To4 = String.valueOf(sub2To4);
                while (s2To4.length() < 3) s2To4 = "0" + s2To4;
                if (!isPandigital2(s2To4)) continue;

                String s3To10 = String.valueOf(sub3To10);
                while (s3To10.length() < 8) s3To10 = "0" + s3To10;
                if (!isPandigital2(s3To10)) continue;

                if (s2To4.substring(1, 3).equals(s3To10.substring(0, 2)))
                {
                    int fourDigitNumberToAdd = Integer.parseInt(s2To4.substring(0, 1) + s3To10);
                    if (isPandigital2(fourDigitNumberToAdd))
                        sub2To10s.add(fourDigitNumberToAdd);
                }
            }
        }

        long sum = 0;
        for (Integer number : sub2To10s)
        {
            // try to find a first digit that retains pandigitality
            String workNumber = String.valueOf(number);
            for (int i = 0; i < 10; i++)
            {
                String possiblePandigital = String.valueOf(i) + workNumber;
                if (isPandigital2(possiblePandigital))
                    sum += Long.parseLong(possiblePandigital);
            }
        }

        // OLD NAIVE SOLUTION:
//        long numbersChecked = 0;
//        long sum = 0;
//        for (long i = 1_023_456_789; i <= 9_876_543_210L; i++)
//        {
//            if (isPandigital(i) && hasDivisibleProperty(i))
//            {
//                sum += i;
//                System.out.println(i);
//            }
//            numbersChecked++;
//            if (numbersChecked % 100_000_000 == 0) System.out.println((System.currentTimeMillis() - startTime) + " ms: Numbers Checked: " + numbersChecked);
//        }

        System.out.println("sum of all 0 to 9 pandigital numbers with this property: " + sum);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
        // Answer: 16695334890
        // Speed Record without seeing other solutions: 11 min 40 sec
        // Speed Record with    seeing other solutions: 211 ms
    }

    private static boolean isPandigital(long number)
    {
        List<String> digits = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        // each digit must occur once and only once (include 0 for this problem)
        String digitString = String.valueOf(number);
        for (String digit : digits)
            if (!digitString.contains(digit))
                return false;

        return true;
    }

    private static boolean isPandigital2(long number)
    {
        String digitString = String.valueOf(number);
        for (int i = 0; i <= 9; i++)
            if (digitString.length() - digitString.replaceAll(String.valueOf(i), "").length() > 1)
                return false;

        return true;
    }

    private static boolean isPandigital2(String number)
    {
        for (int i = 0; i <= 9; i++)
            if (number.length() - number.replaceAll(String.valueOf(i), "").length() > 1)
                return false;

        return true;
    }

    private static boolean hasDivisibleProperty(long number)
    {
        List<Integer> divisors = Arrays.asList(2, 3, 5, 7, 11, 13, 17);

        String workNumber = String.valueOf(number);
        int count = 0;
        for (Integer i : divisors)
        {
            count++;
            String subString = workNumber.substring(count, count + 3);
            if (Integer.parseInt(subString) % i != 0)
                return false;
        }

        return true;
    }
}
