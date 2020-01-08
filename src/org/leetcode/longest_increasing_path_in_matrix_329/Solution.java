package org.leetcode.longest_increasing_path_in_matrix_329;



import java.util.ArrayList;
import java.util.HashMap;

public class Solution
{
    private HashMap<Node,Integer> nodeRanks = new HashMap<>();
    private HashMap<Node,ArrayList<Path>> pathsAll = new HashMap<>();
    private int lengthOfLongestPath = 0;

    private class Node
    {
        public int row, col,val;
        public ArrayList<Path> availablePaths;

        public Node(int row, int col, int val)
        {
            this.row = row;
            this.col = col;
            this.val = val;

            availablePaths = new ArrayList<>();
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof Node)
            {
                Node other = (Node)obj;
                if (this.row == other.row && this.col == other.col)
                    return true;
            }

            return false;
        }

        @Override
        public String toString()
        {
            return ""+val;
        }

        @Override
        public int hashCode()
        {
            return ((row+col)*(row+col+1)/2)+col;
        }
    }

    private class Path
    {
        public ArrayList<Node> nodes;

        public Path()
        {
            nodes = new ArrayList<>();
        }

        public Path(Node node1, Node node2)
        {
            nodes = new ArrayList<>();
            nodes.add(node1);
            nodes.add(node2);
        }

        public Node getEnd()
        {
            return nodes.get(nodes.size()-1);
        }

        public Node getStart()
        {
            return nodes.get(0);
        }

        public Path merge(Path pathSecond)
        {
            Path pathMerged = new Path();
            pathMerged.nodes.addAll(nodes);

            for (int i=1; i<pathSecond.nodes.size(); i++)
                pathMerged.nodes.add(pathSecond.nodes.get(i));

            return pathMerged;
        }

        public boolean startsWith(Path pathToStartWith)
        {
            for (int i=0; i<pathToStartWith.nodes.size(); i++)
            {
                if (!pathToStartWith.nodes.get(i).equals(this.nodes.get(i)))
                    return false;
            }

            return true;
        }

        @Override
        public String toString()
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (Node node:nodes)
                stringBuilder.append(node.val).append("-");

            return stringBuilder.toString();
        }
    }

    private void updateNodeRank(Node node, int rankNew)
    {
        Integer rankExisting = nodeRanks.get(node);

        if (rankExisting == null)
            nodeRanks.put(node,rankNew);
        else if (rankExisting < rankNew)
            nodeRanks.replace(node, rankNew);
    }

    public ArrayList<Path> findPathsToNeighbours(int[][] matrix, Node node)
    {
        int width = matrix[0].length;
        int height = matrix.length;

        ArrayList<Path> paths = new ArrayList<>();
        int valNode = matrix[node.row][node.col];

        if (node.col > 0 && matrix[node.row][node.col-1] > valNode)
            paths.add(new Path(node,new Node(node.row, node.col-1, matrix[node.row][node.col-1])));

        if (node.col < width-1 && matrix[node.row][node.col+1] > valNode)
            paths.add(new Path(node,new Node(node.row, node.col+1, matrix[node.row][node.col+1])));

        if (node.row > 0 && matrix[node.row-1][node.col] > valNode)
            paths.add(new Path(node,new Node(node.row-1, node.col, matrix[node.row-1][node.col])));

        if (node.row < height-1 && matrix[node.row+1][node.col] > valNode)
            paths.add(new Path(node,new Node(node.row+1, node.col, matrix[node.row+1][node.col])));

        return paths;
    }

    private void findPathsFromNode(int[][] matrix, Path from, Node node)
    {
        ArrayList<Path> paths = new ArrayList<>();

        ArrayList<Path> pathsToNeighbours = findPathsToNeighbours(matrix,node);
        paths.addAll(findPathsToNeighbours(matrix,node));

        if (pathsToNeighbours.size() == 0)
            return;

        if (from != null)
        {
            ArrayList<Path> pathsMerged = new ArrayList<>();

            for (Path pathToNeighbour : pathsToNeighbours)
                pathsMerged.add(from.merge(pathToNeighbour));

            //Replace the paths that start with "from"
            ArrayList<Path> pathsOfRoot = new ArrayList<>();
            pathsOfRoot.addAll(pathsMerged);
            pathsOfRoot.addAll(pathsAll.get(from.getStart()));

            pathsAll.replace(from.getStart(), pathsOfRoot);

        }
        else
        {
            pathsAll.put(node, pathsToNeighbours);

            int rankOfNode = nodeRanks.get(node) != null ? nodeRanks.get(node) : 0;

            if (rankOfNode >= 1)
                return;
        }

        for (Path pathToNeighbour : pathsToNeighbours)
        {

            Path current = from == null ? pathToNeighbour : from.merge(pathToNeighbour);
            //Update node ranks
            updateNodeRank(pathToNeighbour.getEnd(),current.nodes.size());

            int rankOfNeighbour = nodeRanks.get(current.getEnd()) != null ? nodeRanks.get(current.getEnd()) : 0;

            if (current.nodes.size() >= rankOfNeighbour)
                findPathsFromNode(matrix, from == null ? pathToNeighbour : from.merge(pathToNeighbour), pathToNeighbour.getEnd());
        }

    }

    /**
     * Finds the longest increasing path in the given matrix
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix)
    {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        pathsAll.clear();
        lengthOfLongestPath = 0;

        for (int r=0; r<matrix.length; r++)
        {
            int[] row = matrix[r];

            for (int c=0; c<row.length; c++)
            {
                Node node = new Node(r,c,matrix[r][c]);

                findPathsFromNode(matrix,null, node);
            }
        }

        for (Node node: pathsAll.keySet())
        {
            for (Path path: pathsAll.get(node))
            {
                if (path.nodes.size() > lengthOfLongestPath)
                    lengthOfLongestPath = path.nodes.size();
            }
        }

        return lengthOfLongestPath != 0 ? lengthOfLongestPath : 1;
    }


}
