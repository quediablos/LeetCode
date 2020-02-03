package org.leetcode.training;

public class Node
{
    public int lowX,lowY,highX,highY;
    public int minX,minY,maxX,maxY;

    public Node node1,node2,node3,node4;

    public Node(int lowX,int lowY,int highX,int highY)
    {
        this.lowX = lowX;
        this.lowY = lowY;
        this.highX = highX;
        this.highY = highY;

        this.minX = lowX;
        this.minY = lowY;
        this.maxX = highX;
        this.maxY = highY;
    }
}
