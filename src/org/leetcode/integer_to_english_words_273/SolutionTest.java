package org.leetcode.integer_to_english_words_273;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SolutionTest
{
    /************************** Sub cases ************************/

    @Test
    public void testOwn_1()
    {
        Solution solution = new Solution();

        solution.numberToWords(24_567_891);
    }

    @Test
    public void testOwn_2()
    {
        Solution solution = new Solution();

        assertTrue(solution.numberToWords(0).equals("Zero"));
    }

    @Test
    public void testOwn_3()
    {
        Solution solution = new Solution();

        assertTrue(solution.numberToWords(10).equals("Ten"));
    }

    @Test
    public void testOwn_4()
    {
        Solution solution = new Solution();

        assertTrue(solution.numberToWords(111).equals("One Hundred Eleven"));
    }

    /*************************** LC Cases ************************/

    @Test
    public void testLC_1()
    {
        Solution solution = new Solution();

        assertTrue(solution.numberToWords(123).equals("One Hundred Twenty Three"));
    }

    @Test
    public void testLC_3()
    {
        Solution solution = new Solution();

        assertTrue(solution.numberToWords(1_234_567).equals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"));
    }

    @Test
    public void testLC_4()
    {
        Solution solution = new Solution();

        assertTrue(solution.numberToWords(1_234_567_891).equals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"));
    }

}
