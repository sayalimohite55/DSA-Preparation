package com.problems.arraysAndStrings;

/*
* Question: Best Time to Buy And Sell Stocks
* You are given an array prices where prices[i] is the price of a given stock on the ith day.
* You want to maximize your profit by choosing a single day to buy one stock
* and choosing a different day in the future to sell that stock.
* Return the maximum profit you can achieve from this transaction.
* If you cannot achieve any profit, return 0.

*/
public class BestTimeToBuyAndSellStocks {
    private static int bruteForceSolutionForMaxProfit(int[] profits) {
        /*
        * Intent:
        * For each day consider a day as to buy stock and identify maxProfit w.r.t it in coming days
        * */
        int maxProfit = Integer.MIN_VALUE;

        for(int i=0; i<profits.length; i++) {
            int profit = 0;
            for(int j= i+1; j<profits.length; j++) {
                if(profits[j]-profits[i] > profit)
                    profit = profits[j]-profits[i];
            }
            maxProfit = Math.max(maxProfit, profit);
        }

        return Math.max(maxProfit, 0);
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static int maxProfitUsingSlidingWindow(int[] prices) {
        /*
         * Intent:
         * Using sliding window, identify min price day followed by max price day to log maximum profit
         * */
        int maxProfit = Integer.MIN_VALUE;
        int buyDay = 0, sellDay = 1;
        while( sellDay < prices.length) {
            if(prices[buyDay] < prices[sellDay]) {
                int profit = prices[sellDay] - prices[buyDay];
                maxProfit = Math.max(maxProfit, profit);
                sellDay++;
            } else if(buyDay == sellDay) {
                sellDay++;
            } else{
                buyDay++;
            }
        }
        return Math.max(maxProfit, 0);
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    private static int maxProfit(int[] prices) {
        /*
         * Intent:
         * 1. Initialise minPrice and maxProfit values
         * 2. while traversing find minPrice and corresponding maxProfit
         * */
        int maxProfit = Integer.MIN_VALUE;
        int minPrice = Integer.MAX_VALUE;

        int i=0;
        while( i < prices.length) {
            if(prices[i] < minPrice)
                minPrice = prices[i];
            maxProfit = Math.max(maxProfit, prices[i]-minPrice);
            i++;
        }
        return Math.max(maxProfit, 0);
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(n^2)
        System.out.println("MaxProfit: "+bruteForceSolutionForMaxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println("MaxProfit: "+bruteForceSolutionForMaxProfit(new int[]{7,6,4,3,1}));

        //optimised solution using sliding window - o(n)
        System.out.println("\n\nMaxProfit: "+maxProfitUsingSlidingWindow(new int[]{7,1,5,3,6,4}));
        System.out.println("MaxProfit: "+maxProfitUsingSlidingWindow(new int[]{7,6,4,3,1}));

        //Another optimised solution - o(n)
        System.out.println("\n\nMaxProfit: "+maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println("MaxProfit: "+maxProfit(new int[]{7,6,4,3,1}));
    }
}
