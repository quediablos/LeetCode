package org.leetcode.word_ladder_ii_126;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    private HashMap<String,List<String>> neighbours = new HashMap();
    private int minLength = 9999999;
    private HashSet<String> deadEnds = new HashSet<>();
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

       /* @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof Node))
                return false;

            Node other = (Node)obj;

            return other.word.equals(word) &&
        }*/
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

    private void calculateDistances(List<String> wordList, String endWord)
    {
        for (String word: wordList)
        {
            distances.put(word,distance(word,endWord));
        }
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

    private List<String> getNeighbours(String from)
    {
        List<String> availableNeighbours = neighbours.get(from);

        if (availableNeighbours == null)
            return null;


        //Include the neighbours with min distance
        int distanceMin = 9999999;

        for (int i=0; i<availableNeighbours.size(); i++)
        {
            if (distances.get(availableNeighbours.get(i)) < distanceMin)
                distanceMin = distances.get(availableNeighbours.get(i));
        }

        for (int i=0; i<availableNeighbours.size(); i++)
        {
            if (distances.get(availableNeighbours.get(i)) > distanceMin)
                availableNeighbours.remove(i);
        }
        return availableNeighbours;

    }

    /*private ArrayList<LinkedList<String>> forkPath( HashMap<String,ArrayList<LinkedList<String>>> paths,
                                                    String endNode, ArrayList<String> childNodes)
    {

    }*/

    private void findPathsBFS(String beginWord, String endWord)
    {
        Queue<Node> queue = new LinkedList<>();
        HashSet<String> visitedWords = new HashSet<>();
        HashMap<String,ArrayList<LinkedList<String>>> paths = new HashMap<>();
        Node root = new Node(beginWord,null);
        queue.add(root);
        Node parentNode = null;

        while (!queue.isEmpty())
        {
            Node currentNode = queue.poll();
            ArrayList<Node> branchNodes = getBranchNodes(currentNode);
            currentNode.branches = branchNodes;
            parentNode = currentNode;

            for (Node branch : branchNodes)
            {
                if (!visitedWords.contains(branch.word))
                {
                    queue.add(branch);

                    if (branch.word.equals(endWord))
                    {
                        int x=0;

                        //System.out.println(getPathView(path));

                    }
                    else
                    {
                        visitedWords.add(branch.word);
                    }

                }
            }

        }

    }




    private List<List<String>> findPathsDFS(String from, String destination, LinkedList<String> pathPrevious)
    {
        List<String> availableNeighbours = getNeighbours(from);
        List<List<String>> pathsFromMe = new ArrayList<>();

        //Path found
        if (from.equals(destination))
        {
            if (pathPrevious == null)
            {
                LinkedList path = new LinkedList();
                path.add(from);
                path.add(destination);
                pathsFromMe.add(path);
                return pathsFromMe;
            }
            else
            {
                pathPrevious.addLast(from);

                if (pathPrevious.size() < minLength)
                    minLength = pathPrevious.size();

                pathsFromMe.add((List)pathPrevious.clone());
                return pathsFromMe;
            }
        }
        //Dead end
        else if (availableNeighbours == null)
        {
            return pathsFromMe;
        }
        //Continue
        else
        {
            if (pathPrevious == null)
                pathPrevious = new LinkedList<>();

            pathPrevious.add(from);

            for (String neighbour : availableNeighbours)
            {

                if (pathPrevious != null && pathPrevious.contains(neighbour))
                    continue;

                else if (pathPrevious.size() == minLength)
                    break;


                List<List<String>> pathsFromNeighbour = findPathsDFS(neighbour,destination,pathPrevious);

                for (List<String> pathFromNeighbour : pathsFromNeighbour)
                {
                    pathsFromMe.add(pathFromNeighbour);
                }
                if (pathPrevious.size() != 0)
                    pathPrevious.removeLast();
            }

            return pathsFromMe;
        }

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

        calculateDistances(wordList,endWord);

        /*List<List<String>> allPaths = findPathsDFS(beginWord,endWord,null);

        List<List<String>> shortestPaths = new ArrayList<>();

        for (List<String> path: allPaths )
        {
            if (path.size() <= minLength)
                shortestPaths.add(path);
        }

        return shortestPaths;*/

        findPathsBFS(beginWord,endWord);

        return null;

    }

}
