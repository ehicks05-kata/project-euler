package net.ehicks.euler;

/*Find the sum of all the primes below two million.*/
public class Problem10
{
    public static void main(String[] args)
    {
        findPrimeSum();
    }
    public static void findPrimeSum()
    {
        final int GIVEN = 2000000; //we want to find the sum of all primes below this number
        final double GIVEN_ROOT = Math.sqrt(GIVEN);
        final int GIVEN_ROOT_INT = (int)GIVEN_ROOT;
        long sum = 0; //accumulator
        boolean[] list = new boolean[GIVEN]; //create array

        for(int a = 1; a < GIVEN_ROOT_INT; a++) //This is the filter loop
        {
            if(!list[a]) //if the value in a is true, don't run this block
            {
                for(int b = a; b < (GIVEN-1); b = b+(a+1)) //set loop start point equal to a and continue through the array
                {
                    list[b] = true;
                }
                list[a] = false;
            }
        }
        for(int c = 1; c < (GIVEN-1); c++)
        {
            if(!list[c])
            {
                sum += (c+1); //add the current number to the sum
            }
        }
        System.out.println(sum); //print the sum
    }
}