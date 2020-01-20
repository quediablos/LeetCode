package org.leetcode.falling_squares_699;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution
{
    private static final int WIDTH = 1;
    private static final int X = 0;

    private Map<Integer,List<Range>> heightMap = new HashMap<>();
    private int maxHeight = 0;

    public enum OverlapType
    {
        COVERS,
        SAME,
        IS_COVERED,
        WITH_INTERSECTIONS,
        NONE
    }

    public class Range
    {
        public int start;
        public int end;

        public Range(int start,int end)
        {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString()
        {
            return "Range{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }


    }

    public OverlapType checkOverlap(int start, int end, Range range)
    {
        if (start < range.start && end > range.end)
            return OverlapType.COVERS;
        else if (start == range.start && end == range.end)
            return OverlapType.SAME;
        else if (start > range.start && end < range.end)
            return OverlapType.IS_COVERED;
        else if (end <= range.start || start >= range.end)
            return OverlapType.NONE;
        else
            return OverlapType.WITH_INTERSECTIONS;
    }

    public Range[] insert(Range range, int midStart, int midEnd)
    {
        Range[] rangesNew = new Range[3];
        rangesNew[0] = new Range(range.start,midStart);
        rangesNew[1] = new Range(midStart,midEnd);
        rangesNew[2] = new Range(midEnd,range.end);
        return rangesNew;
    }

    private int placeSquare(int pos, int width)
    {
        if (maxHeight == 0)
            return 0;

        boolean heightLevelFound = false;

        for (int heightLevel = 1; heightLevel <= maxHeight; heightLevel++)
        {
            List<Range> ranges = heightMap.get(heightLevel);

            //Search if the new square overlaps with any height levels.
            for (int i=0; i<ranges.size(); i++)
            {
                OverlapType overlapType = checkOverlap(pos,pos+width,ranges.get(i));

                if (!overlapType.equals(OverlapType.NONE))
                {
                    heightLevelFound = true;

                    List<Range> rangesUpper = heightMap.get(heightLevel + width) != null ? heightMap.get(heightLevel + width) : new LinkedList<>();

                    rangesUpper.add(new Range(pos,pos+width));
                }
            }

            if (heightLevelFound)
                return heightLevel;
        }

        return 0;
    }

    /**
     * Method to be called
     * @param positions
     * @return
     */
    public List<Integer> fallingSquares(int[][] positions)
    {

        for (int i=0; i<positions.length; i++)
        {
            //Find where the new square will sit and place it.
            int heightOfNewSquare = placeSquare(positions[i][X],positions[i][WIDTH]);

            if (heightOfNewSquare > maxHeight)
                maxHeight = heightOfNewSquare;

        }


        return null;

    }
}
