package org.leetcode.divide_two_integers_29;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SolutionTest {

    /******************* Custom cases ********************************/

    @Test
    public void test_Custom_1()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(114567,-2) == -57283);
    }

    @Test
    public void test_Custom_2()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(0,-2) == 0);
    }

    @Test
    public void test_Custom_3()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(1000,-2) == -500);
    }

    @Test
    public void test_Custom_4()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(1001,2) == 500);
    }

    @Test
    public void test_Custom_5()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(10011,2) == 5005);
    }

    @Test
    public void test_Custom_6()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(1_000,2) == 500);
    }

    @Test
    public void test_Custom()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(2147483647,-1) == -2147483647);
    }

    /************************* Leet code cases **********************/

    @Test
    public void test_1()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(10,3) == 3);
    }

    @Test
    public void test_2()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(7,-3) == -2);
    }

    @Test
    public void test_5()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(-1,1) == -1);
    }

    @Test
    public void test_17()
    {

        Solution solution = new Solution();
        assertTrue(solution.divide(-2147483648,-1) == 2147483647);
    }

    @Test
    public void test_18()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(2_147_483_647,2) == 1_073_741_823);
    }

    @Test
    public void test_19()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(-2_147_483_648,2) == -1_073_741_824);
    }

    @Test
    public void test_21()
    {

        Solution solution = new Solution();
        assertTrue(solution.divide(-1_010_369_383,-2_147_483_648) == 0);
    }

    @Test
    public void test_571()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(-1_060_849_722,99_958_928) == -10);
    }


}
