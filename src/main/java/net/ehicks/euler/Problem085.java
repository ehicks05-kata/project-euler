package net.ehicks.euler;

/*
By counting carefully it can be seen that a rectangular grid measuring 3 by 2 contains eighteen rectangles:
(picture showing 6 1x1 rectangles, 4 2x1 rects, 2 3x1 rects, 3 1x2 rects, 2 2x2 rects and 1 3x2 rect)
Although there exists no rectangular grid that contains exactly two million rectangles, find the area of the grid with the nearest solution.
* */

public class Problem085
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        int targetRectangles = 2_000_000;
        int bestMatchGridDifference = Integer.MAX_VALUE;
        int bestMatchGridWidth = 0;
        int bestMatchGridHeight = 0;
        int bestMatchGridRectangles = 0;

        outer:
        for (int gridWidth = 1; gridWidth <= 3000; gridWidth++)
        {
            for (int gridHeight = 1; gridHeight <= gridWidth; gridHeight++)
            {
                int gridRectangles = 0;

                for (int w = 1; w <= gridWidth; w++)
                {
                    for (int h = 1; h <= gridHeight; h++)
                    {
                        // how many rectangles of h by w can fit in a grid of gridWidth by gridHeight?
                        int horizontalInstances = gridWidth - w + 1;
                        int verticalInstances = gridHeight - h + 1;

                        gridRectangles += horizontalInstances * verticalInstances;
                    }
                }

                int difference = Math.abs(targetRectangles - gridRectangles);
                if (difference < bestMatchGridDifference)
                {
                    bestMatchGridDifference = difference;
                    bestMatchGridWidth = gridWidth;
                    bestMatchGridHeight = gridHeight;
                    bestMatchGridRectangles = gridRectangles;
                }

                if (gridRectangles > targetRectangles)
                {
                    if (gridHeight == 1)
                        break outer;
                    
                    continue outer;
                }
            }
        }

        System.out.println(bestMatchGridWidth + " by " + bestMatchGridHeight + ", area: " + (bestMatchGridWidth * bestMatchGridHeight) + ", rectangles: " + bestMatchGridRectangles);
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
