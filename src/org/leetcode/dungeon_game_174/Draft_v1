package org.leetcode.dungeon_game_174;


import java.util.LinkedList;

public class Solution
{
    public class Node
    {
        public int point;
        public Node parent;
        public int r;
        public int c;

        public Node(int point, Node parent, int r, int c)
        {
            this.point = point;
            this.parent = parent;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString()
        {
            return "Node{" +
                    "point=" + point +
                    ", r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    private Node[] findNeighbours(int[][] dungeon, Node node)
    {
        int rowLength = dungeon[0].length;
        int colLength = dungeon.length;

        Node[] neighbours = new Node[2];
        if (node.c < rowLength-1)
            neighbours[0] = new Node(dungeon[node.r][node.c+1],node,node.r,node.c+1);
        else
            neighbours[0] = null;

        if (node.r < colLength-1)
            neighbours[1] = new Node(dungeon[node.r+1][node.c],node,node.r+1,node.c);
        else
            neighbours[1] = null;

        if (neighbours[0] == null && neighbours[1] == null)
            return null;
        else
            return neighbours;

    }

    private int calculateMinHpForPath(Node node)
    {
        int hpRequired = 1;
        int hp = 1;
        Node current = node;
        LinkedList<Integer> points = new LinkedList<>();

        do
        {
           points.addFirst(current.point);
        }
        while ((current = current.parent) != null);

        for (Integer point: points)
        {
            int pointAbs = Math.abs(point);
            if (point < 0 && pointAbs >= hp)
            {
                hpRequired += pointAbs - hp + 1;
                hp = 1;
            }
            else
            {
                hp += point;
            }
        }

        return hpRequired;
    }


    /**
     * Interface method
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon)
    {
        int minHp = Integer.MAX_VALUE;
        LinkedList<Node> openNodes = new LinkedList<>();
        Node start = new Node(dungeon[0][0],null,0,0);
        openNodes.addLast(start);

        while (!openNodes.isEmpty())
        {
            Node node = openNodes.pollLast();
            Node[] neighbours = findNeighbours(dungeon,node);

            //Finish
            if (neighbours == null)
            {
                //Check min hp.
                int hpRequired = calculateMinHpForPath(node);

                if (hpRequired < minHp)
                    minHp = hpRequired;
            }
            else
            {
                if (neighbours[0] != null)
                    openNodes.addLast(neighbours[0]);
                if (neighbours[1] != null)
                    openNodes.addLast(neighbours[1]);
            }

        }

        return minHp;
    }
}
