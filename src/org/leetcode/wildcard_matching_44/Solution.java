package org.leetcode.wildcard_matching_44;


import java.util.LinkedList;
import java.util.List;

public class Solution
{

    private Boolean checkEdgeCases(String s, String wildcard)
    {
        if (onlyStars(wildcard))
            return Boolean.TRUE;

        if (s.isEmpty())
        {
            if (wildcard.isEmpty())
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        }
        else if (wildcard.isEmpty())
            return Boolean.FALSE;

        return null;
    }

    private boolean onlyStars(String wildcard)
    {
        if (wildcard.isEmpty())
            return false;

        for (int i=0; i<wildcard.length(); i++)
        {
            if (wildcard.charAt(i) != '*')
                return false;
        }

        return true;
    }

    /**
     * Simplifies the wildcard removing extra *s
     * @param wildcard
     * @return
     */
    private String simplifyWildcard(String wildcard)
    {
        StringBuilder wildcardSimplified = new StringBuilder();

        return wildcardSimplified.toString();
    }

    public List<String> divideIntoSequences(String p)
    {
        List<String> sequenceList = new LinkedList<>();
        StringBuilder sequence = new StringBuilder();
        boolean startedSequence = false;

        for (int i=0; ;i++)
        {
            char character = p.charAt(i);

            //last character
            if (i == p.length() - 1)
            {
                sequence.append(character);
                sequenceList.add(sequence.toString());
                return sequenceList;
            }
            else if (character == '*')
            {
                if (startedSequence)
                {
                    if (p.charAt(i-1) != '*')
                    {
                        startedSequence = false;
                        sequence.append(character);
                        sequenceList.add(sequence.toString());
                        sequence = new StringBuilder();
                        i--;
                    }
                    else
                    {
                        continue;
                    }
                }
                else
                {
                    sequence.append(character);
                    startedSequence = true;
                }

                continue;
            }
            else
            //? or any character
            {
                if (startedSequence)
                {
                    sequence.append(character);
                }
                else
                {
                    sequence.append(character);
                    startedSequence = true;
                }
            }
        }

    }

    public int compare(String string, int startString, int endString, String sequence, int startSequence)
    {
        for (int i=startString; i<=endString; i++)
        {
            char charFromString = string.charAt(i);
            char charFromSequence = sequence.charAt(i + (startSequence-startString));

            if (charFromSequence == '?')
                continue;
            else if (charFromString == charFromSequence)
                continue;
            else
                return i;
        }

        return endString+1;
    }

    /**
     * Checks if string has any part fitting the sequence starting with the given index.
     * @param string
     * @param sequence
     * @param from
     * @return offset to be continued afterwards.
     */
    public int searchSequence(String string, String sequence, int from)
    {
        int start,end;
        //eg: ab,ab*
        if (sequence.charAt(0) != '*')
        {
            //eg: ab
            if (sequence.charAt(sequence.length()-1) != '*')
            {
                end = string.length()-1;
                if (string.length() != sequence.length())
                    return -1;
                else
                    return compare(string,0,end,sequence,0) ==  end+1 ? end+1: -1;
            }
            //eg: ab*
            else
            {
                if (from + sequence.length()-1 > string.length())
                    return -1;

                end = sequence.length()-2;
                return compare(string,0,end,sequence,0) == end+1 ? end+1: -1;
            }

        }
        //eg: *ab, *ab*
        else
        {
            //eg: *ab
            if (sequence.charAt(sequence.length()-1) != '*')
            {
                //no more characters left.
                if (from + sequence.length()-1 > string.length())
                    return -1;

                start = string.length()-sequence.length()+1;
                end = string.length()-1;

                return compare(string,start,end,sequence,1) == end+1 ? end+1 : -1;
            }
            //eg: *ab*
            else
            {
                int until = string.length()-sequence.length()+2;
                for (int i = from; i<=until;)
                {
                    end = i+sequence.length()-3;

                    if (compare(string,i,end,sequence,1) == end+1)
                        return end+1;
                    else i = end+1;


                }
            }
        }

        //no match
        return -1;
    }

    /**
     * Interface method
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p)
    {
        //Edge cases
        Boolean edgeCases;
        if ((edgeCases = checkEdgeCases(s,p)) != null)
            return edgeCases;

        //Divide the wildcard into sequences.
        List<String> sequences = divideIntoSequences(p);

        //No sequence such as *,**
        if (sequences.isEmpty())
            return true;

        //For each sequence, try to find the first match.
        int offset = 0;
        for (String sequence: sequences)
        {
            offset = searchSequence(s,sequence,offset);

            if (offset == -1)
                return false;
        }

        return true;
    }
}
