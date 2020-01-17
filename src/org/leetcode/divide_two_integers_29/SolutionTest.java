package org.leetcode.divide_two_integers_29;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

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

    @Test
    public void performance_1()
    {
        Solution solution = new Solution();
        long t0 = System.nanoTime();

        for (int i=1; i<= 1000; i++)
        {
            assertTrue(solution.divide(-2147483648,-1017100424) == 2);
        }

        long t1 = System.nanoTime() - t0;

        assertTrue((t1 / 1_000l) < 6_000l);
    }

    @Test
    public void test_AtomicInteger()
    {
        AtomicInteger atomicInteger = new AtomicInteger();

        for (int i=1; i<= 1_000_000; i++)
            atomicInteger.incrementAndGet();
    }

    @Test
    public void test_int()
    {
        int k = 0;
        for (int i=1; i<= 1_000_000; i++)
            k++;
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

    @Test
    public void test_576()
    {
        Solution solution = new Solution();
        assertTrue(solution.divide(-2147483648,-1017100424) == 2);
    }

}
