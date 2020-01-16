package org.leetcode.divide_two_integers_29;


import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class Solution {

    private static int quotient = 0;
    private static int remainder = 1;



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

    private void boostQuotientWithZeros(String dividendSeries, StringBuilder quotientSeries,AtomicInteger indexDividend)

    {
        quotientSeries.append('0');

        while (true)
        {
            if (indexDividend.intValue() == dividendSeries.length()-1 || dividendSeries.charAt(indexDividend.intValue()) != '0')
                return;

            if (dividendSeries.charAt(indexDividend.getAndIncrement()) == '0')
            {
                quotientSeries.append('0');
            }

        }
    }

    private int boostFromQuotient(StringBuilder remainderSeries, String dividendSeries, StringBuilder quotient, int divisor,
                                  AtomicInteger indexDividend, boolean boostedQuotientWithZeros)
    {
        int subDividend = 0;
        boolean firstTake = true;


        while (true)
        {
            if (indexDividend.intValue() == dividendSeries.length())
            {
                quotient.append('0');
                return -1;
            }

            remainderSeries.append(dividendSeries.charAt(indexDividend.incrementAndGet()));
            subDividend = Integer.parseInt(remainderSeries.toString());

            if (subDividend <= divisor)
            {
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
        StringBuilder remainder = new StringBuilder("");
        StringBuilder quotientStr = new StringBuilder("");
        AtomicInteger indexDividend = new AtomicInteger(0);
        int subDividend = 0;

        //Take to first sub dividend
        StringBuilder subDividendStr = new StringBuilder("");
        for (int i=0; i<dividendStr.length();i++)
        {
            subDividendStr.append(dividendStr.charAt(i));

            if ((subDividend = Integer.parseInt(subDividendStr.toString())) > divisor)
            {
                indexDividend = new AtomicInteger(i+1);
                break;
            }
        }

        while (true)
        {
            int[] subResult = divideSmall(subDividend,divisor);

            quotientStr.append(subResult[Solution.quotient]);
            remainder = new StringBuilder("");
            remainder.append(subResult[Solution.remainder]);

            if (indexDividend.intValue() == dividendStr.length())
                break;

            //Boost the remainder while also appending zeros to the quotient.
            boolean boostedQuotientWithZeros = false;
            if (subResult[Solution.remainder] == 0 && dividendStr.charAt(indexDividend.intValue()) == '0')
            {
                boostQuotientWithZeros(dividendStr,quotientStr,indexDividend);
                boostedQuotientWithZeros = true;
            }

            //No more numbers in the dividend.
            if (indexDividend.intValue() == dividendStr.length()-1)
                break;

            //Boost the remainder series until it's big enough to be divided into divisor again.
            subDividend = boostFromQuotient(remainder,dividendStr, quotientStr,divisor,indexDividend,boostedQuotientWithZeros);

            if (subDividend == -1)
                break;
        }

        int quotient = Integer.parseInt(quotientStr.toString());

        return sign == -1 ? -quotient : quotient;

    }
}
