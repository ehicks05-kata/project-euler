package net.ehicks.euler;/* Problem 24 What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9? */
import java.util.ArrayList;
import java.util.List;
public class Problem24
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<Integer> digits = generateArray(10);
        System.out.print("1: ");
        printArray(digits);
        for(int i = 2; i < 1000001; i++)
        {
            digits = doPermutation(digits, i);
            if(i > 999000)
            {
                System.out.print(i+": ");
                printArray(digits);
            }
        }
    }
    public static ArrayList<Integer> doPermutation(ArrayList<Integer> digits, int currentPermutation)
    {
        // Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
        int k = -1;
        for(int i = 0; i < digits.size() - 1; i++)
        {
            if(digits.get(i) < digits.get(i + 1)) k = i;
        }
        //Find the largest index l such that a[k] < a[l]. Since k + 1 is such an index, l is well defined and satisfies k < l.
        int l = 0;
        for(int i = 0; i < digits.size(); i++)
        {
            if(digits.get(k) < digits.get(i)) l = i;
        }
        //Swap a[k] with a[l].
        if(k != -1) swapDigits(digits, k, l);
        //Reverse the sequence from a[k + 1] up to and including the final element a[n].
        if(k != -1)
        {
            ArrayList<Integer> part1 = innerList(digits, 0, k + 1);
            ArrayList<Integer> part2 = innerList(digits, k + 1, digits.size());
            ArrayList<Integer> part2Reversed = reverse(part2);
            part1.addAll(part2Reversed);
            //System.out.println("K: " + k + " L: " + l);
            return part1;
        }
        if(k == -1) reverse(digits);
        return digits;
    }
    public static ArrayList<Integer> innerList(ArrayList<Integer> original, int start, int end)
    {
        int size = end - start;
        ArrayList<Integer> inner = generateArray(size);
        for(int i = 0; i < size; i++)
        {
            inner.set(i, original.get(start));
            start++;
        }
        return inner;
    }
    public static ArrayList<Integer> generateArray(int size)
    {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) digits.add(i);
        return digits;
    }
    public static void printArray(List<Integer> arrayToPrint)
    {
        for (int i = 0; i < arrayToPrint.size(); i++) System.out.print(arrayToPrint.get(i) + " ");
        System.out.println();
    }
    public static void swapDigits(ArrayList<Integer> arrayToSwap, Integer firstDigitIndex, Integer secondDigitIndex)
    {
        int firstDigit = arrayToSwap.get(firstDigitIndex);
        int secondDigit = arrayToSwap.get(secondDigitIndex);

        arrayToSwap.set(firstDigitIndex, secondDigit);
        arrayToSwap.set(secondDigitIndex, firstDigit);
    }
    public static ArrayList<Integer> reverse(List<Integer> original)
    {
        int size = original.size();
        ArrayList<Integer> reversedArray = generateArray(size);
        for(Integer i : original)
        {
            reversedArray.set(size - 1, i);
            size--;
        }
        return reversedArray;
    }
}