package net.ehicks.euler;/*
Triangle, square, pentagonal, hexagonal, heptagonal, and octagonal numbers are all figurate (polygonal) numbers and are generated by the following formulae:

Triangle	 	P3,n=n(n+1)/2	 	1, 3, 6, 10, 15, ...
Square	 	    P4,n=n2	 	        1, 4, 9, 16, 25, ...
Pentagonal	 	P5,n=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
Hexagonal	 	P6,n=n(2n−1)	 	1, 6, 15, 28, 45, ...
Heptagonal	 	P7,n=n(5n−3)/2	 	1, 7, 18, 34, 55, ...
Octagonal	 	P8,n=n(3n−2)	 	1, 8, 21, 40, 65, ...
The ordered set of three 4-digit numbers: 8128, 2882, 8281, has three interesting properties.

The set is cyclic, in that the last two digits of each number is the first two digits of the next number (including the last number with the first).
Each polygonal type: triangle (P3,127=8128), square (P4,91=8281), and pentagonal (P5,44=2882), is represented by a different number in the set.
This is the only set of 4-digit numbers with this property.
Find the sum of the only ordered set of six cyclic 4-digit numbers for which each polygonal type:
triangle, square, pentagonal, hexagonal, heptagonal, and octagonal, is represented by a different number in the set.
*/

import java.util.*;

public class Problem061
{
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();

        int sum = 0;
        List<Integer> triangles = getPolygonals(3);
        List<Integer> squares = getPolygonals(4);
        List<Integer> pentagonals = getPolygonals(5);
        List<Integer> hexagonals = getPolygonals(6);
        List<Integer> heptagonals = getPolygonals(7);
        List<Integer> octagonals = getPolygonals(8);
        List<List<Integer>> polygonalSets = Arrays.asList(triangles, squares, pentagonals, hexagonals, heptagonals, octagonals);
        Map<Integer, List<Integer>> polygonalSetMap = new HashMap<>();
        polygonalSetMap.put(3, triangles);
        polygonalSetMap.put(4, squares);
        polygonalSetMap.put(5, pentagonals);
        polygonalSetMap.put(6, hexagonals);
        polygonalSetMap.put(7, heptagonals);
        polygonalSetMap.put(8, octagonals);

        List<Problem061Chain> chains = new ArrayList<>();
        int polygonalSetType = 3;
        for (List<Integer> polygonalSet : polygonalSets)
        {
            for (Integer polygonal : polygonalSet)
            {
                int comparisonPolygonalSetType = 3;
                for (List<Integer> comparisonPolygonalSet : polygonalSets)
                {
                    if (polygonalSet.equals(comparisonPolygonalSet))
                    {
                        comparisonPolygonalSetType++;
                        continue;
                    }
                    for (Integer comparisonPolygonal : comparisonPolygonalSet)
                    {
                        if (canBeChained(polygonal, comparisonPolygonal, true))
                            chains.addAll(getChains(polygonal, polygonalSetType, comparisonPolygonal, comparisonPolygonalSetType));
                    }
                    comparisonPolygonalSetType++;
                }
            }
            polygonalSetType++;
        }

        List<Problem061Chain> length3Chains = new ArrayList<>();
        for (Problem061Chain chain : chains)
        {
            Map<Integer, List<Integer>> setsToCheck = new HashMap<>(polygonalSetMap);
            for (Integer typeUsed : chain.getTypesUsed())
                setsToCheck.remove(typeUsed);

            for (Integer key : setsToCheck.keySet())
            {
                List<Integer> setToCheck = setsToCheck.get(key);
                for (Integer comparisonPolygonal : setToCheck)
                {
                    // can we add this new number to front of chain?
                    if (canBeChained(comparisonPolygonal, chain.number1, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.shiftByOne();
                        newChain.number1 = comparisonPolygonal;
                        newChain.type1 = key;
                        length3Chains.add(newChain);
                    }
                    // can we add this to end of chain?
                    if (canBeChained(chain.number2, comparisonPolygonal, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.number3 = comparisonPolygonal;
                        newChain.type3 = key;
                        length3Chains.add(newChain);
                    }
                }
            }
        }

        List<Problem061Chain> length4Chains = new ArrayList<>();
        for (Problem061Chain chain : length3Chains)
        {
            Map<Integer, List<Integer>> setsToCheck = new HashMap<>(polygonalSetMap);
            for (Integer typeUsed : chain.getTypesUsed())
                setsToCheck.remove(typeUsed);

            for (Integer key : setsToCheck.keySet())
            {
                List<Integer> setToCheck = setsToCheck.get(key);
                for (Integer comparisonPolygonal : setToCheck)
                {
                    // can we add this new number to front of chain?
                    if (canBeChained(comparisonPolygonal, chain.number1, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.shiftByOne();
                        newChain.number1 = comparisonPolygonal;
                        newChain.type1 = key;
                        length4Chains.add(newChain);
                    }
                    // can we add this to end of chain?
                    if (canBeChained(chain.number3, comparisonPolygonal, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.number4 = comparisonPolygonal;
                        newChain.type4 = key;
                        length4Chains.add(newChain);
                    }
                }
            }
        }

        List<Problem061Chain> length5Chains = new ArrayList<>();
        for (Problem061Chain chain : length4Chains)
        {
            Map<Integer, List<Integer>> setsToCheck = new HashMap<>(polygonalSetMap);
            for (Integer typeUsed : chain.getTypesUsed())
                setsToCheck.remove(typeUsed);

            for (Integer key : setsToCheck.keySet())
            {
                List<Integer> setToCheck = setsToCheck.get(key);
                for (Integer comparisonPolygonal : setToCheck)
                {
                    // can we add this new number to front of chain?
                    if (canBeChained(comparisonPolygonal, chain.number1, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.shiftByOne();
                        newChain.number1 = comparisonPolygonal;
                        newChain.type1 = key;
                        length5Chains.add(newChain);
                    }
                    // can we add this to end of chain?
                    if (canBeChained(chain.number4, comparisonPolygonal, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.number5 = comparisonPolygonal;
                        newChain.type5 = key;
                        length5Chains.add(newChain);
                    }
                }
            }
        }

        List<Problem061Chain> length6Chains = new ArrayList<>();
        for (Problem061Chain chain : length5Chains)
        {
            Map<Integer, List<Integer>> setsToCheck = new HashMap<>(polygonalSetMap);
            for (Integer typeUsed : chain.getTypesUsed())
                setsToCheck.remove(typeUsed);

            for (Integer key : setsToCheck.keySet())
            {
                List<Integer> setToCheck = setsToCheck.get(key);
                for (Integer comparisonPolygonal : setToCheck)
                {
                    // can we add this new number to front of chain?
                    if (canBeChained(comparisonPolygonal, chain.number1, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.shiftByOne();
                        newChain.number1 = comparisonPolygonal;
                        newChain.type1 = key;
                        length6Chains.add(newChain);
                    }
                    // can we add this to end of chain?
                    if (canBeChained(chain.number5, comparisonPolygonal, false))
                    {
                        Problem061Chain newChain = new Problem061Chain(chain);
                        newChain.number6 = comparisonPolygonal;
                        newChain.type6 = key;
                        length6Chains.add(newChain);
                    }
                }
            }
        }

        List<Problem061Chain> answerChains = new ArrayList<>();
        for (Problem061Chain chain : length6Chains)
        {
            // can we connect the ends of the chain?
            if (canBeChained(chain.number6, chain.number1, false))
            {
                Problem061Chain newChain = new Problem061Chain(chain);
                answerChains.add(newChain);
            }
        }

        Problem061Chain answerChain = answerChains.get(0);
        sum += answerChain.number1 + answerChain.number2 + answerChain.number3 + answerChain.number4 + answerChain.number5 + answerChain.number6;

        System.out.println("Find the sum of the only ordered set of six cyclic 4-digit numbers for which each polygonal type is represented by a different number in the set: " + sum);
        System.out.println("Done in " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    private static boolean canBeChained(int n1, int n2, boolean alsoCheckReverse)
    {
        String s1 = String.valueOf(n1);
        String s2 = String.valueOf(n2);
        if (alsoCheckReverse) return s1.substring(2).equals(s2.substring(0, 2)) || s2.substring(2).equals(s1.substring(0, 2));
        else return s1.substring(2).equals(s2.substring(0, 2));
    }

    private static List<Problem061Chain> getChains(int n1, int n1Type, int n2, int n2Type)
    {
        List<Problem061Chain> chains = new ArrayList<>();
        String s1 = String.valueOf(n1);
        String s2 = String.valueOf(n2);
        if (s1.substring(2).equals(s2.substring(0, 2)))
        {
            Problem061Chain chain = new Problem061Chain();
            chain.number1 = n1;
            chain.number2 = n2;
            chain.type1 = n1Type;
            chain.type2 = n2Type;
            chains.add(chain);
        }
        if (s2.substring(2).equals(s1.substring(0, 2)))
        {
            Problem061Chain chain = new Problem061Chain();
            chain.number1 = n2;
            chain.number2 = n1;
            chain.type1 = n2Type;
            chain.type2 = n1Type;
            chains.add(chain);
        }
        return chains;
    }

    private static List<Integer> getPolygonals(int type)
    {
        List<Integer> polygonals = new ArrayList<>();
        int previous = 0;
        for (int i = 1; previous < 10000; i++)
        {
            int polygonal = 0;
            if (type == 3) polygonal = i * (i + 1) / 2;
            if (type == 4) polygonal = i * i;
            if (type == 5) polygonal = i * (3 * i - 1) / 2;
            if (type == 6) polygonal = i * (2 * i - 1);
            if (type == 7) polygonal = i * (5 * i - 3) / 2;
            if (type == 8) polygonal = i * (3 * i - 2);
            if (polygonal > 999)
                polygonals.add(polygonal);
            previous = polygonal;
        }

        return polygonals;
    }

    static class Problem061Chain
    {
        public int number1;
        public int number2;
        public int number3;
        public int number4;
        public int number5;
        public int number6;
        public int type1;
        public int type2;
        public int type3;
        public int type4;
        public int type5;
        public int type6;

        public Problem061Chain()
        {
        }

        public Problem061Chain(Problem061Chain source)
        {
            this.number1 = source.number1;
            this.number2 = source.number2;
            this.number3 = source.number3;
            this.number4 = source.number4;
            this.number5 = source.number5;
            this.number6 = source.number6;
            this.type1 = source.type1;
            this.type2 = source.type2;
            this.type3 = source.type3;
            this.type4 = source.type4;
            this.type5 = source.type5;
            this.type6 = source.type6;
        }

        public void shiftByOne()
        {
            if (number6 != 0) return;
            number6 = number5;
            number5 = number4;
            number4 = number3;
            number3 = number2;
            number2 = number1;
            number1 = 0;
            type6 = type5;
            type5 = type4;
            type4 = type3;
            type3 = type2;
            type2 = type1;
            type1 = 0;
        }

        public List<Integer> getTypesUsed()
        {
            List<Integer> typesUsed = new ArrayList<>();
            if (type1 > 0) typesUsed.add(type1);
            if (type2 > 0) typesUsed.add(type2);
            if (type3 > 0) typesUsed.add(type3);
            if (type4 > 0) typesUsed.add(type4);
            if (type5 > 0) typesUsed.add(type5);
            if (type6 > 0) typesUsed.add(type6);
            return typesUsed;
        }
    }

}