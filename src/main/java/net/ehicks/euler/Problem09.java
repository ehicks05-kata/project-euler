package net.ehicks.euler;

/*There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.*/
public class Problem09
{
    public static void main(String[] args)
    {
        findPythagoreanTrip();
    }
    public static void findPythagoreanTrip()
    {
        final int GIVEN = 1000;

        for(int a = 1; a < GIVEN ; a++) //This loop steps through
        {
            for(int b = a; b < GIVEN ; b++)
            {
                for(int c = b; c < GIVEN ; c++)
                {
                    boolean isASumX, isATriplet;

                    isASumX = isSumX(a, b, c, GIVEN);
                    isATriplet = isTriplet(a, b, c);

                    if(isASumX && isATriplet)
                        System.out.println(a+"\n"+b+"\n"+c+"\n"+(a*b*c));
                }
            }
        }
    }
    public static boolean isTriplet(int x, int y, int z)
    {
        return ((x*x)+(y*y) == (z*z));
    }
    public static boolean isSumX(int m, int n, int o, int p)
    {
        return ((m+n+o) == p);
    }
}