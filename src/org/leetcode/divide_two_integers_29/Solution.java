package org.leetcode.divide_two_integers_29;


import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class Solution {

    private static int quotient = 0;
    private static int remainder = 1;

    public class MutableInt
    {
        public int val;

        public MutableInt(int val)
        {
            this.val = val;
        }
    }

    private int checkForOverflows(int dividend, int divisor)
    {
         if (dividend == MIN_VALUE)
         {
             if (divisor == MIN_VALUE)
                 return 1;
             else if (divisor == -1)
                 return MAX_VALUE;
             else if (divisor == 1)
                 return MIN_VALUE;
             else if (divisor == MAX_VALUE)
                 return -1;
             else
                 return 5;
         }
         else if (dividend == MAX_VALUE)
         {
             if (divisor == MIN_VALUE)
                 return 0;
             else if (divisor == -1)
                 return MIN_VALUE + 1;
             else if (divisor == 1)
                 return MAX_VALUE;
             else if (divisor == MAX_VALUE)
                 return 1;
             else
                 return 5;
         }
         else if (divisor == Integer.MIN_VALUE || divisor == Integer.MAX_VALUE)
             return 0;


        return 5;
    }

    private int[] checkEdgeCases(int dividend, int divisor)
    {
        int[] result = new int[] {-1,-1};

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
        else if (Math.abs(dividend) < Math.abs(divisor))
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


    private int boostFromQuotient(StringBuilder remainderSeries, String dividendSeries, int[] quotientArr, MutableInt quotientInd, int divisor,
                                  MutableInt indexDividend)
    {
        int subDividend = 0;

        while (true)
        {
            if (indexDividend.val == dividendSeries.length())
                return -1;


            remainderSeries.append(dividendSeries.charAt(indexDividend.val++));
            subDividend = parse(remainderSeries.toString());

            if (subDividend < divisor)
            {
               if (true)
                   quotientArr[quotientInd.val++] = 0;
               continue;
            }
            else
            {
                return subDividend;
            }
        }

    }

    private String abs(String val)
    {
        if (val.charAt(0) == '-')
            return val.substring(1,val.length());
        else
            return val;
    }

    private int parse(String number)
    {
        if (number.equals("2147483648"))
            return Integer.MAX_VALUE;
        else
            return Integer.parseInt(number);
    }

    /**
     * Interface method.
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor)
    {

        //Check for overflow cases.
        int overflow = checkForOverflows(dividend,divisor);

        if (overflow != 5)
            return overflow;

        //determine Quotient sign.
        int sign = ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) ? 1 : -1;

        //Set absolute value of divisor.
        divisor = Math.abs(divisor);

        //Convert dividend to string.
        String dividendStr = abs(Integer.toString(dividend));
        StringBuilder remainderStr = new StringBuilder("");
        int[] quotientArr = new int[] {0,0,0,0,0,0,0,0,0,0};
        MutableInt quotientInd = new MutableInt(0);
        MutableInt indexDividend = new MutableInt(0);
        int subDividend = 0;

        //Take to first sub dividend
        StringBuilder subDividendStr = new StringBuilder("");
        for (int i=0; i<dividendStr.length();i++)
        {
            subDividendStr.append(dividendStr.charAt(i));

            if ((subDividend = parse(subDividendStr.toString())) >= divisor)
            {
                indexDividend.val = i + 1;
                break;
            }
        }

        while (true)
        {
            int[] subResult = divideSmall(subDividend,divisor);

            //quotientArr.append(subResult[Solution.quotient]);
            quotientArr[quotientInd.val++] = subResult[Solution.quotient];
            remainderStr = new StringBuilder("");
            remainderStr.append(subResult[Solution.remainder]);

            //Boost the remainder series until it's big enough to be divided into divisor again.
            subDividend = boostFromQuotient(remainderStr,dividendStr, quotientArr,quotientInd, divisor,indexDividend);

            if (subDividend == -1)
                break;
        }

        int quotient = parse(quotientArr.toString());

        return sign == -1 ? -quotient : quotient;

    }
}
