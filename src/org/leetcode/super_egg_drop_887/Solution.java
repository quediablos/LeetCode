package org.leetcode.super_egg_drop_887;


public class Solution {

    public int superEggDrop(int K, int N)
    {
        if (N==1)
            return 1;

        int rangeBegin = 0;
        int rangeEnd = N;
        int moves = 0;

        while (rangeBegin != N)
        {
            rangeBegin = (int)Math.floor(((double) rangeBegin + (double) rangeEnd) / 2.0 + 1.0);
            moves++;
        }

        return moves+1;

    }
}
