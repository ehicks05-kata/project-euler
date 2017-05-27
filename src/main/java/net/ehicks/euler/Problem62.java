package net.ehicks.euler;/*
The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3).
In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
Find the smallest cube for which exactly five permutations of its digits are cube.
*/

import java.math.BigInteger;
import java.util.*;

public class Problem62
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();

        Cube answer = new Cube(BigInteger.ZERO, 0);
        List<Cube> answerPermutations = new ArrayList<>();

        List<Cube> cubes = getCubes(10_000);

        for (Cube cube : cubes)
        {
            List<Cube> permutations = new ArrayList<>(Arrays.asList(cube));
            for (Cube compareCube : cubes)
            {
                if (!cube.value.equals(compareCube.value) && cube.isMatchingDigitOccurrences(compareCube))
                    permutations.add(compareCube);
            }

            if (permutations.size() == 5)
            {
                answer = cube;
                answerPermutations = permutations;
                break;
            }
        }

        for (Cube permutation : answerPermutations)
            System.out.println(permutation);

        System.out.println("Smallest cube for which exactly five permutations of its digits are cube: " + answer.value);
        timer.printDuration();
    }

    private static List<Cube> getCubes(int count)
    {
        List<Cube> cubes = new ArrayList<>();
        for (int i = 1; i < count; i++)
            cubes.add(new Cube(BigInteger.valueOf(i).pow(3), i));
        return cubes;
    }

    private static class Cube
    {
        public BigInteger value;
        public int cubeOf;
        public int digits;
        public Map<Integer, Integer> digitOccurrences;

        public Cube(BigInteger value, int cubeOf)
        {
            this.value = value;
            this.cubeOf = cubeOf;
            this.digits = String.valueOf(value).length();

            this.digitOccurrences = new HashMap<>();
            for (String digitChar : String.valueOf(value).split(""))
            {
                int digit = Integer.parseInt(digitChar);
                if (digitOccurrences.get(digit) == null)
                    digitOccurrences.put(digit, 1);
                else
                    digitOccurrences.put(digit, digitOccurrences.get(digit) + 1);
            }
        }

        @Override
        public String toString()
        {
            return "Cube{" +
                    "value=" + value +
                    ", cubeOf=" + cubeOf +
                    '}';
        }

        public boolean isMatchingDigitOccurrences(Cube that)
        {
            if (this.digits != that.digits) return false;

            for (Integer key : digitOccurrences.keySet())
            {
                if (that.digitOccurrences.get(key) == null || !this.digitOccurrences.get(key).equals(that.digitOccurrences.get(key)))
                    return false;
            }
            return true;
        }
    }
}