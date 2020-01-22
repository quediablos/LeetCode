package org.leetcode.wildcard_matching_44;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SolutionTest
{

    /*************************** Custom cases **************************/



    /*************************** LC cases ******************************/

    @Test
    public void test_1()
    {
        assertTrue((new Solution()).isMatch("aa","a") == false);
    }
}
