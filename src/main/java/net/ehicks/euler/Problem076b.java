package net.ehicks.euler;
/*
It is possible to write five as a sum in exactly six different ways:

4 + 1
3 + 2
3 + 1 + 1
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1

How many different ways can one hundred be written as a sum of at least two positive integers?
 */


// took around 700 minutes to finish!
import java.util.*;

public class Problem076b
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        Timer innerTimer = new Timer();

        for (int i = 60; i < 101; i++)
        {
            long combos = findCombos(i);
            String message = String.format("%4d: %16d combos %s", i, combos, innerTimer.getDuration(true));
            System.out.println(message);
        }

        timer.printDuration();
    }

    private static long findCombos(int number)
    {
        long totalCombos = 0;
        Set<List<Integer>> combos = new HashSet<>();
        combos.add(Collections.singletonList(number));

        // each loop we will find all combos of length i and stop when i == number
        for (int i = 0; i < number; i++)
        {
            Set<List<Integer>> newCombos = new HashSet<>();

            for (List<Integer> combo : combos)
            {
                for (Integer item : combo)
                {
                    if (item > 1)
                    {
                        for (int j = 1; j < item; j++)
                        {
                            if (item - j < j)
                                break;
                            List<Integer> newCombo = new ArrayList<>(combo);
                            int part1 = item - j;
                            int part2 = j;
                            newCombo.remove(item);
                            newCombo.add(part1);
                            newCombo.add(part2);
                            newCombo.sort(Comparator.reverseOrder());
                            newCombos.add(newCombo);
                        }
                    }
                }
            }

            totalCombos += newCombos.size();
            combos = newCombos;
        }

        return totalCombos;
    }
}
