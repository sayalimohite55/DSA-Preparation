package com.problems.rePractice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
* Question:
* Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
* return the number of islands.
* An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
* You may assume all four edges of the grid are all surrounded by water.
*
* Example 1:
* Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
* Output: 1
*
* Example 2:
* Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
* Output: 3
*
* Constraints:
* m == grid.length
* n == grid[i].length
* 1 <= m, n <= 300
* grid[i][j] is '0' or '1'.
* */
public class NumberOfIslands {
    private static class Pair{
        int row, column;
        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public static int countIslandsUsingBFS(int[][] grid){
        //Breadth First Search
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        int count = 0;


        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j<columnLength; j++) {
                if(grid[i][j] == 1) { //Meaning it's a land and not visited yet
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i,j));
                    grid[i][j] = -1; //Marking this as visited

                    while(!q.isEmpty()) {
                        Pair node = q.remove();
                        int currentRow = node.row;
                        int currentCol = node.column;

                        //Explore neighbors
                        int[] rowDelta = new int[]{0,0,1,-1};
                        int[] columnDelta = new int[]{1,-2,0,0};
                        for(int k =0; k<4; k++) {
                            int newRow = currentRow + rowDelta[k];
                            int newCol = currentCol + columnDelta[k];

                            if(newRow < 0 || newRow == rowLength || newCol < 0 || newCol == columnLength )
                                continue;
                            if(grid[newRow][newCol] == 1) {
                                q.add(new Pair(newRow,newCol));
                                grid[newRow][newCol] = -1;
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count;
        /*
        * Time Complexity = o( rowLength * columnLength)
        * Space Complexity = o(min(rowLength , columnLength)
        * */
    }

    public static int countIslandsUsingDFS(int[][] grid) {
        //Breadth First Search
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        int count = 0;

        for(int i =0;i<rowLength; i++) {
            for(int j=0;j<columnLength; j++) {
                if(grid[i][j] == 1) {
                    //Unvisited land starts here
                    Stack<Pair> stack = new Stack<>();
                    stack.add(new Pair(i,j));
                    grid[i][j] = -1;

                    while(!stack.isEmpty()) {
                        Pair current = stack.pop();
                        int currentRow = current.row;
                        int currentCol = current.column;

                        //Explore neighbors
                        int[] rowDelta = new int[]{0,0,1,-1};
                        int[] columnDelta = new int[]{1,-2,0,0};
                        for(int k =0; k<4; k++) {
                            int newRow = currentRow + rowDelta[k];
                            int newCol = currentCol + columnDelta[k];

                            if(newRow < 0 || newRow == rowLength || newCol < 0 || newCol == columnLength )
                                continue;
                            if(grid[newRow][newCol] == 1) {
                                stack.push(new Pair(newRow,newCol));
                                grid[newRow][newCol] = -1;
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count;
        /*
         * Time Complexity = o( rowLength * columnLength)
         * Space Complexity = o(rowLength * columnLength)
         * */
    }
    public static void main(String[] args) {
        //Solution Using BFS
        System.out.println("Count: "+countIslandsUsingBFS(new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        }));

        System.out.println("Count: "+countIslandsUsingBFS(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        }));

        //Solution Using DFS
        System.out.println("\nCount: "+countIslandsUsingDFS(new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        }));

        System.out.println("Count: "+countIslandsUsingDFS(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        }));
    }
}
