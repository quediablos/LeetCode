package org.leetcode.create_maximum_number_321;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest
{
    /*********************** Own cases ************************/


    /************************ LC cases *************************/

    @Test
    public void testLC_1()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {9, 8, 6, 5, 3};
        int[] nums1 = new int[] {3, 4, 6, 5};
        int[] nums2 = new int[] {9, 1, 2, 5, 8, 3};
        int k = 5;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    @Test
    public void testLC_2()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {6, 7, 6, 0, 4};
        int[] nums1 = new int[] {6,7};
        int[] nums2 = new int[] {6,0,4};
        int k = 5;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }
}
