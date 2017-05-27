package net.ehicks.euler;/*
See Project Euler for complete instructions...

The first ten continued fraction representations of (irrational) square roots are:

√2=[1;(2)], period=1
√3=[1;(1,2)], period=2
√5=[2;(4)], period=1
√6=[2;(2,4)], period=2
√7=[2;(1,1,1,4)], period=4
√8=[2;(1,4)], period=2
√10=[3;(6)], period=1
√11=[3;(3,6)], period=2
√12= [3;(2,6)], period=2
√13=[3;(1,1,1,1,6)], period=5

Exactly four continued fractions, for N ≤ 13, have an odd period.

How many continued fractions for N ≤ 10000 have an odd period?
*/

public class Problem64
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();
        int answer = 0;



        System.out.println("How many continued fractions for N ≤ 10000 have an odd period?: " + answer);
        timer.printDuration();
    }
}