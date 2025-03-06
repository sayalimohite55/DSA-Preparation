package com.problems.maths;

/*
* Question:
* You are given an n x n 2D matrix representing an image,
* rotate the image by 90 degrees (clockwise).
* You have to rotate the image in-place, which means you have to modify the input 2D matrix directly
* DO NOT allocate another 2D matrix and do the rotation.
* */
public class RotateImage {

    private static void rotateMatrixUsingTransposeMethod(int[][] matrix) {
        /*
        * Intent:
        * find Transpose matrix by swapping a[i][j] with a[j][i]
        * Reverse values in each column of transpose matrix
        * */

        int m = matrix.length;
        int n = matrix[0].length;

        //Transpose matrix
        for(int i=0;i<m; i++) {
            for(int j=i; j<n; j++) {
                //swap i,j with j,i
                if(i != j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        //Reverse elements in each column
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
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

    public static void main (String[] args) {
        int[][] image = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(image);

        rotateMatrixUsingTransposeMethod(image);
        System.out.println();
        printMatrix(image);
    }
}
