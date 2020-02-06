package org.leetcode.integer_to_english_words_273;

public class Solution
{
    private static final String[] THOUSANDS = new String[] {"","Thousand","Million","Billion"};
    private static final String[] DIGITS = new String[] {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
    private static final String[] TEENS = new String[] {"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    private static final String[] TENS = new String[] {"Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};

    private int valOfDigit(char digit)
    {
        return digit - '0';
    }

    /**
     * From 123456 to 123 456 with indexes
     * @param numText
     * @return
     */
    private int[][] divideIntoSections(String numText)
    {
        int numberOfSections = (int)Math.ceil((double)numText.length()/3.0);
        int[][] sections = new int[numberOfSections][3];

        for (int i=1; i<=numberOfSections; i++)
        {
            sections[i-1][0] = i*3-3;

            //last section
            if (i==numberOfSections)
            {
                if (numText.length()%3 == 0)
                    sections[i-1][1] = i*3-1;
                else if (numText.length()%3 == 2)
                    sections[i-1][1] = i*3-2;
                else
                    sections[i-1][1] = i*3-3;
            }
            else
                sections[i-1][1] = i*3-1;

        }

        return sections;
    }

    private String buildTens(String numText,int start,int end)
    {
        int length = end-start+1;

        //0,1 etc
        if (length == 1)
        {
            return DIGITS[numText.charAt(start) - '0'];
        }
        else if (length >= 2)
        {
            //00
            if (numText.charAt(start) == '0' && numText.charAt(end) == '0')
            {
                return "";
            }
            //01, 02 etc
            else if (numText.charAt(end) == '0')
            {
                return DIGITS[numText.charAt(start) - '0'];
            }
            //10,20 etc
            else if (numText.charAt(start) == '0')
            {
                return TENS[numText.charAt(end) - '1'];
            }
            //11,12 teens
            else if(numText.charAt(start) >= '1' && numText.charAt(start) <= '9' &&
                    numText.charAt(start+1) == '1')
            {
                return TEENS[numText.charAt(start) - '1'];
            }
            //56 77 etc
            else
            {
                return TENS[numText.charAt(start+1) - '1'] + " " + DIGITS[numText.charAt(start) - '0'];
            }
        }

        return "";

    }

    private String buildSection(String numText,int start,int end,int sectionNo)
    {
        StringBuilder stringBuilder = new StringBuilder();
        int length = end-start+1;
        String value = "";
        String hundred = "";
        String tens = "";
        String thousand = "";

        //hundred
        if (length == 3 && numText.charAt(end) != '0')
        {
            hundred = DIGITS[numText.charAt(end) - '0'] + " Hundred";
        }

        //tens
        if (length >= 1)
        {
            tens = buildTens(numText,start,end);
        }

        //thousand
        thousand = THOUSANDS[sectionNo];

        if (!hundred.isEmpty() && !tens.isEmpty())
            hundred += " ";

        if (!tens.isEmpty() && !thousand.isEmpty())
            tens += " ";

        return hundred + tens + thousand;
    }

    /**
     * Interface method
     * @param num 123_354_654
     * @return
     */
    public String numberToWords(int num)
    {
        String numText = new StringBuilder(Integer.toString(num)).reverse().toString();
        StringBuilder finalText = new StringBuilder();

        int[][] sections = divideIntoSections(numText);
        String[] sectionTexts = new String[sections.length];

        //Build each section
        for (int i=0; i<sections.length;i++)
        {
            sectionTexts[sections.length-i-1] = buildSection(numText,sections[i][0],sections[i][1],i);
        }

        //Join all sections
        for (int i=0; i<sectionTexts.length; i++)
        {
            finalText.append(sectionTexts[i]);

            if (i != sectionTexts.length-1)
                finalText.append(" ");
        }

        return finalText.toString();
    }
}
