package org.leetcode.dungeon_game_174;


import java.util.Iterator;
import java.util.LinkedList;

public class Solution
{
    public class Node
    {
        public int point;
        public Node parent;
        public int r;
        public int c;
        public int hpRequiredToPass;
        public int hpAfterPassing;
        public int depth;

        public Node(int point, Node parent, int r, int c, int hpRequiredToPass, int hpAfterPassing, int depth) {
            this.point = point;
            this.parent = parent;
            this.r = r;
            this.c = c;
            this.hpRequiredToPass = hpRequiredToPass;
            this.hpAfterPassing = hpAfterPassing;
            this.depth = depth;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "point=" + point +
                    ", r=" + r +
                    ", c=" + c +
                    ", hpRequiredToPass=" + hpRequiredToPass +
                    ", hpAfterPassing=" + hpAfterPassing +
                    ", depth=" + depth +
                    '}';
        }
    }

    private Node[] findNeighbours(int[][] dungeon, Node node)
    {
        int rowLength = dungeon[0].length;
        int colLength = dungeon.length;
        int[] hpValues;
        Node[] neighbours = new Node[2];

        //right
        if (node.c < rowLength-1)
        {
            hpValues = calculateHpForPassing(node.hpAfterPassing,node.hpRequiredToPass,dungeon[node.r][node.c+1]);
            neighbours[1] = new Node(dungeon[node.r][node.c+1],node,node.r,node.c+1,hpValues[0],hpValues[1],node.depth+1);
        }
        else
            neighbours[1] = null;

        //down
        if (node.r < colLength-1)
        {
            hpValues = calculateHpForPassing(node.hpAfterPassing,node.hpRequiredToPass,dungeon[node.r+1][node.c]);
            neighbours[0] = new Node(dungeon[node.r+1][node.c],node,node.r+1,node.c,hpValues[0],hpValues[1],node.depth+1);
        }

        else
            neighbours[0] = null;

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

    private int[] calculateHpForPassing(int hp, int hpRequired, int point)
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

        return new int[] {hpRequired,hp};
    }

    private int compareNodes(Node node1, Node node2)
    {
        if (node1.hpRequiredToPass < node2.hpRequiredToPass)
            return 1;
        else if (node2.hpRequiredToPass < node1.hpRequiredToPass)
            return 2;
        else if (node1.hpAfterPassing > node2.hpAfterPassing)
            return 1;
        else if (node2.hpAfterPassing < node1.hpAfterPassing)
            return 2;
        else
            return 1;

    }

    private LinkedList<Node> backtrack(LinkedList<Node> openNodes)
    {
        if (openNodes.size() <= 2)
            return openNodes;

        LinkedList<Node> postOpenNodes = new LinkedList<>();
        Iterator<Node> iterator = openNodes.iterator();
        Node previous = iterator.next();
        Node current = iterator.next();
        boolean first = true;

        do
        {
            if (!first)
            {
                previous = current;
                current = iterator.next();
            }

            if (previous.r == current.r && previous.c == current.c)
            {
                int compare = compareNodes(previous,current);

                if (compare == 1)
                    postOpenNodes.add(previous);
                else
                    postOpenNodes.add(current);
            }
            else
            {
                if (first)
                    postOpenNodes.add(previous);

                if (!iterator.hasNext())
                    postOpenNodes.add(current);
            }

            first = false;

        }
        while (iterator.hasNext());

        if (postOpenNodes.size() > 2)
            return postOpenNodes;
        else
            return openNodes;

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
        int[] hpValues = calculateHpForPassing(1,1,dungeon[0][0]);
        Node start = new Node(dungeon[0][0],null,0,0,hpValues[0],hpValues[1],0);
        openNodes.addLast(start);

        while (!openNodes.isEmpty())
        {
            Node node = openNodes.pollFirst();
            Node[] neighbours = findNeighbours(dungeon,node);

            //Finish
            if (neighbours == null)
            {
                //Check min hp.
                if (node.hpRequiredToPass < minHp)
                    minHp = node.hpRequiredToPass;
            }
            else
            {
                if (neighbours[0] != null)
                    openNodes.addLast(neighbours[0]);
                if (neighbours[1] != null)
                    openNodes.addLast(neighbours[1]);

            }

            //Backtrack
            if (openNodes.size() >= 2 && openNodes.getFirst().depth == openNodes.getLast().depth)
            {
                openNodes = backtrack(openNodes);
            }

        }

        return minHp;
    }
}
