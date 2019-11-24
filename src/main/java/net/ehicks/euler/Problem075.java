package net.ehicks.euler;

/*
It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way,
but there are many more examples.

12 cm: (3,4,5)
24 cm: (6,8,10)
30 cm: (5,12,13)
36 cm: (9,12,15)
40 cm: (8,15,17)
48 cm: (12,16,20)

In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle,
and other lengths allow more than one solution to be found;
for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.

120 cm: (30,40,50), (20,48,52), (24,45,51)

Given that L is the length of the wire, for how many values of L ? 1,500,000 can exactly one integer sided right angle triangle be formed?
* */

import java.util.*;

public class Problem075
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        Map<Integer, Set<String>> lengthToTriangles = new HashMap<>();

        // using Euclid's formula for generating Pythagorean triples
        outer:
        for (int k = 1; true; k++)
        {
            m:
            for (int m = 2; true; m++)
            {
                for (int n = 1; n < m; n++)
                {
                    int a = k * (m * m - n * n);
                    int b = k * (2 * m * n);
                    int c = k * (m * m + n * n);

                    int wireLength = a + b + c;
                    if (wireLength > 1_500_000)
                    {
                        if (m == 2)
                            break outer;

                        if (n == 1)
                            break m;

                        break;
                    }

                    String description = Math.min(a, b) + ", " + Math.max(a, b) + ", " + c;
                    lengthToTriangles.merge(wireLength, new HashSet<>(Collections.singletonList(description)), (strings, strings2) -> {
                        Set<String> temp = new HashSet<>();
                        temp.addAll(strings);
                        temp.addAll(strings2);
                        return temp;
                    });
                }
            }
        }

        lengthToTriangles.entrySet().removeIf(integerListEntry -> integerListEntry.getValue().size() > 1);
        System.out.println(lengthToTriangles.size());
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
