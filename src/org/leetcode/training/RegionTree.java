package org.leetcode.training;



public class RegionTree
{



    private boolean checkOverlap(int lowX1,int lowY1, int highX1, int highY1,
                                 int lowX2,int lowY2, int highX2, int highY2)
    {

        return (lowX1 < highX2 && highX1 > lowX2 && highY1 > lowY2 && lowY1 < highY2);
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

        if (root.node3 != null && root.node3.maxX >= node.lowX && root.node3.maxY >= node.lowY)
            return overlapSearch(root.node3,node);
        else if (root.node2 != null && root.node2.maxX >= node.lowX)
            return overlapSearch(root.node2,node);
        else if (root.node4 != null && root.node4.maxY >= node.lowY)
            return overlapSearch(root.node4,node);
        else
            return overlapSearch(root.node1,node);
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

        if (highX > root.maxX)
            root.maxX = highX;

        if (highY > root.maxY)
            root.maxY = highY;

        if (lowX < root.minX)
            root.minX = lowX;

        if (lowY < root.minY)
            root.minY = lowY;

        return root;

    }


}
