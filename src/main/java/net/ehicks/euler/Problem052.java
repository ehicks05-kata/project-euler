package net.ehicks.euler;

import java.util.HashMap;
import java.util.Map;

/*
It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
*/
public class Problem052
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < 2_000_000; i++)
        {
            Map<Character, Integer> originalCharMap = buildCharMap(i);

            boolean allMatches = true;
            for (int multiplier = 2; multiplier < 7; multiplier++)
            {
                if (!originalCharMap.equals(buildCharMap(i * multiplier))) allMatches = false;
            }
            if (allMatches)
            {
                System.out.println(i);
                break;
            }
        }
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");

    }

    private static Map<Character, Integer> buildCharMap(int i)
    {
        Map<Character, Integer> charMap = new HashMap<>();
        char[] chars = String.valueOf(i).toCharArray();
        for (char character : chars)
        {
            if (charMap.get(character) == null) charMap.put(character, 1);
            else charMap.put(character, charMap.get(character) + 1);
        }

        return charMap;
    }
}
