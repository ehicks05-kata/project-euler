package net.ehicks.euler;

/*Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ? b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.*/
public class Problem021
{
    public static void main(String[] args)
    {
        final int MAX_NUM = 10000;
        int sumOfAllAmicable = 0, sumOfDivisors = 0;
        int testNumLeft = 0, testNumRight = 0, testNum2Left = 0, testNum2Right = 0;
        int divisorSums[][] = new int[MAX_NUM][2];

        //Generate array: the first column = n, the second column = d(n)
        for(int i = 1; i < MAX_NUM; i++)
        {
            sumOfDivisors = 0;
            divisorSums[i][0] = i;

            for(int j = 1; j < divisorSums[i][0]; j++)
            {
                if(divisorSums[i][0] % j == 0)
                {
                    sumOfDivisors += j;
                }
            }
            divisorSums[i][1] = sumOfDivisors;
            System.out.println(divisorSums[i][0]+"  "+divisorSums[i][1]);
        }
        //run through each number and if , add both numbers to sumOfAllAmicable
        for(int i = 1; i < MAX_NUM; i++)
        {
            if(divisorSums[i][1] < MAX_NUM)
                testNum2Right = divisorSums[divisorSums[i][1]][1];

            if(divisorSums[i][0] != divisorSums[i][1] && testNum2Right == divisorSums[i][0])
            {
                sumOfAllAmicable += divisorSums[i][0] + divisorSums[i][1];
                System.out.println(divisorSums[i][0] +" "+ divisorSums[i][1]);
            }

        }
        System.out.println(sumOfAllAmicable/2);
    }
}