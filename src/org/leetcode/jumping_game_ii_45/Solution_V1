package org.leetcode.jumping_game_ii_45;

public class Solution
{

    private int[] findDestinationRange(int[] nums, int offset)
    {
        int[] destinationRange = new int[2];

        destinationRange[0] = offset + 1;
        destinationRange[1] = Math.min(offset+nums[offset],nums.length-1);

        return destinationRange;
    }

    private int findBestNode(int[] nums, int beginNode, int endNode)
    {
        int bestScore = 0;
        int bestNode = -1;
        int score;

        //For each node possible, calculate their score based on their max length they promise + the distance
        // we make going to each one of them
        for (int i = beginNode; i <= endNode; i++)
        {
            score = nums[i] + i - beginNode + 1;
            if (score > bestScore)
            {
                bestScore = score;
                bestNode = i;
            }
        }

        return bestNode;
    }

    /**
     * Interface method
     * @param nums
     * @return
     */
    public int jump(int[] nums)
    {
        //Edge cases
        if (nums.length == 1)
            return 0;
        else if (nums.length == 2)
            return 1;

        int stepCount = 0;
        int currentNode = 0;

        while(true)
        {
            //Get the range of nodes we can jump to
            int[] destinationRange = findDestinationRange(nums,currentNode);

            //Reached the end.
            if (destinationRange[1] == nums.length-1)
                return stepCount+1;

            //Find the most distance promising node among the possible ones.
            currentNode = findBestNode(nums,destinationRange[0],destinationRange[1]);
            stepCount++;

        }
    }
}
