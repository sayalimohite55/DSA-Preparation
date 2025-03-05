package com.problems.dfs_bfs;

/*
* Question:
* Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
* return the number of islands.
* An island is surrounded by water and is formed by connecting adjacent lands
* horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
* */

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static class Pair {
        int row, column;

        public Pair(int r, int c) {
            this.row = r;
            this.column = c;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

    }

    private static int numberOfIslandsUsingBFS(char[][] grid) {
        /*
         * Intent:
         * Treat the 2d grid array as an undirected graph and there is an edge
         * between two horizontally or vertically adjacent nodes of value '1'.
         *
         * Use BFS - Breadth First Search for traversal
         * */

        if(grid == null || grid.length == 0)
            return 0;

        int rowLength = grid.length;
        int columnLength = grid[0].length;
        int islandCount = 0;

        for(int i=0; i<rowLength; i++) {
            for(int j=0; j<columnLength; j++) {
                if(grid[i][j] == '1') {
                    islandCount++;

                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    while(!q.isEmpty()) {
                        Pair node = q.remove();
                        int r = node.getRow();
                        int c = node.getColumn();

                        //Mark current node as visited
                        grid[r][c] = '0';

                        if(r-1 >= 0 && grid[r-1][c] == '1')
                            q.add(new Pair(r-1, c));

                        if(r+1 < rowLength && grid[r+1][c] == '1')
                            q.add(new Pair(r+1, c));

                        if(c-1 >= 0 && grid[r][c-1] == '1')
                            q.add(new Pair(r, c-1));

                        if(c+1 < columnLength && grid[r][c+1] == '1')
                            q.add(new Pair(r, c+1));
                    }
                }
            }
        }
        return islandCount;
        /*
        * Time Complexity = o(m * n)
        * Space Complexity = o( min(m, n)) - because in worst case where the grid is filled
        *                                   with lands, the size of queue can grow up to min(m,n).
        * */
    }

    private static int numberOfIslandsUsingDFS(char[][] grid) {
        /*
        * Intent:
        * Treat the 2d grid array as an undirected graph and there is an edge
        * between two horizontally or vertically adjacent nodes of value '1'.
        *
        * Use DFS - Depth first search for traversal
        * */
        if(grid == null || grid.length == 0)
            return 0;

        int rowLength = grid.length;
        int columnLength = grid[0].length;
        int islandCount = 0;

        for(int i=0; i<rowLength; i++) {
            for(int j=0; j<columnLength; j++) {
                if(grid[i][j] == '1') {
                    islandCount ++;
                    dfs(grid,i,j);
                }
            }
        }
        return islandCount;
        /*
        * Time Complexity = o(m * n) --> where m is no of rows and n is no. of columns
        * Space Complexity = o(m * n)
        * */
    }

    private static void dfs(char[][] grid,int i,int j) {
        int rowLength = grid.length;
        int columnLength = grid[0].length;

        if(i >= rowLength || j >= columnLength || i < 0 || j < 0 || grid[i][j] == '0')
            return;

        //Marking node as visited
        grid[i][j]='0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }

    public static void main(String[] args) {
        char[][] grid1 = new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};

        char[][] grid2 = new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};

        // Solution using BFS
        System.out.println("Number of Islands: "+numberOfIslandsUsingBFS(grid1));
        System.out.println("Number of Islands: "+numberOfIslandsUsingBFS(grid2));

        grid1 = new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};

        grid2 = new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};

        // Solution using DFS
        System.out.println("\n\nNumber of Islands: "+numberOfIslandsUsingDFS(grid1));
        System.out.println("Number of Islands: "+numberOfIslandsUsingDFS(grid2));
    }
}
