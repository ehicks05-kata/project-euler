package net.ehicks.euler;

/*
Peter has nine four-sided (pyramidal) dice, each with faces numbered 1, 2, 3, 4.
Colin has six six-sided (cubic) dice, each with faces numbered 1, 2, 3, 4, 5, 6.

Peter and Colin roll their dice and compare totals: the highest total wins. The result is a draw if the totals are equal.

What is the probability that Pyramidal Pete beats Cubic Colin? Give your answer rounded to seven decimal places in the form 0.abcdefg
* */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem205
{
    private static final Random r = new Random();

    public static void main(String[] args)
    {
        Timer timer = new Timer();

        double wins = 0;
        double games = 0;
        double peteWinChance = 0;

        System.out.println("Probability: " + peteWinChance);
        timer.printDuration();
    }

    private static List<List<Integer>> getCombinations(int dice, int sides)
    {
        List<List<Integer>> combos = new ArrayList<>();
        List<Integer> firstCombo = new ArrayList<>();
        for (int i = 0; i < dice; i++)
            firstCombo.add(1);
        combos.add(firstCombo);

        List<Integer> lastCombo = new ArrayList<>();
        for (int i = 0; i < dice; i++)
            firstCombo.add(sides);

        List<Integer> combo = new ArrayList<>(firstCombo);
        while (true)
        {
            if (combo.equals(lastCombo))
                break;

            // todo
        }

        return combos;
    }
}
