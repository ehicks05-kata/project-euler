package net.ehicks.euler;

//Find the longest sequence using a starting number under one million.
public class Problem014
{
    public static void main(String[] args)
    {
        //start timer
        long startTime = System.currentTimeMillis();

        double currentNum;
        double terms;
        double mostTerms = 0;
        double nWithMostTerms = 0;

        for(int n = 1; n < 1000000; n++)
        {
            currentNum = n;
            terms = 1;

            if(n % 10000 == 0)
                System.out.println("Percent Complete: "+(n / 10000));

            while(currentNum != 1)
            {
                if(currentNum % 2 == 0)
                {
                    currentNum /= 2;
                }
                else
                {
                    currentNum = (currentNum * 3) + 1;
                }
                terms++;
            }

            if(terms > mostTerms)
            {
                mostTerms = terms;
                nWithMostTerms = n;
            }
        }
        //stop timer
        long stopTime = System.currentTimeMillis();
        //calculate time taken
        long runTime = stopTime - startTime;

        System.out.println("\nNumber with the Most Terms: "+nWithMostTerms+"\nNumber of Terms: "+mostTerms);
        System.out.println("\nTime to Run: "+runTime);
    }
}

/*
The following iterative sequence is defined for the set of positive integers:

n  n/2 (n is even)
n  3n + 1 (n is odd)

Which starting number, under one million, produces the longest chain?
*/