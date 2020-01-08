package org.leetcode.max_points_on_a_line_149;


import java.util.HashMap;


public class Solution {

    private static final int x = 0;
    private static final int y = 1;

    private HashMap<MapEntry,MapValue> lineSegments = new HashMap<>();
    private HashMap<Integer,Integer> repeatingPoints = new HashMap<>();

    private int hash2(int a, int b)
    {
        return 2001*(a+1000)+(b+1000);
    }


    public class MapEntry
    {
        public int startX,startY,dx,dy;

        public MapEntry(int startX, int startY, int dx, int dy) {
            this.startX = startX;
            this.startY = startY;
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public int hashCode() {

            return hash2(hash2(startX,startY),hash2(dx,dy));
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MapEntry))
                return false;

            MapEntry other = (MapEntry)obj;

            return startX == other.startX &&
                   startY == other.startY &&
                   dx == other.dx &&
                   dy == other.dy;

        }

        @Override
        public String toString() {
            return "" + startX + "," + startY + "- (" + dx + "," + dy + ")";
        }
    }

    public class MapValue
    {
        public int endX,endY;

        public MapValue(int endX, int endY) {
            this.endX = endX;
            this.endY = endY;
        }

        @Override
        public String toString() {
            return "" + endX + "," + endY;
        }
    }

    private void updatePointRepetition(int x, int y)
    {
        int hash = hash2(x,y);
        Integer repeatCount = repeatingPoints.get(hash);

        if (repeatCount != null)
            repeatingPoints.replace(hash,repeatCount+1);
        else
            repeatingPoints.put(hash,1);
    }

    private int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    private int[] simplifySlope(int dx,int dy)
    {
        int[] arr = new int[2];

        if (dx == 0 && dy == 0)
        {
            arr[x] = 0;
            arr[y] = 0;
        }
        else if (dx == 0)
        {
            arr[x] = 0;
            arr[y] = 1;
        }
        else if (dy == 0)
        {
            arr[x] = 1;
            arr[y] = 0;
        }
        else
        {
            int gcd = Math.abs(gcd(dx,dy));
            arr[x] = dx / gcd;
            arr[y] = dy / gcd;
        }

        return arr;
    }



    private void orderPoints(int [][] points)
    {
        //order by x
        for (int i=0; i<points.length-1; i++)
        {
            for (int j=0; j<points.length-i-1; j++)
            {
                if (points[j][x] > points[j+1][x])
                {
                    int valX = points[j][x];
                    int valY = points[j][y];

                    points[j][x] = points[j+1][x];
                    points[j][y] = points[j+1][y];

                    points[j+1][x] = valX;
                    points[j+1][y] = valY;
                }

            }
        }

        //order by y
        for (int i=0; i<points.length-1; i++)
        {
            for (int j=0; j<points.length-i-1; j++)
            {
                if (points[j][y] > points[j+1][y])
                {
                    int valX = points[j][x];
                    int valY = points[j][y];

                    points[j][x] = points[j+1][x];
                    points[j][y] = points[j+1][y];

                    points[j+1][x] = valX;
                    points[j+1][y] = valY;
                }

            }
        }
    }

    private int findLengthFrom(MapEntry entry)
    {
        int length = 1;
        MapEntry currentEntry = entry;
        MapValue currentValue;

        Integer repeatsOfEntry = repeatingPoints.get(hash2(currentEntry.startX,currentEntry.startY));
        length += repeatsOfEntry != null ? repeatsOfEntry-1: 0;

        while(true)
        {
            currentValue = lineSegments.get(currentEntry);

            if (currentValue!=null && !(currentValue.endX == currentEntry.startX && currentValue.endY == currentEntry.startY))
            {
                currentEntry = new MapEntry(currentValue.endX,currentValue.endY,currentEntry.dx,currentEntry.dy);
                length++;

                Integer repeatsOfValue = repeatingPoints.get(hash2(currentEntry.startX,currentEntry.startY));
                length += repeatsOfValue != null ? repeatsOfValue-1: 0;
            }
            else
            {
                break;
            }
        }

        return length;
    }

    private void countRepeatingPoints(int[][] points)
    {
        for (int i=0; i<points.length; i++)
        {
            updatePointRepetition(points[i][x],points[i][y]);
        }
    }

    public int maxPoints(int[][] points) {

        if (points.length <= 2)
            return points.length;

        countRepeatingPoints(points);

        orderPoints(points);

        //Generate line segments of length two
        for (int i=0; i<points.length; i++)
        {
            for (int k=i+1; k<points.length; k++)
            {
                int[] simplifiedSlope = simplifySlope(points[k][x]-points[i][x],points[k][y]-points[i][y]);

                MapEntry entry = new MapEntry(points[i][x],points[i][y],simplifiedSlope[x],simplifiedSlope[y]);
                MapValue valueExisting = lineSegments.get(entry);

                if (valueExisting != null)
                {
                    int distanceManhattanExisting = Math.abs(valueExisting.endY - points[i][y]) + Math.abs(valueExisting.endX - points[i][x]);
                    int distanceManhattanNew = Math.abs(points[k][y] - points[i][y]) + Math.abs(points[k][x] - points[i][x]);

                    if (distanceManhattanNew < distanceManhattanExisting)
                        lineSegments.replace(entry,new MapValue(points[k][x],points[k][y]));
                }
                else
                {
                    lineSegments.put(entry,new MapValue(points[k][x],points[k][y]));
                }
            }

        }

        int maxLength = 2;

        //Now generate longer line segments using the previous ones.
        for(MapEntry entry: lineSegments.keySet())
        {
            int length = findLengthFrom(entry);

            if (length > maxLength)
                maxLength = length;
        }

        return maxLength;
    }
}
