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

import java.util.*;

public class Problem076
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        Map<Byte, Integer> comboCounts = new HashMap<>();

        Set<List<Byte>> combos = new HashSet<>();
        combos.add(Arrays.asList((byte)1, (byte) 1));
        for (byte i = 3; i < 101; i++)
        {
            combos = incrementCombinations(combos);

            comboCounts.put(i, combos.size());
            int comboCountDelta = combos.size() - comboCounts.getOrDefault((byte) (i - (byte) 1), 0);
            double percentGrowth = (double) comboCountDelta / comboCounts.getOrDefault((byte) (i - (byte) 1), 1);
            percentGrowth *= 100;
            double avgComboSize = combos.stream().mapToInt(value -> value.size()).average().getAsDouble();
            String message = String.format("%4d: %16d combos %10f%% %s, avg combo size: %6f", i, combos.size(), percentGrowth, timer.getDuration(true), avgComboSize);
            System.out.println(message);
        }

        System.out.println(combos.size());
        timer.printDuration();
    }

    private static Set<List<Byte>> incrementCombinations(Set<List<Byte>> combinations)
    {
        Set<List<Byte>> newCombos = new HashSet<>();

        Set<Byte> distinctItems;
        List<Byte> newCombo;
        for (List<Byte> combination : combinations)
        {
            // take every distinct item in the combination and increase it by one
            distinctItems = new HashSet<>(combination);
            for (Byte item : distinctItems)
            {
                newCombo = new ArrayList<>(combination);
                newCombo.remove(item);
                newCombo.add((byte) (item + (byte) 1));
                newCombo.sort(Comparator.reverseOrder());
                newCombos.add(newCombo);
            }

            // also just append '1' to the combo
            newCombo = new ArrayList<>(combination);
            newCombo.add((byte) 1);
            newCombo.sort(Comparator.reverseOrder());
            newCombos.add(newCombo);
        }

        return newCombos;
    }
}
