package org.leetcode.divide_two_integers_29;


public class Solution {

    private static int quotient = 0;
    private static int remainder = 1;

    private int[] checkEdgeCases(int dividend, int divisor)
    {
        int[] result = new int[] {-1,-1};

        //Edge cases
        if (dividend == 0)
        {
            result[quotient] = 0;
            result[remainder] = 0;
            return result;
        }
        else if (divisor == 1)
        {
            result[quotient] = dividend;
            result[remainder] = 0;
        }
        else if (dividend == divisor)
        {
            result[quotient] = 1;
            result[remainder] = 0;
            return result;
        }
        else if (dividend < divisor)
        {
            result[quotient] = 0;
            result[remainder] = 0;
            return result;
        }

        return result;
    }

    //Divide two numbers which are close iteratively by subtraction.
    public int[] divideSmall(int dividend, int divisor)
    {
        int[] result = checkEdgeCases(dividend,divisor);

        if (result[0] != -1)
            return result;

        //Subtract until the remaining amount is less than divisor.
        result[quotient] = 0;
        result[remainder] = dividend;
        while(result[remainder]>= divisor)
        {
            result[quotient]++;
            result[remainder] -= divisor;
        }

        return result;
    }

    /**
     * Interface method.
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor)
    {

        //Assign absolute values and determine quotient sign.
        int sign = ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) ? 1 : -1;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        //Check for edge cases
        int[] edgeCases = checkEdgeCases(dividend,divisor);

        if (edgeCases[quotient] != -1)
            return edgeCases[quotient];

        String dividendSeries = Integer.toString(dividend);
        StringBuilder remainderSeries = new StringBuilder("");
        StringBuilder quotientSeries = new StringBuilder("");
        Integer indexDividend = 0;
        int subDividend = 0;

        //Take to first sub dividend
        StringBuilder subDividendStr = new StringBuilder("");
        for (int i=0; i<dividendSeries.length();i++)
        {
            subDividendStr.append(dividendSeries.charAt(i));

            if ((subDividend = Integer.parseInt(subDividendStr.toString())) >= divisor)
            {
                indexDividend = i+1;
                break;
            }
        }

        while (true)
        {
            int[] subResult = divideSmall(subDividend,divisor);

            quotientSeries.append(subResult[quotient]);
            remainderSeries = new StringBuilder("");
            remainderSeries.append(subResult[remainder]);

            if (indexDividend == dividendSeries.length())
                break;

            //Boost the remainder series until it's big enough to be divided into divisor again.
            if (subResult[remainder] == 0)
            {

            }
            else
            {
                while ((subDividend = Integer.parseInt(remainderSeries.toString())) < divisor)
                {
                    remainderSeries.append(dividendSeries.charAt(indexDividend++));
                }
            }

        }

        int quotient = Integer.parseInt(quotientSeries.toString());

        return sign == -1 ? -quotient : quotient;

    }
}
