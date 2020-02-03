package org.leetcode.perfect_rectangle_391;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest
{

    /*************************** Sub tests *********************/


    /***************************** Own cases *************************/

    @Test
    public void testOwn_1()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{1,1,3,3},{3,1,4,2},{200,200,300,300}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testOwn_2()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{-10,0,+10,1},{20,0,100,1}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testOwn_3()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{-100,0,+100,1},{100,0,102,1}};
        assertTrue(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testOwn_4()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{-3,-1,-1,1},{-1,-1,1,1},{-3,1,-1,3},{-1,1,1,3}};
        assertTrue(solution.isRectangleCover(rectangles));
    }

    /***************************** LC cases *****************************/

    @Test
    public void testLC_1()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}};
        assertTrue(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testLC_2()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{1,1,2,3},{1,3,2,4},{3,1,4,2},{3,2,4,4}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testLC_3()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{1,1,3,3},{3,1,4,2},{1,3,2,4},{3,2,4,4}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testLC_4()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{1,1,3,3},{3,1,4,2},{1,3,2,4},{2,2,4,4}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testLC_34()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{0,0,4,1},{0,0,4,1}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testLC_41()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{0,0,4,1},{7,0,8,2},{5,1,6,3},{6,0,7,2},{4,0,5,1},{4,2,5,3},{2,1,4,3},{-1,2,2,3},{0,1,2,2},{6,2,8,3},{5,0,6,1},{4,1,5,2}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void testLC_42()
    {

        Solution solution = new Solution();
        int[][] rectangles = new int[10000][4];

        //{0,-1,1,0}-{0,9998,1,9999}
        for (int i = 0; i<=9999; i++)
        {
            int[] rectangle = new int[4];
            rectangle[0] = 0;
            rectangle[1] = i-1;
            rectangle[2] = 1;
            rectangle[3] = i;
            rectangles[i] = rectangle;
        }

        assertTrue(solution.isRectangleCover(rectangles));
    }


    @Test
    public void testLC_44()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[BigInput_44_0.input.length+ BigInput_44_1.input.length+ BigInput_44_2.input.length][4];

        for(int i = 0; i<BigInput_44_0.input.length + BigInput_44_1.input.length+ BigInput_44_2.input.length; i++)
        {
            if (i < BigInput_44_0.input.length)
                rectangles[i] = BigInput_44_0.input[i];
            else if (i < BigInput_44_0.input.length+BigInput_44_1.input.length)
                rectangles[i] = BigInput_44_1.input[i-BigInput_44_0.input.length];
            else
                rectangles[i] = BigInput_44_2.input[i-BigInput_44_0.input.length-BigInput_44_1.input.length];
        }
        assertTrue(solution.isRectangleCover(rectangles));
    }
}
