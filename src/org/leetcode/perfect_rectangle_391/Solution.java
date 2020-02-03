package org.leetcode.perfect_rectangle_391;

import java.util.HashMap;
import java.util.Map;

public class Solution
{
    private Map<Integer,Map<Integer,Long>> areas = new HashMap<>();
    private long areaGross = 0l;

    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = -1;
    int maxY = -1;

    public class Node
    {
        public int x1,y1,x2,y2;
        public Node n1,n2,n3,n4;

        public Node(int x1, int y1, int x2, int y2)
        {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }


    private void updateEdgeValues(int x1,int y1,int x2,int y2)
    {
        if (x1 < minX)
            minX = x1;
        if (y1 < minY)
            minY = y1;
        if (x2 > maxX)
            maxX = x2;
        if (y2 > maxY)
            maxY = y2;
    }

    private void saveRectangle(Node root,int x1,int y1,int x2,int y2)
    {
        Node node = new Node(x1, y1, x2, y2);
        if (root == null)
        {
            root = node;
            return;
        }
        if (x1 >= root.x1 && y1 >= root.y1)
            saveRectangle(root.n1,x1,y1,x2,y2);
        else if (x1 >= root.x1 && y1 < root.y1)
            saveRectangle(root.n4,x1,y1,x2,y2);
        else if (x1 < root.x1 && y1 >= root.y1)
            saveRectangle(root.n2,x1,y1,x2,y2);
        else if (x1 < root.x1 && y1 < root.y1)
            saveRectangle(root.n3,x1,y1,x2,y2);
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


        return false;

    }
}
