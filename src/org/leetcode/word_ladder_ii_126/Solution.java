package org.leetcode.word_ladder_ii_126;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

    private HashMap<String,List<String>> neighbours = new HashMap();
    private int minLength = 9999999;
    private HashMap<String,Integer> distances = new HashMap<>();

    public class Node
    {
        public Node parent;
        public String word;
        public ArrayList<Node> branches;

        public Node(String word,Node parent)
        {
            this.word = word;
            this.parent = parent;
        }
        public Node(String word,Node parent, List<String> branchWords)
        {
            this.word = word;
            this.parent = parent;

            branches = new ArrayList<>();
            for (String branchWord: branchWords)
            {
                branches.add(new Node(branchWord,this));
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "word='" + word + '\'' +
                    '}';
        }
    }

    public boolean canTransormTo(String source, String destination)
    {
        int transformedChars = 0;
        for (int i=0; i<destination.length(); i++)
        {
            if (source.charAt(i) != destination.charAt(i) && ++transformedChars > 1)
                return false;
        }

        return true;
    }

    private void addNeighbour(String word, String neigbour)
    {
        List<String> neighboursOfWord = neighbours.get(word);

        if (neighboursOfWord == null)
            neighbours.put(word,new ArrayList<>());

        neighbours.get(word).add(neigbour);
    }

    private void generateNeighbours(List<String> wordList)
    {
        for (int i=0; i<wordList.size()-1; i++)
        {
            for (int k=i+1; k<wordList.size(); k++)
            {
                String word = wordList.get(i);
                String neighbour = wordList.get(k);

                if (canTransormTo(word,neighbour))
                {
                    addNeighbour(word,neighbour);
                    addNeighbour(neighbour,word);

                }

            }
        }
    }

    private int distance(String word1, String word2)
    {
        int distance = 0;
        for (int i=0; i<word2.length(); i++)
        {
            if (word1.charAt(i) != word2.charAt(i))
                distance++;
        }
        return distance;
    }


    private String getPathView(LinkedList<String> path)
    {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = path.iterator();

        while(iterator.hasNext())
        {
            stringBuilder.append(iterator.next()).append("-");
        }

        return stringBuilder.toString();
    }

    private ArrayList<Node> getBranchNodes(Node from)
    {
        List<String> availableNeighbours = neighbours.get(from.word);
        ArrayList<Node> branchNodes = new ArrayList<>();

        if (availableNeighbours == null)
            return null;

        for (String branchWord: availableNeighbours)
        {
            branchNodes.add(new Node(branchWord,from));
        }
        return branchNodes;
    }


    private int getChangedIndex(String word1, String word2)
    {
        for (int i=0; i<word1.length(); i++)
        {
            if (word1.charAt(i) != word2.charAt(i))
                return i;
        }

        return -1;
    }

    private LinkedList<String> generatePath(Node node, int order)
    {
        LinkedList<String> path = null;
        if (node.parent == null)
        {
            path = new LinkedList<>();

            if (order == 1)
                path.addFirst(node.word);
            else
                path.addLast(node.word);
            return path;
        }
        else
        {
            path = generatePath(node.parent,order);

            if (order == 1)
                path.addFirst(node.word);
            else
                path.addLast(node.word);

            return path;
        }

    }

    private int getDepth(Node node)
    {
        if (node.parent == null)
            return 1;
        else
            return getDepth(node.parent) + 1;
    }

    private int hashOfPath(List<String> path)
    {
        StringBuilder stringBuilder = new StringBuilder();

        Iterator<String> iterator = path.iterator();
        while (iterator.hasNext())
        {
            stringBuilder.append(iterator.next()).append("-");
        }
        return stringBuilder.toString().hashCode();
    }



    private boolean checkForIntersection(List<Node> branchList1, List<Node> branchList2)
    {
        for (Node node1: branchList1)
        {
            for (Node node2: branchList2)
            {
                if (node1.word.equals(node2.word))
                    return true;
            }
        }

        return false;
    }

    private void mergeSecondPath(LinkedList<String> pathFirst, LinkedList<String> pathSecond)
    {
        Iterator<String> iterator = pathSecond.iterator();

        while (iterator.hasNext())
        {
            pathFirst.addLast(iterator.next());
        }
    }


    private List<List<String>> findPathsViaIntersections(List<Node> visitedNodesFromStart,List<Node> visitedNodesFromEnd )
    {
        List<List<String>> pathsFound = new ArrayList<>();

        //Active queues
        Iterator<Node> iteratorFromStart = visitedNodesFromStart.iterator();

        while(iteratorFromStart.hasNext())
        {
            Node nodeFromStart = iteratorFromStart.next();

            Iterator<Node> iteratorFromEnd = visitedNodesFromEnd.iterator();

            while (iteratorFromEnd.hasNext())
            {
                Node nodeFromEnd = iteratorFromEnd.next();

                //intersection
                if (nodeFromStart.word.equals(nodeFromEnd.word))
                {
                    LinkedList<String> pathFromStart = generatePath(nodeFromStart,1);
                    LinkedList<String> pathFromEnd = generatePath(nodeFromEnd.parent,-1);

                    //merge paths
                    mergeSecondPath(pathFromEnd,pathFromStart);
                    pathsFound.add(pathFromStart);
                }
            }
        }

        return pathsFound;
    }

    /**
     * Not finished yet
     * @param beginWord
     * @param endWord
     * @return
     */
    private List<List<String>> findPathsDualBfs(String beginWord, String endWord)
    {
        Queue<Node> queueFromStart = new LinkedList<>();
        LinkedList<Integer> levelsFromStart = new LinkedList<>();
        Queue<Node> queueFromEnd = new LinkedList<>();
        LinkedList<Integer> levelsFromEnd = new LinkedList<>();
        Set<String> openWordsFromStart = new HashSet<>();
        Set<String> openWordsFromEnd = new HashSet<>();
        List<List<String>> pathsFound = new ArrayList<>();
        Node rootStart = new Node(beginWord,null);
        Node rootEnd = new Node(endWord,null);
        LinkedList<Node> visitedNodesFromStart = new LinkedList<>();
        LinkedList<Node> visitedNodesFromEnd = new LinkedList<>();


        queueFromStart.add(rootStart);
        queueFromEnd.add(rootEnd);
        levelsFromStart.add(1);
        levelsFromEnd.add(1);

        int minPathLength = 999999;
        boolean finish = false;

        while (!queueFromStart.isEmpty() && !queueFromEnd.isEmpty())
        {

            Node currentNodeFromStart = queueFromStart.peek();
            Node currentNodeFromEnd = queueFromEnd.peek();

            openWordsFromStart.add(currentNodeFromStart.word);
            openWordsFromEnd.add(currentNodeFromEnd.word);

            //Find the branches
            ArrayList<Node> branchNodesFromStart = getBranchNodes(currentNodeFromStart);
            ArrayList<Node> branchNodesFromEnd = getBranchNodes(currentNodeFromEnd);
            currentNodeFromStart.branches = new ArrayList<>();
            currentNodeFromEnd.branches = new ArrayList<>();

            //Check if the current level is finished.
            int currentLevel = levelsFromStart.getFirst() + 1;

            //Current level finished.
            if (/*levelsFromStart.getFirst().equals(levelsFromStart.getLast()) ||
                levelsFromEnd.getFirst().equals(levelsFromEnd.getLast())*/true)
            {
                List<List<String>> paths1 = findPathsViaIntersections((List)queueFromStart,(List)queueFromEnd);
                //List<List<String>> paths2 = findPathsViaIntersections(branchNodesFromEnd,(List)queueFromStart);
                if (!paths1.isEmpty())
                {

                    return paths1;
                }
            }

            //Branches from current from start
            if (branchNodesFromStart != null)
            {
                for (Node branch : branchNodesFromStart)
                {

                    //Check if the node was visited the same way before
                    if (!openWordsFromStart.contains(branch.word))
                    {
                        //TODO:do not add terminal nodes to the queue.
                        queueFromStart.add(branch);
                        levelsFromStart.addLast(currentLevel);
                        //currentNodeFromStart.branches.add(branch);
                        visitedNodesFromStart.addLast(branch);

                    }

                }
            }

            if (branchNodesFromEnd != null)
            {
                for (Node branch : branchNodesFromEnd)
                {
                    //Check if the node was visited the same way before
                    if (!openWordsFromEnd.contains(branch.word))
                    {
                        queueFromEnd.add(branch);
                        levelsFromEnd.addLast(currentLevel);
                        //currentNodeFromEnd.branches.add(branch);
                        visitedNodesFromEnd.addLast(branch);
                    }

                }
            }

            queueFromStart.poll();
            queueFromEnd.poll();
            levelsFromStart.poll();
            levelsFromEnd.poll();

            //Check for paths via intersections
            //pathsFound.addAll(findPathsViaIntersections(visitedNodesFromStart,visitedNodesFromEnd));

        }

    return pathsFound;

    }

    private List<List<String>> findPathsBFS(String beginWord, String endWord)
    {
        Queue<Node> queue = new LinkedList<>();
        Set<String> openWords = new HashSet<>();
        List<List<String>> pathsFound = new ArrayList<>();
        Node root = new Node(beginWord,null);
        queue.add(root);
        int minPathLength = 999999;

        while (!queue.isEmpty())
        {

            Node currentNode = queue.poll();
            openWords.add(currentNode.word);
            ArrayList<Node> branchNodes = getBranchNodes(currentNode);
            currentNode.branches = new ArrayList<>();

            if (branchNodes == null)
                continue;

            if (!pathsFound.isEmpty() && getDepth(currentNode) + 1 > minPathLength)
                break;

            for (Node branch : branchNodes)
            {
                //Check if the node was visited the same way before
                if (!openWords.contains(branch.word))
                {
                    queue.add(branch);

                    if (branch.word.equals(endWord))
                    {
                        LinkedList<String> path = generatePath(branch,-1);

                        pathsFound.add(path);

                        minPathLength = path.size() < minPathLength ? path.size() : minPathLength;
                    }
                }

            }

        }

        return pathsFound;
    }


    /**
     * Main method
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(beginWord))
            wordList.add(beginWord);

        generateNeighbours(wordList);

        return findPathsBFS(beginWord,endWord);
        //return findPathsDualBfs(beginWord,endWord);

    }

}
