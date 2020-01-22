package org.leetcode.falling_squares_699;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SolutionTest
{

    private void assertResult(List<Integer> result, int[] expectedResult)
    {
        for (int i=0; i<result.size(); i++)
        {
            System.out.println(i + " - " + "expected:" + expectedResult[i] + " returned:" + result.get(i));
            assertTrue(result.get(i) == expectedResult[i]);
        }
    }
    /********************** Custom cases **********************/

    @Test
    public void testCustom_1()
    {
        Solution solution = new Solution();
        int [][] positions = new int[][] {{0,1},{1,1},{2,1},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},{9,1},{10,1}};
        int [] expectedResult = new int[] {1,1,1,1,1,1,1,1,1,1,1};

        List<Integer> result = solution.fallingSquares(positions);
        assertResult(result,expectedResult);
    }

    @Test
    public void testCustom_2()
    {
        Solution solution = new Solution();
        int [][] positions = new int[][] {{2,1},{5,1},{2,5},{7,1},{7,1},{7,3}};
        int [] expectedResult = new int[] {1,1,6,6,6,6};

        List<Integer> result = solution.fallingSquares(positions);
        assertResult(result,expectedResult);
    }

    /********************* Leetcode cases *********************/
    @Test
    public void test_1()
    {
        Solution solution = new Solution();
        int [][] positions = new int[][] {{1,2},{2,3},{6,1}};
        int [] expectedResult = new int[] {2,5,5};

        List<Integer> result = solution.fallingSquares(positions);

        assertResult(result,expectedResult);

    }

    @Test
    public void test_2()
    {
        Solution solution = new Solution();
        int [][] positions = new int[][] {{100,100},{200,100}};
        int [] expectedResult = new int[] {100,100};

        List<Integer> result = solution.fallingSquares(positions);

        assertResult(result,expectedResult);

    }

}
