package org.leetcode.word_ladder_ii_126;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SolutionTest {

    @Test
    public void canTransormTo_1()
    {
        Solution solution = new Solution();

        assertTrue(solution.canTransormTo("hit","hot"));
    }

    @Test
    public void canTransormTo_2()
    {
        Solution solution = new Solution();

        assertFalse(solution.canTransormTo("hit","cog"));
    }
}
