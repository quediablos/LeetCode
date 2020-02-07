package org.leetcode.create_maximum_number_321;

import java.util.LinkedList;
import java.util.List;

public class Solution
{
    public class Value
    {
        public int number;
        public int array;
        public int index;

        public Value(int number, int array, int index)
        {
            this.number = number;
            this.array = array;
            this.index = index;
        }

        @Override
        public String toString()
        {
            return "Value{" +
                    "number=" + number +
                    ", array=" + array +
                    ", index=" + index +
                    '}';
        }
    }

    public class Node
    {
        public Value value;
        public Node right;
        public Node left;

        public Node(Value value)
        {
            this.value = value;
        }
    }

    public Node insertToTree(Node root,Value value)
    {
        if (root == null)
            return new Node(value);
        else if (value.number >= root.value.number)
            root.right = insertToTree(root.right,value);
        else
            root.left = insertToTree(root.left,value);

        return root;
    }

    private List<Value> sortArrays(int[] nums1, int[] nums2)
    {
        Node root = null;

        for (int i=0; i<nums1.length; i++)
            root = insertToTree(root,new Value(nums1[i],1,i));

        for (int i=0; i<nums2.length; i++)
            root = insertToTree(root,new Value(nums2[i],2,i));

        List<Value> sortedList = new LinkedList<>();

        treeToArray(root,sortedList);

        return sortedList;

    }

    private void treeToArray(Node root,List<Value> list)
    {
        if (root == null)
            return;

        treeToArray(root.right,list);

        list.add(root.value);

        treeToArray(root.left,list);
    }

    /**
     * Interface method
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k)
    {
        //TODO:edge cases

        List<Value> sortedList = sortArrays(nums1,nums2);

        int index1 = -1;
        int index2 = -1;
        int[] resultArray = new int[k];
        int resultArrayInd = 0;
        int remainingNumbersInResult = k;

        for (Value value : sortedList)
        {
            int remainingNumbersAtArray1,remainingNumbersAtArray2;

            if (remainingNumbersInResult == 0)
                break;

            //taking from array 1
            if (value.array == 1 && index1 < value.index)
            {
                remainingNumbersAtArray1 = nums1.length - value.index - 1;
                remainingNumbersAtArray2 = nums2.length - index1 - 1;

                if (remainingNumbersAtArray1 + remainingNumbersAtArray2 >= remainingNumbersInResult-1)
                {
                    resultArray[resultArrayInd++] = value.number;
                    index1 = value.index;
                    remainingNumbersInResult--;
                }
            }
            //taking from array 2
            else if (value.array == 2 && index2 < value.index)
            {
                remainingNumbersAtArray1 = nums1.length - index1 - 1;
                remainingNumbersAtArray2 = nums2.length - value.index - 1;

                if (remainingNumbersAtArray1 + remainingNumbersAtArray2 >= remainingNumbersInResult-1)
                {
                    resultArray[resultArrayInd++] = value.number;
                    index2 = value.index;
                    remainingNumbersInResult--;
                }
            }
        }

        return resultArray;
    }
}
