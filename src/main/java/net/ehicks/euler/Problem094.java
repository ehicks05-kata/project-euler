package net.ehicks.euler;

/*
It is easily proved that no equilateral triangle exists with integral length sides and integral area.
However, the almost equilateral triangle 5-5-6 has an area of 12 square units.

We shall define an almost equilateral triangle to be a triangle for which two sides are equal and
the third differs by no more than one unit.

Find the sum of the perimeters of all almost equilateral triangles with integral side lengths and area and
whose perimeters do not exceed one billion (1,000,000,000).
* */

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Problem094
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        long sum = 0;
        BigDecimal limit = new BigDecimal(1_000_000_000);

        outer:
        for (int i = 2; true; i++)
        {
            for (int thirdSide = i - 1; thirdSide < i + 2; thirdSide += 2)
            {
                BigDecimal p = new BigDecimal(i + i + thirdSide).divide(new BigDecimal(2), RoundingMode.HALF_UP);

                if (p.compareTo(limit) >= 0)
                    break outer;

                // Heron's formula
                BigDecimal t1 = p.subtract(new BigDecimal(i));
                BigDecimal t2 = p.subtract(new BigDecimal(i));
                BigDecimal t3 = p.subtract(new BigDecimal(thirdSide));
                BigDecimal area = p.multiply(t1).multiply(t2).multiply(t3).sqrt(MathContext.DECIMAL128);
                if (area.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO))
                {
                    String message = String.format("%32s, p: %12s, a: %24s", i + "-" + i + "-" + thirdSide, p, area);
                    System.out.println(message);
                    sum += p.longValue();
                }
            }
        }

        System.out.println(sum);
        timer.printDuration();
    }
}
