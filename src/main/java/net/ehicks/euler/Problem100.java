package net.ehicks.euler;

/*
If a box contains twenty-one coloured discs, composed of fifteen blue discs and six red discs, and two discs were taken at random,
it can be seen that the probability of taking two blue discs, P(BB) = (15/21)×(14/20) = 1/2.

The next such arrangement, for which there is exactly 50% chance of taking two blue discs at random,
is a box containing eighty-five blue discs and thirty-five red discs.

By finding the first arrangement to contain over 10^12 = 1,000,000,000,000 discs in total,
determine the number of blue discs that the box would contain.
* */

/*
    Optimizing a Brute Force Solution:

    The first example probabilities are 15/21 and 14/20. In decimal these are .714 and .700.
    The next examples in decimal are .708 and .705. It looks like the two fractions are getting closer.
    So the first speedup was to just start each numerator off so it would be at the previous solution's second probability.
    This makes it so for each denominator you only have to check about 2 numerators.

    Runtime was still slow so the next observation was to see if there was a pattern to the growth of the denominators
    of each solution. Just looking at the first few solutions showed the denominator jumped by 5.25 times between the first
    two solutions. It grew by larger amounts between each subsequent solution. So the next optimization was just to
    jump the denominator forward by multiplying it by multiple of the previous two solutions. This makes it so we only
    have to check a very small number of denominators before finding the next solution.

    I don't understand things like 'diophantine equations' or 'pell equation' enough to come up with an elegant solution.
    But it is nice that these hacky speedups are enough to find the solution quickly (~600ms on my machine).
 */
public class Problem100
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        double nextNumStartRatio = 0;
        double nextNumEndRatio = 1;

        double lastDen = 0;
        double lastDenMultiple = 0;

        outer:
        for (long den = 1; true; den++)
        {
            long start = (long) (den * nextNumStartRatio);
            if (start == 0) start = 1;
            long end = (long) (den * nextNumEndRatio) + 1;
            if (end > den) end = den;

            for (long num = start; num < end; num++)
            {
                if (2 * (num * (num - 1)) == den * (den - 1))
                {
                    double first = (double) num / den;
                    double second = (double) (num - 1) / (den - 1);

                    nextNumStartRatio = second;
                    nextNumEndRatio = first;

                    String message = String.format("%32s -> %.24f, %.24f", (num + " / " + den), first, second);
                    System.out.println(message);

                    if (den > 1e12)
                    {
                        System.out.println("answer: " + num);
                        break outer;
                    }

                    // skip over denominators that probably won't be useful
                    if (lastDen > 0)
                    {
                        lastDenMultiple = (double) den / lastDen;
                    }
                    lastDen = den;
                    den = lastDenMultiple == 0 ? den + 1 : (long) (lastDen * lastDenMultiple);

                    continue outer;
                }
            }
        }

        timer.printDuration();
    }
}
