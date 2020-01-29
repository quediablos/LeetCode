package org.leetcode.jumping_game_ii_45;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SolutionTest
{

    /****************** Custom cases ********************/

    @Test
    public void testCustom_1()
    {
        Solution solution = new Solution();
        assertTrue(solution.jump(new int[] {5,4,2,3,2,1,1,1}) == 3);
    }

    /****************** LC cases ***********************/

    @Test
    public void test_1()
    {
        Solution solution = new Solution();
        assertTrue(solution.jump(new int[] {2,3,1,1,4}) == 2);
    }

    @Test
    public void test_91()
    {
        Solution solution = new Solution();
        assertTrue(solution.jump(new int[] {0}) == 0);
    }
}
