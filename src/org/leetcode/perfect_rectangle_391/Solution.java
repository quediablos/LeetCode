package org.leetcode.perfect_rectangle_391;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution
{
    private static int MAX_POWER_OF_TWO = 15;

    private long areaGross = 0l;
    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = -1;
    int maxY = -1;

    public class Rectangle
    {
        public int lowX, lowY, highX, highY;

        public Rectangle(int lowX, int lowY, int highX, int highY)
        {
            this.lowX = lowX;
            this.lowY = lowY;
            this.highX = highX;
            this.highY = highY;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "lowX=" + lowX +
                    ", lowY=" + lowY +
                    ", highX=" + highX +
                    ", highY=" + highY +
                    '}';
        }
    }

    public class Point
    {
        public int x,y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public class Segment
    {
        public List<Rectangle> rectangles = new LinkedList<>();
        public Segment[] childSegments = new Segment[4];
        public Stack<Integer> key = new Stack<>();
        public Point center;
        public int degree;

        public Rectangle childSegmentRectangle(int child)
        {
            int edgeLength = powerOfTwo(MAX_POWER_OF_TWO-degree);
            if (child == 0)
                return new Rectangle(center.x,center.y,center.x+edgeLength,center.y+edgeLength);
            else if (child == 1)
                return new Rectangle(center.x-edgeLength,center.y,center.x,center.y+edgeLength);
            else if (child == 2)
                return new Rectangle(center.x-edgeLength,center.y-edgeLength,center.x,center.y);
            else
                return new Rectangle(center.x,center.y-edgeLength,center.x+edgeLength,center.y);

        }

        public Segment createChildSegment(int child)
        {
            Segment childSegment = new Segment();
            childSegment.degree = this.degree+1;
            this.childSegments[child] = childSegment;

            //center of child segment
            int childEdgeLength = powerOfTwo(MAX_POWER_OF_TWO-degree);

            if (child == 0)
                childSegment.center = new Point(this.center.x + childEdgeLength/2, this.center.y + childEdgeLength/2);
            else if (child == 1)
                childSegment.center = new Point(this.center.x - childEdgeLength/2, this.center.y+childEdgeLength/2);
            else if (child == 2)
                childSegment.center = new Point(this.center.x-childEdgeLength/2,this.center.y-childEdgeLength/2);
            else
                childSegment.center = new Point(this.center.x+childEdgeLength/2,this.center.y-childEdgeLength/2);


            return childSegment;
        }

    }



    private int powerOfTwo(int degree)
    {
        if (degree == 0)
            return 1;
        else if (degree == 1)
            return 2;

        int val = 2;

        for (int i=1; i<degree; i++)
            val *= 2;

        return val;
    }

    private boolean checkOverlap(Rectangle r1, Rectangle r2)
    {
        return (r1.lowX < r2.highX && r1.highX > r2.lowX && r1.highY > r2.lowY && r1.lowY < r2.highY);
    }


    private Boolean checkEdgeCases(int[][] rectangles)
    {
        if (rectangles.length == 0)
            return Boolean.FALSE;
        else if (rectangles.length == 1)
            return Boolean.TRUE;
        else
            return null;
    }

    private void updateEdgeValues(int lowX, int lowY, int highX, int highY)
    {
        if (lowX < minX)
            minX = lowX;

        if (lowY < minY)
            minY = lowY;

        if (highX > maxX)
            maxX = highX;

        if (highY > maxY)
            maxY = highY;
    }


    private boolean searchForOverlap(Segment rootSegment, Rectangle rectangle)
    {
        //first check in the rectangles of the root segment
        for (Rectangle rectangleInSegment : rootSegment.rectangles)
        {
            if (checkOverlap(rectangleInSegment,rectangle))
                return true;
        }

        //determine the child segments to go to.
        for (int child = 0; child <= 3; child++)
        {
            if (rootSegment.childSegments[child] != null &&
                checkOverlap(rootSegment.childSegmentRectangle(child),rectangle) &&
                searchForOverlap(rootSegment.childSegments[child],rectangle))
            {
                return true;
            }
        }

        return false;

    }

    private void insertRectangle(Segment rootSegment, Rectangle rectangle)
    {
        //no more segmentation
        if (rootSegment.degree == MAX_POWER_OF_TWO)
        {
            rootSegment.rectangles.add(rectangle);
            return;
        }
        //rectangle covers all child segments
        else if (rootSegment.center.x > rectangle.lowX && rootSegment.center.x < rectangle.highX &&
                 rootSegment.center.y > rectangle.lowY && rootSegment.center.y < rectangle.highY)
        {
            rootSegment.rectangles.add(rectangle);
            return;
        }
        //rectangle overlaps with one or two child segments
        else
        {
            int overlapCount = 0;
            int overlappingChild = -1;
            for (int child=0; child<=3; child++)
            {
                if (checkOverlap(rootSegment.childSegmentRectangle(child),rectangle))
                {
                    overlapCount++;
                    overlappingChild = child;
                }
            }

            if (overlapCount == 2)
            {
                rootSegment.rectangles.add(rectangle);
                return;
            }
            else
            {
                rootSegment.childSegments[overlappingChild] = rootSegment.childSegments[overlappingChild] != null ?
                        rootSegment.childSegments[overlappingChild] : rootSegment.createChildSegment(overlappingChild);

                insertRectangle(rootSegment.childSegments[overlappingChild],rectangle);
            }

        }

    }


    /**
     * Interface method
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles)
    {
        //Edge cases
        Boolean edgeCases = checkEdgeCases(rectangles);

        if (edgeCases != null)
            return edgeCases;

        Segment rootSegment = new Segment();
        rootSegment.degree = 0;
        rootSegment.center = new Point(0,0);

        for (int[] rectangle : rectangles)
        {
            Rectangle r = new Rectangle(rectangle[0],rectangle[1],rectangle[2],rectangle[3]);

            if (searchForOverlap(rootSegment,r))
                return false;

            insertRectangle(rootSegment,r);
            areaGross += (r.highX-r.lowX)*(r.highY-r.lowY);
            updateEdgeValues(rectangle[0],rectangle[1],rectangle[2],rectangle[3]);
        }

        //Compare the total area
        return (maxX-minX)*(maxY-minY) == areaGross;

    }
}
