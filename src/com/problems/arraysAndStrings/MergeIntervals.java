package com.problems.arraysAndStrings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
* Question:
* Given an array of intervals where intervals[i] = [starti, endi],
* merge all overlapping intervals, and return an array of the non-overlapping intervals
* that cover all the intervals in the input.
*
* Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
* Output: [[1,6],[8,10],[15,18]]
* Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
* */
public class MergeIntervals {
    private static void mergeIntervals(int[][] intervals) {
        /*
        * Intent:
        * Sort array , so that intervals are set in a sequence
        * See if end time of current interval overlaps with start or end time of next interval
        * If yes, merge the two intervals
        * */
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        LinkedList<int[]> list = new LinkedList<>();

        for(int[] currentInterval : intervals) {
            if(list.isEmpty() || list.getLast()[1] < currentInterval[0] )
                list.add(currentInterval);
            else {
                list.getLast()[1] = Math.max(list.getLast()[1], currentInterval[1]);
            }
        }
        printOutput(list);
        /*
        * Time Complexity = o(n) + o(n log n) --> sorting and traversal
        * Space Complexity = o(n)
        * */
    }

    private static void printOutput(List<int[]> list) {
        System.out.println();
        for(int[] interval : list) {
            System.out.print(" ["+interval[0]+", "+interval[1]+"]");
        }
    }

    public static void main(String[] args) {
        mergeIntervals(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        mergeIntervals(new int[][]{{1,4},{4,5}});
    }
}
