package org.leetcode.max_points_on_a_line_149;


import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void test_1()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{2,2},{4,1},{4,4},{5,5}}) == 3);
    }

    @Test
    public void test_2()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}}) == 4);
    }

    @Test
    public void test_3()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{0,0},{1,1},{0,0}}) == 3);
    }

    @Test
    public void test_4()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{0,0},{1,1},{0,0},{1,1},{2,2}}) == 5);
    }

    @Test
    public void test_31()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{0,0},{-1,-1},{2,2}}) == 3);
    }

    @Test
    public void test_33()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{2,3},{3,3},{-5,3}}) == 3);
    }

    @Test
    public void test_22()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{1,1},{1,1},{1,1}}) == 3);
    }

    @Test
    public void test_23()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{0,-1},{0,3},{0,-4},{0,-2},{0,-4},{0,0},{0,0},{0,1},{0,-2},{0,4}}) == 10);
    }

    @Test
    public void test_A1()
    {
        Assert.assertTrue((new Solution()).maxPoints(new int[][]{{0,0},{0,0},{0,1}}) == 3);
    }
}
