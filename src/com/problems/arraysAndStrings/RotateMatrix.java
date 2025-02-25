package com.problems.arraysAndStrings;

/*
* Question : Rotate Metrics
* Given an image represented by N X N matrix, where each pixel in image is 4bytes.
* Write a method to rotate an image by 90 degrees.
* Follow up : Can you do this in place?
*
* 1 2 3         3 6 9
* 4 5 6  =>     2 5 8
* 7 8 9         1 4 7
* */
public class RotateMatrix {

    public static void rotateImageUsingExtraSpace(int[][] matrix) {
        /*
        * Intent: 1. Create a new matrix and copy elements in given matrix reverse order
        * */
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] resultMatrix = new int[columns][rows];

        for(int i=0, m=0; i<rows; i++, m++) {
            for(int j=columns-1, n=0; j>=0 ; j--, n++) {
                resultMatrix[n][m] = matrix[i][j];
            }
        }
        System.out.println("\nMatrix after 90 degree rotation:");
        printMatrix(resultMatrix);
        /*
        * Time Complexity = o(n) * o(n) = o(n^2)
        * Space Complexity = o(n) * o(n) = o(n^2)
        * */
    }

    public static void rotateMatrixUsingTransposeMethod(int[][] matrix) {
        /*
        * Intent : 1. Find a transpose of a matrix by swapping a[i][j] with a[j][i]
        *          2. Reverse the columns of transpose matrix to get rotated matrix
        * */
        int rows = matrix.length;
        int columns = matrix[0].length;

        //Step 1: Transpose of matrix
        for(int i=0; i<rows; i++) {
            for(int j=i; j<columns ; j++) {
                if (i != j) {
                    //swap a[i][j] with a[j][i]
                    matrix[i][j] = matrix[i][j] + matrix[j][i];
                    matrix[j][i] = matrix[i][j] - matrix[j][i];
                    matrix[i][j] = matrix[i][j] - matrix[j][i];
                }
            }
        }
        System.out.println("\nTranspose Matrix: ");
        printMatrix(matrix);

        //step 2: Reverse columns of transpose matrix
        for(int i=0; i<columns; i++) {
            int top = 0, bottom =rows-1;
            while(top < bottom) {
                //swap a[i][j] with a[j][i]
                matrix[top][i] = matrix[top][i] + matrix[bottom][i];
                matrix[bottom][i] = matrix[top][i] - matrix[bottom][i];
                matrix[top][i] = matrix[top][i] - matrix[bottom][i];
                top++;
                bottom--;
            }
        }
        System.out.println("\nMatrix after 90 degree rotation:");
        printMatrix(matrix);
        /*
         * Time Complexity = o(n^2) + o(n^2) = o(n^2)
         * Space Complexity = o(1)
         * */
    }

    // Helper method to print the matrix
    public static void printMatrix(int[][] matrix) {
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

        //Brute Force Solution - using extra space - o(n^2)
        rotateImageUsingExtraSpace(image);

        //Optimised solution using transpose method
        rotateMatrixUsingTransposeMethod(image);
    }
}
