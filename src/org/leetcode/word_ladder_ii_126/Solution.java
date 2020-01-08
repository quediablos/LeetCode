package org.leetcode.word_ladder_ii_126;


public class Solution {

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
}
