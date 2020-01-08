package org.leetcode.validate_ip_address;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest
{
    @Test
    public void testIpV4()
    {
        Assert.assertTrue((new Solution().validIPAddress("23.-0.25.6").equals("IPv4")));
    }

    @Test
    public void testIpV6_success()
    {
        Assert.assertTrue((new Solution().validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334").equals("IPv6")));
    }

    @Test
    public void testIpV6_signs_failure()
    {
        Assert.assertTrue((new Solution().validIPAddress("2001:db8:85a3:-0:0:8A2E:0370:7334").equals("Neither")));
    }

    @Test
    public void testIpV6_failure()
    {
        Assert.assertTrue((new Solution().validIPAddress("").equals("Neither")));
    }
}
