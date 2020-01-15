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

}
