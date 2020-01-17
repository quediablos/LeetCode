package org.leetcode.super_egg_drop_887;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SolutionTest
{

    @Test
    public void test_2()
    {
        assertTrue((new Solution()).superEggDrop(2,6) == 3);
    }

    @Test
    public void test_3()
    {
        assertTrue((new Solution()).superEggDrop(3,14) == 4);
    }

    @Test
    public void test_58()
    {
        assertTrue((new Solution()).superEggDrop(1,2) == 2);
    }

    @Test
    public void test_68()
    {
        assertTrue((new Solution()).superEggDrop(1,2) == 2);
    }

    @Test
    public void test_71()
    {
        assertTrue((new Solution()).superEggDrop(1,4) == 4);
    }
}
