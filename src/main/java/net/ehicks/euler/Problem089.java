package net.ehicks.euler;

/*
For a number written in Roman numerals to be considered valid there are basic rules which must be followed.
Even though the rules allow some numbers to be expressed in more than one way there is always a "best" way of writing a particular number.

For example, it would appear that there are at least six ways of writing the number sixteen:

IIIIIIIIIIIIIIII
VIIIIIIIIIII
VVIIIIII
XIIIIII
VVVI
XVI

However, according to the rules only XIIIIII and XVI are valid, and the last example is considered to be the most efficient,
as it uses the least number of numerals.

The 11K text file, roman.txt (right click and 'Save Link/Target As...'), contains one thousand numbers written in valid,
but not necessarily minimal, Roman numerals; see About... Roman Numerals for the definitive rules for this problem.

Find the number of characters saved by writing each of these in their minimal form.

Note: You can assume that all the Roman numerals in the file contain no more than four consecutive identical units.


ROMAN NUMERAL RULES:
Traditional Roman numerals are made up of the following denominations:

I = 1
V = 5
X = 10
L = 50
C = 100
D = 500
M = 1000

In order for a number written in Roman numerals to be considered valid there are three basic rules which must be followed.

Numerals must be arranged in descending order of size.
M, C, and X cannot be equalled or exceeded by smaller denominations.
D, L, and V can each only appear once.

In addition to the three rules given above, if subtractive combinations are used then the following four rules must be followed.

Only one I, X, and C can be used as the leading numeral in part of a subtractive pair.
I can only be placed before V and X.
X can only be placed before L and C.
C can only be placed before D and M.

* */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem089
{
    public static void main(String[] args) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("src/data/Problem089Roman.txt"));

        int answer = lines.stream()
                .mapToInt(roman ->
                {
                    int decimal = rtd(roman);
                    String minimalRoman = dtr(decimal);
                    int charsSaved = roman.length() - minimalRoman.length();
                    System.out.println(roman + " -> " + decimal + " -> " + minimalRoman + " -> chars saved: " + charsSaved);
                    return charsSaved;
                }).sum();

        System.out.println("total chars saved: " + answer);
    }

    static Map<String, Integer> numeralToValue = new HashMap<>();

    static
    {
        numeralToValue.put("I", 1);
        numeralToValue.put("V", 5);
        numeralToValue.put("X", 10);
        numeralToValue.put("L", 50);
        numeralToValue.put("C", 100);
        numeralToValue.put("D", 500);
        numeralToValue.put("M", 1000);
    }

    static List<String> leadingSubtractives = Arrays.asList("I", "X", "C");

    private static int rtd(String roman)
    {
        int result = 0;

        for (int i = 0; i < roman.length(); i++)
        {
            String c = roman.substring(i, i + 1);

            if (isValidSubtractive(roman, i))
            {
                String next = roman.substring(i + 1, i + 2);
                result += numeralToValue.get(next) - numeralToValue.get(c);
                i++;
            }
            else
                result += numeralToValue.get(c);
        }

        return result;
    }

    private static boolean isValidSubtractive(String roman, int startIndex)
    {
        if (startIndex >= roman.length() - 1)
            return false;

        String c = roman.substring(startIndex, startIndex + 1);
        if (!leadingSubtractives.contains(c))
            return false;

        String next = roman.substring(startIndex + 1, startIndex + 2);
        boolean validSubtractive = false;

        if (c.equals("I") && (next.equals("V") || next.equals("X"))) validSubtractive = true;
        if (c.equals("X") && (next.equals("L") || next.equals("C"))) validSubtractive = true;
        if (c.equals("C") && (next.equals("D") || next.equals("M"))) validSubtractive = true;

        return validSubtractive;
    }

    private static String dtr(int decimal)
    {
        StringBuilder result = new StringBuilder();

        while (decimal > 0)
        {
            if (decimal >= 1000)
            {
                result.append("M");
                decimal -= 1000;
                continue;
            }
            if (decimal >= 900)
            {
                result.append("CM");
                decimal -= 900;
                continue;
            }
            if (decimal >= 500)
            {
                result.append("D");
                decimal -= 500;
                continue;
            }
            if (decimal >= 400)
            {
                result.append("CD");
                decimal -= 400;
                continue;
            }
            if (decimal >= 100)
            {
                result.append("C");
                decimal -= 100;
                continue;
            }
            if (decimal >= 90)
            {
                result.append("XC");
                decimal -= 90;
                continue;
            }
            if (decimal >= 50)
            {
                result.append("L");
                decimal -= 50;
                continue;
            }
            if (decimal >= 40)
            {
                result.append("XL");
                decimal -= 40;
                continue;
            }
            if (decimal >= 10)
            {
                result.append("X");
                decimal -= 10;
                continue;
            }
            if (decimal >= 9)
            {
                result.append("IX");
                decimal -= 9;
                continue;
            }
            if (decimal >= 5)
            {
                result.append("V");
                decimal -= 5;
                continue;
            }
            if (decimal >= 4)
            {
                result.append("IV");
                decimal -= 4;
                continue;
            }
            if (decimal >= 1)
            {
                result.append("I");
                decimal -= 1;
                continue;
            }
        }

        return result.toString();
    }
}
