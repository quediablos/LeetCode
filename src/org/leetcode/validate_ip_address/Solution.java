package org.leetcode.validate_ip_address;


class Solution {

    public String validIPAddress(String ip)
    {
        if (isIpV4(ip))
            return "IPv4";

        if (isIpV6(ip))
            return "IPv6";

        return "Neither";
    }

    private Integer parseSection(String string, int base)
    {
        try
        {
            return Integer.parseInt(string, base);
        }
        catch (Exception e)
        {
            return null;
        }

    }

    private boolean validateSectionIPv4(String section)
    {
        if (section.equals(""))
            return false;

        //Check for integer value.
        Integer valSection = parseSection(section, 10);

        if (valSection == null)
            return false;

        //Check for leading zeros and signs
        if (valSection.toString().length() != section.length())
            return false;

        //Check for value range
        if (valSection > 255 || valSection < 0)
            return false;

        return true;
    }

    private boolean validateSectionIPv6(String section)
    {
        if (section.equals(""))
            return false;

        //Check for integer value.
        Integer valSection = parseSection(section, 16);

        if (valSection == null)
            return false;

        //Check signs
        if (section.contains("-") || section.contains("+"))
            return false;

        //Check for length
        if (section.length() > 4)
            return false;

        //Check for value range
        if (valSection > 0xFFFF || valSection < 0x0)
            return false;

        return true;
    }

    private boolean isIpV6(String ip)
    {
        //Divide the string into 8 sections
        String[] sections = ip.split("\\:");

        if (sections.length != 8)
            return false;

        //Check for delimiter at the end because split() ignores it.
        if (ip.charAt(ip.length()-1) == ':')
            return false;

        //For each section...
        for(String section : sections)
        {
            if (!validateSectionIPv6(section))
                return false;
        }

        return true;
    }

    private boolean isIpV4(String ip)
    {

        //Divide the string into 4 sections
        String[] sections = ip.split("\\.");

        if (sections.length != 4)
            return false;

        //Check for delimiter at the end because split() ignores it.
        if (ip.charAt(ip.length()-1) == '.')
            return false;

        //For each section...
        for(String section : sections)
        {
            if (!validateSectionIPv4(section))
                return false;
        }

        return true;

    }



}
