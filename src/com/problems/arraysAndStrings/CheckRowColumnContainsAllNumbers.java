package com.problems.arraysAndStrings;

import java.util.HashSet;
import java.util.Set;

/*
* Question:
* An n x n matrix is valid if every row and every column contains all the integers from 1 to n
* (inclusive).Given an n x n integer matrix,
* return true if the matrix is valid. Otherwise, return false.
*/
public class CheckRowColumnContainsAllNumbers {
    private static boolean solution(int[][] matrix) {
        int n= matrix.length;
        for(int i=0;i<n; i++) {
            Set<Integer> row = new HashSet<>();
            Set<Integer> col = new HashSet<>();
            for(int j=0;j<n;j++) {
                if(matrix[i][j]>= 1 && matrix[i][j]<=n) {
                    row.add(matrix[i][j]);
                    col.add(matrix[j][i]);
                }
                else return false;
            }
            if(row.size() != n || col.size() != n)
                return false;
        }
        return true;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result: "+solution(new int[][]{{1,2,3},{3,1,2},{2,3,1}}));
        System.out.println("Result: "+solution(new int[][]{{1,1,1},{1,2,3},{2,3,1}}));
    }
}
