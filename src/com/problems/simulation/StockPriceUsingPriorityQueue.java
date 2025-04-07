package com.problems.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
* Question:
* You are given a stream of records about a particular stock. Each record contains a timestamp
* and the corresponding price of the stock at that timestamp.
* Unfortunately due to the volatile nature of the stock market, the records do not come in order.
* Even worse, some records may be incorrect. Another record with the same timestamp may appear later
* in the stream correcting the price of the previous wrong record.
*
* Design an algorithm that:
*   - Updates the price of the stock at a particular timestamp, correcting the price from any previous
*     records at the timestamp.
*   - Finds the latest price of the stock based on the current records.
*     The latest price is the price at the latest timestamp recorded.
*   - Finds the maximum price the stock has been based on the current records.
*   - Finds the minimum price the stock has been based on the current records.
* Implement the StockPrice class:
* StockPrice() Initializes the object with no price records.
* void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
* int current() Returns the latest price of the stock.
* int maximum() Returns the maximum price of the stock.
* int minimum() Returns the minimum price of the stock.
*
* Constraints:
* 1 <= timestamp, price <= 109
* At most 105 calls will be made in total to update, current, maximum, and minimum.
* current, maximum, and minimum will be called only after update has been called at least once.
*
* Example:
* Input
["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
* Output    [null, null, null, 5, 10, null, 5, null, 2]
*
* Explanation
* StockPrice stockPrice = new StockPrice();
* stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
* stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
* stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
* stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
* stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
                          // Timestamps are [1,2] with corresponding prices [3,5].
* stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
* stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
* stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
*/
public class StockPriceUsingPriorityQueue {
    Map<Integer,Integer> timestampWisePriceMap;
    int currentTimestamp;
    PriorityQueue<int[]> minHeap, maxHeap;

    public StockPriceUsingPriorityQueue() {
        this.timestampWisePriceMap = new HashMap<>();
        this.currentTimestamp = -1;
        minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        maxHeap = new PriorityQueue<>((a,b) -> b[0] - a[0]);
    }

    public void update(int timestamp, int price) {
        timestampWisePriceMap.put(timestamp,price);
        currentTimestamp = Math.max(currentTimestamp, timestamp);
        maxHeap.add(new int[]{price, timestamp});
        minHeap.add(new int[]{price, timestamp});
    }

    int current() {
        return timestampWisePriceMap.get(currentTimestamp);
    }

    int maximum() {
        int top[] = maxHeap.peek();
        //while entries in map and heap don't match remove older values(updated)
        while(timestampWisePriceMap.get(top[1]) != top[0]) {
            maxHeap.remove();
            top = maxHeap.peek();
        }
        return top[0];
    }

    int minimum() {
        int top[] = minHeap.peek();
        //while entries in map and heap don't match remove older values(updated)
        while(timestampWisePriceMap.get(top[1]) != top[0]) {
            minHeap.remove();
            top = minHeap.peek();
        }
        return top[0];
    }

    public static void main(String[] args) {
        //Example
        StockPriceUsingPriorityQueue stock = new StockPriceUsingPriorityQueue();
        String[] instructions = new String[]{"update","update","current","maximum","update","maximum","update","minimum"};
        int[][] timestampWithPrice = new int[][]{{1,10},{2,5},{1,3},{4,2}};

        System.out.println("\nResult: ");
        int i=0;
        for(String instruction: instructions) {
            switch(instruction) {
                case "update":
                    stock.update(timestampWithPrice[i][0],timestampWithPrice[i][1]);
                    i++;
                    break;
                case "current":
                    System.out.print(stock.current() + " ");
                    break;
                case "maximum":
                    System.out.print(stock.maximum() + " ");
                    break;
                case "minimum":
                    System.out.print(stock.minimum() + " ");
                    break;
            }
        }
        /*
        * Time Complexity = o(n log n)
        *   -   For n instructions
        *   -   For each update in map its takes o(1) time
        *   -   For each insert in heap it takes o(log n) time
        * Space Complexity = o(n)
        * */
    }
}
