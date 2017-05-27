package net.ehicks.euler;

/*
If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?
 */
public class Problem39
{
    public static void main(String[] args)
    {
        int pWithMostSolutions = 0;
        int mostSolutions = 0;
        long startTime = System.currentTimeMillis();

        for (int perimeter = 4; perimeter <= 1000; perimeter+=2)
        {
            int numberOfSolutions = 0;

            for (int a = 1; a < perimeter - 2; a++)
            {
                for (int b = 1; b < perimeter - 2; b++)
                {
                    for (int c = 1; c < perimeter - 2; c++)
                    {
                        if ((a + b + c) == perimeter)
                        {
                            if ((a*a + b*b) == c*c) numberOfSolutions++;
                        }
                    }
                }
            }
            if (numberOfSolutions > mostSolutions)
            {
                pWithMostSolutions = perimeter;
                mostSolutions = numberOfSolutions;
            }
            if (numberOfSolutions > 0) System.out.println(perimeter + ": " + numberOfSolutions);
        }
        System.out.println("Perimeter with the greatest number of solutions:" + pWithMostSolutions + ". Number of Solutions:" + mostSolutions + ".");
        System.out.println("Process took " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}