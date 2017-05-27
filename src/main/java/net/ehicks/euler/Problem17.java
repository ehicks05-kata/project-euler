package net.ehicks.euler;

/*If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?*/
public class Problem17
{
    public static void main(String[] args)
    {
        String[] str = new String[]{"zero", "one",    "two",    "three",    "four",     "five",    "six",     "seven",     "eight",    "nine",     "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty",
                "thirty", "forty",  "fifty",    "sixty",    "seventy", "eighty",  "ninety",    "hundred",  "thousand", "and"};
        int sum = 0;
        boolean hasOnes = false;
        boolean hasTens = false;
        boolean hasHundreds = false;

        for(int hundreds = 0; hundreds < 10; hundreds++)
        {
            for(int tens = 0; tens < 10; tens++)
            {
                for(int ones = 0; ones < 10; ones++)
                {
                    if(ones > 0)
                        hasOnes = true;
                    if(tens > 0)
                        hasTens = true;
                    if(hundreds > 0)
                        hasHundreds = true;

                    if(hundreds == 0)
                        hasHundreds = false;
                    if(tens == 0)
                        hasTens = false;
                    if(ones == 0)
                        hasOnes = false;

                    //n hundred
                    if(hasHundreds)
                    {
                        System.out.print(str[hundreds]+" "+str[28]+" ");
                        sum += str[hundreds].length();
                        sum += str[28].length();
                    }
                    //and
                    if(hasHundreds && (hasTens || hasOnes))
                    {
                        System.out.print(str[30]+" ");
                        sum += str[30].length();
                    }
                    //10-19
                    if(tens == 1)
                    {
                        System.out.print(str[tens+ones+9]);
                        sum += str[tens+ones+9].length();
                    }
                    //twenty-ninety
                    if(tens > 1)
                    {
                        System.out.print(str[tens+18]);
                        sum += str[tens+18].length();
                    }
                    //hyphen
                    if(tens > 1 && hasOnes)
                    {
                        System.out.print("-");
                    }
                    //all ones except 10-19 range
                    if(hasOnes && (tens != 1))
                    {
                        System.out.print(str[ones]);
                        sum += str[ones].length();
                    }
                    System.out.print("\n");
                }
            }
        }
        System.out.println(str[1]+" "+str[29]);
        sum += (str[1].length() + str[29].length());
        System.out.println("Total characters = "+sum);
    }
}