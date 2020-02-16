package org.leetcode.best_time_to_sell_and_buy_stocks_188;


import java.util.*;
import java.util.concurrent.Delayed;

public class Solution
{

    public class Deal
    {
        public int start;
        public int end;
        public int buy;
        public int sell;
        public int profit;

        public Deal(int start, int end, int buy, int sell, int profit)
        {
            this.start = start;
            this.end = end;
            this.buy = buy;
            this.sell = sell;
            this.profit = profit;
        }



        @Override
        public String toString()
        {
            return "Deal{" +
                    "start=" + start +
                    ", end=" + end +
                    ", buy=" + buy +
                    ", sell=" + sell +
                    ", profit=" + profit +
                    '}';
        }
    }

    private LinkedList<Deal> findBestDeals2(int[] prices)
    {
        LinkedList<Deal> deals = new LinkedList<>();
        boolean findMin = true;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minInd = -1;
        int maxInd = -1;
        //3,2,6,7,5,0,3
        for (int i=0; i<prices.length;i++)
        {
            if (findMin)
            {
                if (prices[i] < min)
                {
                    min = prices[i];
                    minInd = i;
                }
                else
                {
                    findMin = false;
                    max = prices[i];
                    maxInd = i;

                    if (i == prices.length-1)
                        deals.add(new Deal(minInd,maxInd,min,max,max-min));

                }
            }
            else
            {
                if (prices[i] > max)
                {
                    max = prices[i];
                    maxInd = i;

                    if (i == prices.length-1)
                        deals.add(new Deal(minInd,maxInd,min,max,max-min));
                }
                else
                {
                    findMin = true;
                    deals.add(new Deal(minInd,maxInd,min,max,max-min));
                    max = Integer.MIN_VALUE;
                    min = prices[i];
                    minInd = i;
                }
            }
        }

        return deals;
    }


    private Deal merge(Deal deal1, Deal deal2)
    {
        return new Deal(deal1.start,deal2.end,deal1.buy,deal2.sell,deal2.sell-deal1.buy);
    }

    private void devour(LinkedList<Deal> deals)
    {
        int minLoss = Integer.MAX_VALUE;
        int minLossInd = -1;
        boolean removeSale = false;

        //Check merges
        for (int i =0; i<deals.size(); i++)
        {
            //Check loss with merge
            if (i < deals.size()-1)
            {
                Deal deal1 = deals.get(i);
                Deal deal2 = deals.get(i+1);

                int loss = deal1.profit + deal2.profit - (deal2.sell-deal1.buy);

                if (loss < minLoss)
                {
                    minLoss = loss;
                    minLossInd = i;
                    removeSale = false;
                }
            }

            //Check loss with removing the deal.
            if (deals.get(i).profit < minLoss)
            {
                minLoss = deals.get(i).profit;
                removeSale = true;
                minLossInd = i;
            }

        }

        //Remove single sale.
        if (removeSale)
        {
            deals.remove(minLossInd);
        }
        //Merge two sales.
        else
        {
            Deal mergedDeal = merge(deals.get(minLossInd),deals.get(minLossInd+1));

            deals.remove(minLossInd+1);
            deals.remove(minLossInd);
            deals.add(minLossInd,mergedDeal);
        }


    }

    /**
     * Interface method
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices)
    {
        if (k == 0)
            return 0;

        LinkedList<Deal> bestDeals = findBestDeals2(prices);

        if (bestDeals.size() <= k)
        {
            int sum = 0;
            for (Deal deal:bestDeals)
                sum += deal.profit;
            return sum;
        }
        else
        {
            int numberOfDevours = bestDeals.size() - k;

            for (int i=1; i<=numberOfDevours; i++)
                devour(bestDeals);

            int sum = 0;
            for (Deal deal:bestDeals)
                sum += deal.profit;
            return sum;
        }

    }
}
