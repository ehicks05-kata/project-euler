package net.ehicks.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/*
In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

High Card:       Highest value card.
One Pair:        Two cards of the same value.
Two Pairs:       Two different pairs.
Three of a Kind: Three cards of the same value.
Straight:        All cards are consecutive values.
Flush:           All cards of the same suit.
Full House:      Three of a kind and a pair.
Four of a Kind:  Four cards of the same value.
Straight Flush:  All cards are consecutive values of same suit.
Royal Flush:     Ten, Jack, Queen, King, Ace, in same suit.

The cards are valued in the order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives.
But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below);
if the highest cards tie then the next highest cards are compared, and so on.

The file, poker.txt, contains one-thousand random hands dealt to two players.
Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards.
You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.

How many hands does Player 1 win?
*/
public class Problem054
{
    private static Map<Integer, String> rankNames = new HashMap<>();

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        rankNames = new HashMap<>();
        rankNames.put(1, "high card");
        rankNames.put(2, "1 pair");
        rankNames.put(3, "2 pair");
        rankNames.put(4, "3 of a kind");
        rankNames.put(5, "straight");
        rankNames.put(6, "flush");
        rankNames.put(7, "full house");
        rankNames.put(8, "4 of a kind");
        rankNames.put(9, "straight flush");
        rankNames.put(10, "royal flush");

        int p1Wins = 0;
        int lineNumber = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/problem054poker.txt")))
        {
            while (br.ready())
            {
                List<String> cards = Arrays.asList(br.readLine().split(" "));
                List<String> p1Hand = cards.subList(0, 5);
                List<String> p2Hand = cards.subList(5, 10);

                System.out.print(lineNumber++ + ": ");
                if (getWinningHand(p1Hand, p2Hand) > 0) p1Wins++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("oops");
        }

        System.out.println("How many hands does Player 1 win: " + p1Wins);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static int getWinningHand(List<String> p1Hand, List<String> p2Hand)
    {
        int value = 0;

        // rank info contains rank as well as info needed to break ties...
        List<Integer> p1RankInfo = getRankInfo(p1Hand);
        List<Integer> p2RankInfo = getRankInfo(p2Hand);

        for (int i = 0; i < p1RankInfo.size(); i++)
            if (!p1RankInfo.get(i).equals(p2RankInfo.get(i)))
            {
                int result = p1RankInfo.get(i) - p2RankInfo.get(i);
                if (result > 0) System.out.println(rankNames.get(p1RankInfo.get(0)) + " beats " + rankNames.get(p2RankInfo.get(0)));
                if (result < 0) System.out.println(rankNames.get(p2RankInfo.get(0)) + " beats " + rankNames.get(p1RankInfo.get(0)));
                return result;
            }

        return value;
    }

    private static List<Integer> getRankInfo(List<String> hand)
    {
        List<Integer> tiebreakers = new ArrayList<>();

        if (isRoyal(hand) && isFlush(hand))
        {
            tiebreakers.add(0, 10);
            return tiebreakers;
        }
        if (isStraight(hand) && isFlush(hand))
        {
            tiebreakers = Arrays.asList(9, getHighestCard(hand));
            return tiebreakers;
        }
        if (isFourOfAKind(hand, tiebreakers))
        {
            tiebreakers.add(0, 8);
            return tiebreakers;
        }
        tiebreakers = new ArrayList<>();
        if (isFullHouse(hand, tiebreakers))
        {
            tiebreakers.add(0, 7);
            return tiebreakers;
        }
        if (isFlush(hand))
        {
            tiebreakers = Arrays.asList(6, getHighestCard(hand));
            return tiebreakers;
        }
        if (isStraight(hand))
        {
            tiebreakers = Arrays.asList(5, getHighestCard(hand));
            return tiebreakers;
        }
        tiebreakers = new ArrayList<>();
        if (isThreeOfAKind(hand, tiebreakers))
        {
            tiebreakers.add(0, 4);
            return tiebreakers;
        }
        tiebreakers = new ArrayList<>();
        if (isTwoPairs(hand, tiebreakers))
        {
            tiebreakers.add(0, 3);
            return tiebreakers;
        }
        tiebreakers = new ArrayList<>();
        if (isOnePair(hand, tiebreakers))
        {
            tiebreakers.add(0, 2);
            return tiebreakers;
        }

        tiebreakers = new ArrayList<>();
        tiebreakers.add(1);
        tiebreakers.addAll(getCardValuesReverse(hand));
        return tiebreakers;
    }

    private static boolean isFlush(List<String> hand)
    {
        String suit = hand.get(0).substring(1, 2);
        for (int i = 1; i < hand.size(); i++)
            if (!hand.get(i).substring(1, 2).equals(suit))
                return false;
        return true;
    }

    private static boolean isRoyal(List<String> hand)
    {
        List<Integer> values = getCardValues(hand);

        for (int i = 10; i < 15; i++)
            if (!values.contains(i))
                return false;
        return true;
    }

    private static boolean isStraight(List<String> hand)
    {
        List<Integer> values = getCardValues(hand);

        if (getValueMap(values).size() != 5)
            return false;

        for (int i = values.get(0); i < values.get(values.size() - 1); i++)
            if (!values.contains(i))
                return false;
        return true;
    }

    private static List<Integer> getCardValues(List<String> hand)
    {
        List<Integer> values = new ArrayList<>();
        for (String card : hand)
            values.add(getCardValue(card));

        Collections.sort(values);
        return values;
    }

    private static List<Integer> getCardValuesReverse(List<String> hand)
    {
        List<Integer> values = new ArrayList<>();
        for (String card : hand)
            values.add(getCardValue(card));

        Collections.sort(values);

        List<Integer> reversed = new ArrayList<>();
        for (int i = values.size() - 1; i >= 0; i--)
            reversed.add(values.get(i));

        return reversed;
    }

    private static List<Integer> getCardValuesReverse2(List<Integer> handValues)
    {
        Collections.sort(handValues);

        List<Integer> reversed = new ArrayList<>();
        for (int i = handValues.size() - 1; i >= 0; i--)
            reversed.add(handValues.get(i));

        return reversed;
    }

    private static int getHighestCard(List<String> hand)
    {
        return getCardValues(hand).get(4);
    }

    private static boolean isFourOfAKind(List<String> hand, List<Integer> tiebreakers)
    {
        Map<Integer, Integer> valueMap = getValueMap(getCardValues(hand));

        boolean isFourOfAKind = false;
        for (Integer value : valueMap.keySet())
        {
            if (valueMap.get(value) == 4)
            {
                isFourOfAKind = true;
                tiebreakers.add(0, value);
            }
            else
                tiebreakers.add(value);
        }

        return isFourOfAKind;
    }

    private static boolean isThreeOfAKind(List<String> hand, List<Integer> tiebreakers)
    {
        boolean isThreeOfAKind = getNumberOfXOfAKinds(hand, 3) == 1;

        if (isThreeOfAKind)
        {
            Map<Integer, Integer> valueMap = getValueMap(getCardValues(hand));

            List<Integer> nonSetValues = new ArrayList<>();

            for (Integer value : valueMap.keySet())
            {
                if (valueMap.get(value) == 3)
                    tiebreakers.add(0, value);
                else
                    nonSetValues.add(value);
            }
            nonSetValues = getCardValuesReverse2(nonSetValues);
            tiebreakers.addAll(nonSetValues);

        }

        return isThreeOfAKind;
    }

    private static boolean isTwoPairs(List<String> hand, List<Integer> tiebreakers)
    {
        boolean isTwoPairs = getNumberOfXOfAKinds(hand, 2) == 2;

        if (isTwoPairs)
        {
            Map<Integer, Integer> valueMap = getValueMap(getCardValues(hand));

            int nonSetValue = 0;

            for (Integer value : valueMap.keySet())
            {
                if (valueMap.get(value) == 2)
                {
                    if (tiebreakers.size() == 0)
                        tiebreakers.add(0, value);
                    else
                    {
                        if (tiebreakers.get(0) < value) tiebreakers.add(0, value);
                        else tiebreakers.add(value);
                    }
                }
                else
                    nonSetValue = value;
            }
            tiebreakers.add(nonSetValue);
        }

        return isTwoPairs;
    }

    private static boolean isOnePair(List<String> hand, List<Integer> tiebreakers)
    {
        boolean isOnePair = getNumberOfXOfAKinds(hand, 2) == 1;

        if (isOnePair)
        {
            List<Integer> nonSetValues = new ArrayList<>();

            Map<Integer, Integer> valueMap = getValueMap(getCardValues(hand));
            for (Integer value : valueMap.keySet())
            {
                if (valueMap.get(value) == 2)
                    tiebreakers.add(0, value);
                else
                    nonSetValues.add(value);
            }
            nonSetValues = getCardValuesReverse2(nonSetValues);
            tiebreakers.addAll(nonSetValues);
        }

        return isOnePair;
    }

    private static boolean isFullHouse(List<String> hand, List<Integer> tiebreakers)
    {
        boolean isFullHouse = getNumberOfXOfAKinds(hand, 3) == 1 && getNumberOfXOfAKinds(hand, 2) == 1;
        if (isFullHouse)
        {
            Map<Integer, Integer> valueMap = getValueMap(getCardValues(hand));
            for (Integer value : valueMap.keySet())
            {
                if (valueMap.get(value) == 3)
                    tiebreakers.add(0, value);
                else
                    tiebreakers.add(1, value);
            }
        }

        return isFullHouse;
    }

    private static int getNumberOfXOfAKinds(List<String> hand, int setSize)
    {
        int numberOfXOfAKinds = 0;

        Map<Integer, Integer> valueMap = getValueMap(getCardValues(hand));
        for (Integer value : valueMap.keySet())
            if (valueMap.get(value) == setSize)
                numberOfXOfAKinds++;

        return numberOfXOfAKinds;
    }

    private static Map<Integer, Integer> getValueMap(List<Integer> values)
    {
        Map<Integer, Integer> valueMap = new HashMap<>();
        for (Integer value : values)
        {
            if (valueMap.get(value) == null) valueMap.put(value, 1);
            else valueMap.put(value, valueMap.get(value) + 1);
        }
        return valueMap;
    }

    private static int getCardValue(String card)
    {
        int value = 0;
        char rawValue = card.charAt(0);
        if (Character.isDigit(rawValue))
            value = rawValue - '0';
        else
        {
            if (rawValue == 'T') value = 10;
            if (rawValue == 'J') value = 11;
            if (rawValue == 'Q') value = 12;
            if (rawValue == 'K') value = 13;
            if (rawValue == 'A') value = 14;
        }
        return value;
    }
}