package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* Question :
* Determine if a 9 x 9 Sudoku board is valid.
* Only the filled cells need to be validated according to the following rules:
*   - Each row must contain the digits 1-9 without repetition.
*   - Each column must contain the digits 1-9 without repetition.
*   - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
*
* Note:
* A Sudoku board (partially filled) could be valid but is not necessarily solvable.
* Only the filled cells need to be validated according to the mentioned rules.
* */
public class ValidSudoku {
    private static final int gridDimension =9;

    private static boolean isValidSudokuUsingHashSet(char[][] board) {
        /*
        * Intent:
        * Size of board is always going to be 9 X 9.
        * So everytime 3 things to be checked:
        * - Each row must contain the digits 1-9 without repetition
        * - Each column must contain the digits 1-9 without repetition.
        * - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        * */

        for(int i=0;i<gridDimension;i++) {
            Set<Character> rowSet = new HashSet<>();
            Set<Character> colSet = new HashSet<>();
            for(int j=0;j<gridDimension;j++) {
                //check if all rows valid
                if(board[i][j] != '.') {
                    if (rowSet.contains(board[i][j]))
                        return false;
                    rowSet.add(board[i][j]);
                }
                //check if all columns valid
                if(board[j][i] != '.') {
                    if (colSet.contains(board[j][i]))
                        return false;
                    colSet.add(board[j][i]);
                }
            }
        }

        //check if all 3 X 3 grids are valid
        int[][] visitedGrid = new int[gridDimension][gridDimension]; //All values default to 0
        return isAllGridValid(board, visitedGrid, 0, 0);
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(n^2)
        * */
    }

    private static boolean isAllGridValid(char[][] board,int[][] visitedGrid, int m, int n) {
        /*
        * Check if Dimensions are reached
        * Check if grid is visited :
        *   - value 1 denotes - grid is visited & it is valid 3X3 Grid
        */
        if(m >= gridDimension || n >= gridDimension || visitedGrid[m][n] == 1)
            return true;

        if(visitedGrid[m][n] == -1)
            return false;

        boolean flag = true;
        Set<Character> gridSet = new HashSet<>();

        for(int i=m; i<m+3 && m+3 <= gridDimension; i++) {
            for(int j=n; j<n+3 && n+3 <= gridDimension; j++) {
                if(board[i][j] != '.') {
                    if (gridSet.contains(board[i][j])) {
                        flag = false;
                        break;
                    }
                    gridSet.add(board[i][j]);
                }
            }
            if(!flag)
                break;
        }

        //mask current grid as visited
        visitedGrid[m][n] = flag ? 1 : -1;
        return isAllGridValid(board, visitedGrid, m, n+3)  && isAllGridValid(board, visitedGrid,m+3, n);
    }

    public static void main(String[] args) {
        char[][] sudokuBoard1 = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        char[][] sudokuBoard2 = new char[][]{
                {'8','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        char[][] sudokuBoard3 = new char[][]{
                {'.','.','.','.','.','.','5','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'9','3','.','.','2','.','4','.','.'},
                {'.','.','7','.','.','.','3','.','1'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','3','4','.','.','.','.'},
                {'.','.','.','.','.','3','.','.','.'},
                {'.','.','.','.','.','5','2','.','.'}};

        //Solution using HashSet- o(n^2)
        System.out.println("Is valid Sudoku: "+isValidSudokuUsingHashSet(sudokuBoard1));
        System.out.println("Is valid Sudoku: "+isValidSudokuUsingHashSet(sudokuBoard2));
        System.out.println("Is valid Sudoku: "+isValidSudokuUsingHashSet(sudokuBoard3));
    }
}
