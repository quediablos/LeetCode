package org.leetcode.create_maximum_number_321;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest
{
    /*********************** Own cases ************************/

    @Test
    public void testOwn_1()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {8,9,1,2,3,4,5,6,7};
        int[] nums1 = new int[] {1,2,3,4,5,6,7};
        int[] nums2 = new int[] {8,9};
        int k = 9;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    @Test
    public void testOwn_3()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {9,9,7,7};
        int[] nums1 = new int[] {8,9,7,3};
        int[] nums2 = new int[] {8,9,7,3};
        int k = 4;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    @Test
    public void testOwn_2()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {8,7};
        int[] nums1 = new int[] {7};
        int[] nums2 = new int[] {8};
        int k = 2;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    @Test
    public void testOwn_4()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {0,1,7,0,1,6};
        int[] nums1 = new int[] {0,1,7};
        int[] nums2 = new int[] {0,1,6};
        int k = 6;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

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


    @Test
    public void testLC_3()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {9,8,9};
        int[] nums1 = new int[] {3,9};
        int[] nums2 = new int[] {8,9};
        int k = 3;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    @Test
    public void testLC_89()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {9,8,9};
        int[] nums1 = new int[] {8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5};
        int[] nums2 = new int[] {8,9,7,3,5,9,1,0,8,5,3,0,9,2,7,4,8,9,8,1,0,2,0,2,7,2,3,5,4,7,4,1,4,0,1,4,2,1,3,1,5,3,9,3,9,0,1,7,0,6,1,8,5,6,6,5,0,4,7,2,9,2,2,7,6,2,9,2,3,5,7,4,7,0,1,8,3,6,6,3,0,8,5,3,0,3,7,3,0,9,8,5,1,9,5,0,7,9,6,8,5,1,9,6,5,8,2,3,7,1,0,1,4,3,4,4,2,4,0,8,4,6,5,5,7,6,9,0,8,4,6,1,6,7,2,0,1,1,8,2,6,4,0,5,5,2,6,1,6,4,7,1,7,2,2,9,8,9,1,0,5,5,9,7,7,8,8,3,3,8,9,3,7,5,3,6,1,0,1,0,9,3,7,8,4,0,3,5,8,1,0,5,7,2,8,4,9,5,6,8,1,1,8,7,3,2,3,4,8,7,9,9,7,8,5,2,2,7,1,9,1,5,5,1,3,5,9,0,5,2,9,4,2,8,7,3,9,4,7,4,8,7,5,0,9,9,7,9,3,8,0,9,5,3,0,0,3,0,4,9,0,9,1,6,0,2,0,5,2,2,6,0,0,9,6,3,4,1,2,0,8,3,6,6,9,0,2,1,6,9,2,4,9,0,8,3,9,0,5,4,5,4,6,1,2,5,2,2,1,7,3,8,1,1,6,8,8,1,8,5,6,1,3,0,1,3,5,6,5,0,6,4,2,8,6,0,3,7,9,5,5,9,8,0,4,8,6,0,8,6,6,1,6,2,7,1,0,2,2,4,0,0,0,4,6,5,5,4,0,1,5,8,3,2,0,9,7,6,2,6,9,9,9,7,1,4,6,2,8,2,5,3,4,5,2,4,4,4,7,2,2,5,3,2,8,2,2,4,9,8,0,9,8,7,6,2,6,7,5,4,7,5,1,0,5,7,8,7,7,8,9,7,0,3,7,7,4,7,2,0,4,1,1,9,1,7,5,0,5,6,6,1,0,6,9,4,2,8,0,5,1,9,8,4,0,3,1,2,4,2,1,8,9,5,9,6,5,3,1,8,9,0,9,8,3,0,9,4,1,1,6,0,5,9,0,8,3,7,8,5};
        int k = 500;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    /**
     * [8,9]
     * [3,9]
     * 3
     */
    @Test
    public void testLC_64()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {9,8,9};
        int[] nums1 = new int[] {8,9};
        int[] nums2 = new int[] {3,9};
        int k = 3;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    @Test
    public void testLC_59()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {8,8,8,5,4 };
        int[] nums1 = new int[] {3,8,5,3,4};
        int[] nums2 = new int[] {8,7,3,6,8};
        int k = 5;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }


    @Test
    public void testLC_65()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {7,3,8,2,5,6,4,4,0,6,5,7,6,2,0};
        int[] nums1 = new int[] {2,5,6,4,4,0};
        int[] nums2 = new int[] {7,3,8,0,6,5,7,6,2};
        int k = 15;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

    @Test
    public void testLC_80()
    {
        Solution solution = new Solution();
        int[] expectedResult = new int[] {2,6,2,0,1,0,5,4,5,5,3,3,3,4};
        int[] nums1 = new int[] {2,1,7,8,0,1,7,3,5,8,9,0,0,7,0,2,2,7,3,5,5};
        int[] nums2 = new int[] {2,6,2,0,1,0,5,4,5,5,3,3,3,4};
        int k = 35;

        Assert.assertArrayEquals(expectedResult,solution.maxNumber(nums1,nums2,k));
    }

}
