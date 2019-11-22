package net.ehicks.euler;/*
Comparing two numbers written in index form like 2^11 and 3^7 is not difficult,
as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.

However, confirming that 632382^518061 > 519432^525806 would be much more difficult,
as both numbers contain over three million digits.

Using base_exp.txt (right click and 'Save Link/Target As...'),
a 22K text file containing one thousand lines with a base/exponent pair on each line,
determine which line number has the greatest numerical value.

NOTE: The first two lines in the file represent the numbers in the example given above.

NOTE 2: I looked up hints for this one!
 */

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Problem099
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();

        List<String> lines = Files.readAllLines(Paths.get("web/euler/Problem99exp.txt"));
        BigDecimal greatestValue = BigDecimal.ZERO;
        int lineWithGreatestValue = 0;

        int index = 1;
        for (String line : lines)
        {
            String[] parts = line.split(",");
            int base = Integer.parseInt(parts[0]);
            String exponent = parts[1];

            BigDecimal value = new BigDecimal(exponent).multiply(new BigDecimal(Math.log(base)));

            if (value.compareTo(greatestValue) > 0)
            {
                greatestValue = value;
                lineWithGreatestValue = index;
                System.out.println("greatest value: " + greatestValue);
            }
            index++;
        }

        System.out.println("Determine which line number has the greatest numerical value: " + lineWithGreatestValue + " (" + greatestValue + ")");
        timer.printDuration();
    }

}
