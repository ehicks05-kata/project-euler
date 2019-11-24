package net.ehicks.euler;

import java.io.BufferedReader;
import java.io.FileReader;

/*
The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value.
For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

Using words.txt, a 16K text file containing nearly two-thousand common English words, how many are triangle words?
 */
public class Problem042
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        String[] words = null;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("src/data/Problem042Words.txt"));
            String inputText = br.readLine();
            inputText = inputText.replaceAll("\"", "");
            words = inputText.split(",");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        int triangleNumbers = 0;
        for (String word : words)
        {
            String[] chars = word.split("");
            int numberValue = 0;
            for (String character : chars)
            {
                if (character.length() > 0)
                    numberValue += convertLetterToNumber(character.charAt(0));
            }

            if (isTriangleNumber(numberValue))
            {
                triangleNumbers++;
                System.out.println(word);
            }
        }

        System.out.println("Number of triangle Numbers: " + triangleNumbers);
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static boolean isTriangleNumber(int number)
    {
        for (int i = 1; i < 20; i++)
        {
            int triangle = (i * (i + 1)) / 2;
            if (number == triangle) return true;
        }

        return false;
    }

    public static int convertLetterToNumber(char letter)
    {
        return Character.isUpperCase(letter) ? letter - 64 : letter - 96;
    }
}
