package org.leetcode.perfect_rectangle_391;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    @Test
    public void testOwn_5()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{0,0,1,1},{1000,1000,1001,1001},{-1000,-1000,-999,-999},{-100,-100,100,100},{-1,-1,1,1}};
        assertFalse(solution.isRectangleCover(rectangles));
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
    public void test_43()
    {
        Solution solution = new Solution();
        int[][] rectangles = new int[][] {{1,1,2,2},{0,1,1,2},{1,0,2,1},{0,2,3,3},{2,0,3,3}};
        assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    public void test_46() throws IOException
    {
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("InputOfLC46")));

        int[][] rectangles = new int[10779][4];
        int i=0;

        for (String line; (line = txtReader.readLine()) != null;)
        {
            String[] lineSplit = line.split(",");
            rectangles[i][0] = Integer.parseInt(lineSplit[0]);
            rectangles[i][1] = Integer.parseInt(lineSplit[1]);
            rectangles[i][2] = Integer.parseInt(lineSplit[2]);
            rectangles[i][3] = Integer.parseInt(lineSplit[3]);
            i++;
        }

        Solution solution = new Solution();
        assertFalse(solution.isRectangleCover(rectangles));
    }
}
