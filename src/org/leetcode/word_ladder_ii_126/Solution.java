package org.leetcode.word_ladder_ii_126;


import java.util.*;

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

    private int getChangedIndex(String word1, String word2)
    {
        for (int i=0; i<word1.length(); i++)
        {
            if (word1.charAt(i) != word2.charAt(i))
                return i;
        }

        return -1;
    }

    private LinkedList<String> generatePath(Node node)
    {
        LinkedList<String> path = null;
        if (node.parent == null)
        {
            path = new LinkedList<>();
            path.addFirst(node.word);
            return path;
        }
        else
        {
            path = generatePath(node.parent);
            path.addFirst(node.word);
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

    private void registerVisit(Map<String,Set<Integer>> visits, String word, List<String> path)
    {
        if (visits.containsKey(word))
        {
            int hashOfPath = hashOfPath(path);
            visits.get(word).add(hashOfPath);
        }
        else
        {
            Set<Integer> set = new HashSet<>();
            set.add(hashOfPath(path));
            visits.put(word,set);
        }
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

    private List<List<String>> findPathsDualBfs(String beginWord, String endWord)
    {
        Queue<Node> queueFromStart = new LinkedList<>();
        Queue<Node> queueFromEnd = new LinkedList<>();
        Set<String> openWordsFromStart = new HashSet<>();
        Set<String> openWordsFromEnd = new HashSet<>();
        List<List<String>> pathsFound = new ArrayList<>();
        Node rootStart = new Node(beginWord,null);
        Node rootEnd = new Node(endWord,null);

        queueFromStart.add(rootStart);
        queueFromEnd.add(rootEnd);
        int minPathLength = 999999;
        boolean finish = false;

        //Traversal from begin word
        while (!queueFromStart.isEmpty() && !queueFromEnd.isEmpty())
        {

            Node currentNodeFromStart = queueFromStart.poll();
            Node currentNodeFromEnd = queueFromEnd.poll();

            openWordsFromStart.add(currentNodeFromStart.word);
            openWordsFromEnd.add(currentNodeFromEnd.word);

            ArrayList<Node> branchNodesFromStart = getBranchNodes(currentNodeFromStart);
            ArrayList<Node> branchNodesFromEnd = getBranchNodes(currentNodeFromEnd);
            currentNodeFromStart.branches = branchNodesFromStart;
            currentNodeFromEnd.branches = branchNodesFromEnd;

            //Check for intersections from both trees.
            if (checkForIntersection(branchNodesFromStart,branchNodesFromEnd) ||
                (currentNodeFromEnd.parent != null && checkForIntersection(branchNodesFromStart,currentNodeFromEnd.parent.branches)))
            {
                int x=0;
            }

            //Branches from current from start
            if (branchNodesFromStart != null)
            {
                for (Node branch : branchNodesFromStart)
                {
                    //Check for change in the same index
                    if (currentNodeFromStart.parent != null)
                    {
                        int ind1 = getChangedIndex(branch.word,currentNodeFromStart.word);
                        int ind0 = getChangedIndex(currentNodeFromStart.word,currentNodeFromStart.parent.word);

                        if (ind0 == ind1)
                            continue;
                    }

                    //Check if the node was visited the same way before
                    if (!queueFromStart.contains(branch.word) && !openWordsFromStart.contains(branch.word))
                    {
                        queueFromStart.add(branch);

                    }

                }
            }

            if (branchNodesFromEnd != null)
            {
                for (Node branch : branchNodesFromEnd)
                {
                    //Check for change in the same index
                    if (currentNodeFromEnd.parent != null)
                    {
                        int ind1 = getChangedIndex(branch.word,currentNodeFromEnd.word);
                        int ind0 = getChangedIndex(currentNodeFromEnd.word,currentNodeFromEnd.parent.word);

                        if (ind0 == ind1)
                            continue;
                    }

                    //Check if the node was visited the same way before
                    if (!queueFromEnd.contains(branch.word) && !openWordsFromEnd.contains(branch.word))
                    {
                        queueFromEnd.add(branch);

                    }

                }
            }


        }

    return pathsFound;

    }

    private List<List<String>> findPathsBFS(String beginWord, String endWord)
    {
        Queue<Node> queue = new LinkedList<>();
        Set<String> openWords = new HashSet<>();
        List<List<String>> pathsFound = new ArrayList<>();
        Node root = new Node(endWord,null);
        queue.add(root);
        Node parentNode = null;
        int minPathLength = 999999;
        boolean finish = false;

        while (!queue.isEmpty())
        {
            if (finish)
                break;

            Node currentNode = queue.poll();
            openWords.add(currentNode.word);
            ArrayList<Node> branchNodes = getBranchNodes(currentNode);
            currentNode.branches = branchNodes;
            parentNode = currentNode;

            if (branchNodes == null)
                continue;

            if (!pathsFound.isEmpty() && getDepth(currentNode) + 1 > minPathLength)
                break;

            for (Node branch : branchNodes)
            {
                //Check for change in the same index
                if (currentNode.parent != null)
                {
                    int ind1 = getChangedIndex(branch.word,currentNode.word);
                    int ind0 = getChangedIndex(currentNode.word,currentNode.parent.word);

                    if (ind0 == ind1)
                        continue;
                }
                LinkedList<String> path = generatePath(branch);
                System.out.println(getPathView(path));
                //Check if the node was visited the same way before
                if (!queue.contains(branch.word) && !openWords.contains(branch.word))
                {
                    queue.add(branch);

                    if (branch.word.equals(beginWord))
                    {
                        //LinkedList<String> path = generatePath(branch);

                        pathsFound.add(path);

                        minPathLength = path.size() < minPathLength ? path.size() : minPathLength;
                    }
                }

            }

        }

        //Eliminate longer paths
        /*List<List<String>> pathsFinal = new ArrayList<>();
        for (List<String> path: pathsFound)
        {
            if (path.size() <= minPathLength)
                pathsFinal.add(path);
        }
        return pathsFinal;*/
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

        return findPathsDualBfs(beginWord,endWord);

    }

}
