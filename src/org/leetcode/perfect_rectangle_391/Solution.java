package org.leetcode.perfect_rectangle_391;


public class Solution
{

    private long areaGross = 0l;

    int minX = Integer.MAX_VALUE;
    int minY = Integer.MAX_VALUE;
    int maxX = -1;
    int maxY = -1;
    private Node root = null;
    private Node root1 = null;
    private Node root2 = null;
    private Node root3 = null;
    private Node root4 = null;

    public class Node
    {
        public int lowX, lowY, highX, highY;
        public int minX, minY, maxX, maxY;
        public int[] tip1;
        public int[] tip2;
        public int[] tip3;
        public int[] tip4;

        public Node node1, node2, node3, node4;

        public Node(int lowX, int lowY, int highX, int highY) {
            this.lowX = lowX;
            this.lowY = lowY;
            this.highX = highX;
            this.highY = highY;

            tip1 = new int[2];
            tip1[0] = highX;
            tip1[1] = highY;

            tip2 = new int[2];
            tip2[0] = lowX;
            tip2[1] = highY;

            tip3 = new int[2];
            tip3[0] = lowX;
            tip3[1] = lowY;

            tip4 = new int[2];
            tip4[0] = highX;
            tip4[1] = lowY;

        }

        @Override
        public String toString() {
            return "Node{" +
                    "lowX=" + lowX +
                    ", lowY=" + lowY +
                    ", minX=" + minX +
                    ", minY=" + minY +
                    ", maxX=" + maxX +
                    ", maxY=" + maxY +
                    '}';
        }
    }

    private boolean checkOverlap(Node node1, Node node2)
    {

        return (node1.lowX < node2.highX && node1.highX > node2.lowX && node1.highY > node2.lowY && node1.lowY < node2.highY);
    }


    public boolean overlapSearch(Node root, Node node)
    {
        if (root == null)
            return false;

        if (checkOverlap(root,node))
            return true;

        //at 1
        if (node.lowX >= root.lowX && node.lowY >= root.lowY)
        {
            if (root.node2 != null && root.node2.tip1[0] > node.lowX && root.node2.tip1[1] > node.lowY)
                return true;
            else if (root.node3 != null && root.node3.tip1[0] > node.lowX && root.node3.tip1[1] > node.lowY)
                return true;
            else if (root.node4 != null && root.node4.tip1[0] > node.lowX && root.node4.tip1[1] > node.lowY)
                return true;
            else
                return overlapSearch(root.node1,node);
        }
        //at 2
        else if (node.lowX < root.lowX && node.lowY >= root.lowY)
        {
            if (root.node1 != null && root.node1.tip2[0] < node.highX && root.node1.tip2[1] > node.lowY)
                return true;
            else if (root.node3 != null && root.node3.tip1[0] > node.lowX && root.node3.tip1[1] > node.lowY)
                return true;
            else if (root.node4 != null && root.node4.tip2[0] < node.highX && root.node4.tip2[1] > node.lowY)
                return true;
            else overlapSearch(root.node2,node);
        }
        //at 3
        else if (node.lowX < root.lowX && node.lowY < root.lowY)
        {
            if (root.node1 != null && root.node1.tip3[0] < node.lowX && root.node1.tip3[1] < node.lowY)
                return true;
            else if (root.node2 != null && root.node2.tip3[0] < node.highX && root.node2.tip3[1] < node.lowY)
                return true;
            else if (root.node4 != null && root.node4.tip2[0] < node.highX && root.node4.tip2[1] > node.lowY)
                return true;
            else
                overlapSearch(root.node3,node);
        }
        //at 4
        else
        {
            if (root.node1 != null && root.node1.tip4[0] > node.lowX && root.node1.tip4[1] < node.lowY)
                return true;
            else if (root.node2 != null && root.node2.tip4[0] > node.lowX && root.node2.tip4[1] < node.lowY)
                return true;
            else if (root.node3 != null && root.node3.tip1[0] > node.lowX && root.node3.tip1[1] > node.lowY)
                return true;
            else
                return overlapSearch(root.node4,node);
        }

        return false;
    }

    public Node insert(Node root,int lowX,int lowY,int highX,int highY)
    {
        if (root == null)
            return new Node(lowX,lowY,highX,highY);

        if (lowX < root.lowX)
        {
            if (lowY < root.lowY)
                root.node3 = insert(root.node3,lowX,lowY,highX,highY);
            else
                root.node2 = insert(root.node2,lowX,lowY,highX,highY);
        }
        else
        {
            if (lowY < root.lowY)
                root.node4 = insert(root.node4,lowX,lowY,highX,highY);
            else
                root.node1 = insert(root.node1,lowX,lowY,highX,highY);
        }

        if (highX > root.tip1[0] && highY > root.tip1[1])
        {
            root.tip1[0] = highX;
            root.tip1[1] = highY;
        }
        else if (lowX < root.tip2[0] && highY > root.tip2[1])
        {
            root.tip2[0] = lowX;
            root.tip2[1] = highY;
        }
        else if (lowX < root.tip3[0] && lowY < root.tip3[1])
        {
            root.tip3[0] = lowX;
            root.tip3[1] = lowY;
        }
        else if (highX < root.tip4[0] && lowY < root.tip4[1])
        {
            root.tip4[0] = highX;
            root.tip4[1] = lowY;
        }

        return root;
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

    private boolean overlapSearchBruteForce(int[][] rectangles)
    {
        for (int i=0; i<rectangles.length; i++)
        {
            Node node1 = new Node(rectangles[i][0],rectangles[i][1],rectangles[i][2],rectangles[i][3]);
            for (int k=0; k<rectangles.length; k++)
            {
                if (i != k)
                {
                    Node node2 = new Node(rectangles[k][0],rectangles[k][1],rectangles[k][2],rectangles[k][3]);
                    if (checkOverlap(node1,node2))
                        return true;
                }
            }
        }

        return false;
    }

    private boolean saveRectangle(int lowX, int lowY, int highX, int highY)
    {
        Node node = new Node(lowX,lowY,highX,highY);

        if (overlapSearch(root,node))
        {
            return false;
        }
        else
        {
            root = insert(root,lowX,lowY,highX,highY);
            areaGross += (highX-lowX)*(highY-lowY);
        }


        return true;

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

        for (int[] rectangle : rectangles)
        {
            if (!saveRectangle(rectangle[0],rectangle[1],rectangle[2],rectangle[3]))
                return false;

            updateEdgeValues(rectangle[0],rectangle[1],rectangle[2],rectangle[3]);
        }

        //boolean overlap = overlapSearchBruteForce(rectangles);

        //Compare the total area
        return (maxX-minX)*(maxY-minY) == areaGross;

    }
}
