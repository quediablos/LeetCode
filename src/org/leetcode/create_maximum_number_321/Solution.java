package org.leetcode.create_maximum_number_321;

import java.util.LinkedList;
import java.util.Queue;

public class Solution
{
    private static final int MAX_DIGIT = 9;
    public class Value
    {
        public int number;
        public int array;
        public int index1;
        public int index2;
        public Value from;
        public int degree;

        public Value(int number, int array, int index1, int index2, Value from, int degree) {
            this.number = number;
            this.array = array;
            this.index1 = index1;
            this.index2 = index2;
            this.from = from;
            this.degree = degree;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "number=" + number +
                    ", array=" + array +
                    ", index1=" + index1 +
                    ", index2=" + index2 +
                    ", from=" + from +
                    ", degree=" + degree +
                    '}';
        }
    }

    public class MutableInt
    {
        public int val;

        public MutableInt(int val)
        {
            this.val = val;
        }
    }



    private int[] makeNumber(Value value,int length)
    {
        Value v = value;
        int[] number = new int[length];

        for (int i=length-1; i>=0; i--)
        {
            number[i] = v.number;
            v = v.from;
        }

        return number;
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

        int index1 = -1;
        int index2 = -1;
        int remainingNumbers = k;
        int[] result = new int[k];
        int resultInd = 0;
        MutableInt allowedSkips = new MutableInt(nums1.length + nums2.length - k);

        while (remainingNumbers > 0)
        {

        }

        return result;

    }
}
