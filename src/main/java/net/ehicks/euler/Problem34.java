package net.ehicks.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: as 1! = 1 and 2! = 2 are not sums they are not included.
* */
public class Problem34
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        long min = 3;
        long max = 9_999_999;
        List<Long> sumFactNumbers = new ArrayList<>();

        Map<String, Integer> factorials = new HashMap<>();
        factorials.put("0", 1);
        factorials.put("1", 1);
        factorials.put("2", 2);
        factorials.put("3", 6);
        factorials.put("4", 24);
        factorials.put("5", 120);
        factorials.put("6", 720);
        factorials.put("7", 7 * factorials.get("6"));
        factorials.put("8", 8 * factorials.get("7"));
        factorials.put("9", 9 * factorials.get("8"));

        for (long i = min; i < max; i++)
        {
            String[] str = String.valueOf(i).split("");

            int sum = 0;
            for (String character : str)
                sum += factorials.get(character);

            if (sum == i)
            {
                sumFactNumbers.add(i);
                System.out.println(i);
            }
        }

        long sum = 0;
        for (Long number : sumFactNumbers)
            sum += number;

        System.out.println("Sum of all numbers which are equal to the sum of the factorial of their digits: " + sum);

        long end = System.currentTimeMillis();
        long durationMillis = end - start;
        int seconds = (int) (durationMillis / 1000) % 60 ;
        int minutes = (int) ((durationMillis / (1000*60)) % 60);
        System.out.println("Runtime: " + minutes + " minutes, " + seconds + " seconds.");
    }
}
