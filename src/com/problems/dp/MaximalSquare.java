package com.problems.dp;

/*
* Question:
* Given an m x n binary matrix filled with 0's and 1's,
* find the largest square containing only 1's and return its area.
* */
public class MaximalSquare {

    private static int maximalSquareBruteForceSolution(char[][] arr) {
        int maxLength =0;
        int r=arr.length, c = arr[0].length;
        for(int i=0;i<r; i++) {
            for(int j=0; j<c; j++) {
                if(arr[i][j] == '1') {
                    int count = 1;
                    boolean flag = true;
                    while(count+i < r && count+j < c && flag) {
                        for(int k=i; k<=count+i; k++) {
                            if(arr[k][count+j] == '0') {
                                flag = false;
                                break;
                            }
                        }

                        for(int k=j; k<=count+j && flag; k++) {
                            if(arr[count+i][k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if(flag)
                            count++;
                    }
                    maxLength = Math.max(maxLength,count);
                }
            }
        }
        return maxLength * maxLength;
        /*
        * Time Complexity = o(mn * mn) = o((mn)^2)
        * Space Complexity = o(1)
        * */
    }

    private static int maximalSquareUsingDP(char[][] arr) {
        int maxLength =0;
        int r=arr.length, c = arr[0].length;
        int[][] dp = new int[r+1][c+1];

        for(int i=1;i<=r; i++) {
            for(int j=1; j<=c; j++) {
                if(arr[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1])) + 1;
                    maxLength = Math.max(maxLength,dp[i][j]);
                }
            }
        }
        return maxLength * maxLength;
        /*
         * Time Complexity = o(mn)
         * Space Complexity = o(mn)
         * */
    }

    public static void main(String[] args) {
        //Brute Force Solution - o(mn * mn)
        System.out.println("output: " + maximalSquareBruteForceSolution(new char[][] {
                {'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
        System.out.println("output: " + maximalSquareBruteForceSolution(new char[][] {
                {'0','1'},{'1','0'}}));
        System.out.println("output: " + maximalSquareBruteForceSolution(new char[][] {
                {'0'}}));

        //Optimised Solution - o(mn)
        System.out.println("output: " + maximalSquareUsingDP(new char[][] {
                {'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
        System.out.println("output: " + maximalSquareUsingDP(new char[][] {{'0','1'},{'1','0'}}));
        System.out.println("output: " + maximalSquareUsingDP(new char[][] {{'0'}}));
    }
}
