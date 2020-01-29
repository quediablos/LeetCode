package org.leetcode.wildcard_matching_44;


import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SolutionTest
{
    /**************************** Inner tests ****************************/

    @Test
    public void testDivideIntoRegexs_1()
    {
        Solution solution = new Solution();
        List<String> regexListExpected = Arrays.asList("*a*","*?cc*","*t*");
        List<String> regexListReturned = solution.divideIntoSequences("*a*?cc*t*");

        assertTrue(regexListExpected.size() == regexListReturned.size());

        Iterator<String> iteratorRegexListReturned = regexListReturned.iterator();
        regexListExpected.stream().forEach(regexReturned -> {assertTrue(regexReturned.equals(iteratorRegexListReturned.next()));});

    }

    @Test
    public void testDivideIntoRegexs_2()
    {
        Solution solution = new Solution();
        List<String> regexListExpected = Arrays.asList("a*","*?cc*","*t");
        List<String> regexListReturned = solution.divideIntoSequences("a*?cc*t");

        assertTrue(regexListExpected.size() == regexListReturned.size());

        Iterator<String> iteratorRegexListReturned = regexListReturned.iterator();
        regexListExpected.stream().forEach(regexReturned -> {assertTrue(regexReturned.equals(iteratorRegexListReturned.next()));});

    }

    @Test
    public void testDivideIntoRegexs_3()
    {
        Solution solution = new Solution();
        List<String> regexListExpected = Arrays.asList("a?cc?");
        List<String> regexListReturned = solution.divideIntoSequences("a?cc?");

        assertTrue(regexListExpected.size() == regexListReturned.size());

        Iterator<String> iteratorRegexListReturned = regexListReturned.iterator();
        regexListExpected.stream().forEach(regexReturned -> {assertTrue(regexReturned.equals(iteratorRegexListReturned.next()));});

    }

    @Test
    public void testDivideIntoRegexs_4()
    {
        Solution solution = new Solution();
        List<String> regexListExpected = Arrays.asList("*saco??*","*xyz?*");
        List<String> regexListReturned = solution.divideIntoSequences("*saco??*xyz?*");

        assertTrue(regexListExpected.size() == regexListReturned.size());

        Iterator<String> iteratorRegexListReturned = regexListReturned.iterator();
        regexListExpected.stream().forEach(regexReturned -> {assertTrue(regexReturned.equals(iteratorRegexListReturned.next()));});

    }

    @Test
    public void testCompare()
    {
        Solution solution = new Solution();

        /*assertTrue(solution.compare("abcdef",2,3,"c?asd",0) == );
        assertTrue(solution.compare("abcdef",2,3,"???",0));*/
    }

    @Test
    public void testSearchSequence_1()
    {
        Solution solution = new Solution();

        assertTrue(solution.searchSequence("afdgdfgahththccththtccv","afdgdfgahththccththtccv",0) == 23);
        assertTrue(solution.searchSequence("afdgdfgahththccththtccv","afd*",0) == 3);
        assertTrue(solution.searchSequence("afdgdfgahthtafdththtccv","*afd*",2) == 15);
        assertTrue(solution.searchSequence("afdgdfgahththccththtccv","*cct*",0) == 16);
        assertTrue(solution.searchSequence("grogtegeg","*??t*",1) == 5);
        assertTrue(solution.searchSequence("grogtegeg","*??t*",5) == -1);
        assertTrue(solution.searchSequence("grogtegeg","*??",1) == 9);
    }

    /*************************** Custom cases **************************/

    @Test
    public void testCustom_1()
    {
        assertTrue((new Solution()).isMatch("sacrgrgsacoxyzrgrsacoppppxyzpp","*saco??*xyz?*"));
    }

    @Test
    public void testCustom_2()
    {
        assertTrue((new Solution()).isMatch("sacrgrgsacoxyzrgrsacoppoaaxmdxyzp","*saco??*a?md*xyz?"));
    }

    @Test
    public void testCustom_3()
    {
        assertTrue((new Solution()).isMatch("aaaafffbfff","***a**b**"));
    }

    @Test
    public void testCustom_4()
    {
        assertTrue((new Solution()).isMatch("aaaafffbfff","******"));
    }

    @Test
    public void testCustom_5()
    {
        assertFalse((new Solution()).isMatch("","a"));
    }

    @Test
    public void testCustom_6()
    {
        assertTrue((new Solution()).isMatch("a","a"));
    }

    @Test
    public void testCustom_7()
    {
        assertFalse((new Solution()).isMatch("a","b"));
    }

    @Test
    public void testCustom_8()
    {
        assertFalse((new Solution()).isMatch("a",""));
    }

    /*************************** LC cases ******************************/

    @Test
    public void test_1()
    {
        assertFalse((new Solution()).isMatch("aa","a"));
    }

    @Test
    public void test_2()
    {
        assertTrue((new Solution()).isMatch("aa","*"));
    }

    @Test
    public void test_3()
    {
        assertFalse((new Solution()).isMatch("cb","?a"));
    }

    @Test
    public void test_4()
    {
        assertTrue((new Solution()).isMatch("adceb","*a*b"));
    }

    @Test
    public void test_5()
    {
        assertFalse((new Solution()).isMatch("acdcb","a*c?b"));
    }

    @Test
    public void test_18()
    {
        assertFalse((new Solution()).isMatch("mississippi","m??*ss*?i*pi"));
    }

    @Test
    public void test_19()
    {
        assertTrue((new Solution()).isMatch("aaaa","***a"));
    }

    @Test
    public void test_20()
    {
        assertTrue((new Solution()).isMatch("",""));
    }

    @Test
    public void test_53()
    {
        assertFalse((new Solution()).isMatch("a","ab*"));
    }
}
