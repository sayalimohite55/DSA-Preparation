package com.problems.arraysAndStrings;

import java.util.*;

/*
* Question:
* Write an algorithm such that if an element in an MXN matrix is zero, its entire rows and columns are set to zero.
* */
public class ZeroMatrix {
    private static void zeroMatrix(int[][] a) {
        int r = a.length;
        int c = a[0].length;

        boolean[] row = new boolean[r];
        boolean[] column = new boolean[c];

        for(int i=0; i<r;i++) {
            for(int j=0;j<c; j++) {
                if(a[i][j] == 0) {
                    //mark all rows and columns to be zeroed
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for(int i=0; i<r;i++) {
            for(int j=0;j<c; j++) {
                if(row[i] || column[j]) {
                    a[i][j] = 0;
                }
            }
        }

        System.out.println("Output Matrix:");
        printMatrix(a);
        /*
        * Time Complexity = o(n * m) + o(n * m) = o(n*m)
        * Space Complexity = o(n) + o(m)
        * */
    }

    // Helper method to print the matrix
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] m1 = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };
        System.out.println("Original Matrix:");
        printMatrix(m1);

        zeroMatrix(m1);
    }
}
