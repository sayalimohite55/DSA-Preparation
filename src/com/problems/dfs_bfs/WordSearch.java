package com.problems.dfs_bfs;

/*
* Question:
* Given an m x n grid of characters board and a string word, return true if word exists in the grid.
* The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
* horizontally or vertically neighboring. The same letter cell may not be used more than once.
*
* Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
* Output: true
* */
public class WordSearch {
    static int row, column;
    static char[][] board;
    private static boolean wordExists(char[][] arr, String word) {
        /*
        * We need to check if a word exists in the given board or not.
        * In order to do so, we need to use DFS with backtracking
        * We will check at every index if the word path exists
        * if not we will erase current state and back track to prev state
        * */
        board = arr;
        row = arr.length;
        column = arr[0].length;

        for(int i =0; i<row; i++) {
            for(int j=0;j<column; j++) {
                if(backtrack(word, i, j, 0))
                    return true;
            }
        }
        return false;
        /*
        * Time Complexity = o(row * column * word length)
        * Space Complexity = o(1)
        * */
    }

    private static boolean backtrack(String word, int i, int j, int index) {
        //Base case
        if(index >= word.length())
            return true;

        //Check boundaries
        if(i < 0 || i == row || j < 0 || j == column || board[i][j] != word.charAt(index))
            return false;

        //Now mark current cell as visited and explore all neighbors
        board[i][j] = '#';
        boolean result = false;
        result = backtrack(word, i-1, j, index+1) || backtrack(word, i+1, j, index+1)
                || backtrack(word, i, j-1, index+1) || backtrack(word, i, j+1, index+1);

        if(result)
            return result;
        //No word path found hence restore to previous state
        board[i][j] = word.charAt(index);
        return result;
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{{'A','B','C','E'},
                                    {'S','F','C','S'},
                                    {'A','D','E','E'}};
        System.out.println("Result: " + wordExists(arr, "ABCCED"));

        arr = new char[][]{{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        System.out.println("Result: " + wordExists(arr, "SEE"));

        arr = new char[][]{{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        System.out.println("Result: " + wordExists(arr, "ABCB"));
    }
}
