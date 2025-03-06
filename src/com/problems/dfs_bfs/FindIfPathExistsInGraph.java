package com.problems.dfs_bfs;

/*
* Question: Find if path exists from source to destination
* There is a bidirectional graph with n vertices, where each vertex is labeled from 0 to n-1 (inclusive).
* The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi]
* denotes a bidirectional edge between vertex ui and vertex vi.
* Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
*
* You want to determine if there is a valid path that exists from vertex source to vertex destination.
* Given edges and the integers n, source, and destination,
* return true if there is a valid path from source to destination, or false otherwise.
* */

import java.util.*;

public class FindIfPathExistsInGraph {
    private static boolean validPathUsingDFSRecursive(int n, int[][] edges, int source, int destination) {
        /*
         * Intent:
         * 1. start with source node as current node, mark it as visited
         * 2. For each neighbor node nn of current node
         *      - check if nn == destination node, if yes return true
         *      - else step 2
         * Above step will execute recursively
         * */
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];
            graph.computeIfAbsent(a,val-> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b,val-> new ArrayList<>()).add(a);
        }
        boolean[] visited = new boolean[n];
        return dfs(graph, visited, source, destination);
        /*
        * Time Complexity = o(n +m)
        * Space Complexity = o(n +m)
        * */
    }

    private static boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int currentNode, int destNode) {
        if(currentNode == destNode)
            return true;

        if(!visited[currentNode]) {
            for(int node: graph.get(currentNode)) {
                if(!visited[node]) {
                    visited[node] = true;
                    if(dfs(graph, visited, node, destNode))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean validPathUsingBFS(int n, int[][] edges, int source, int destination) {
        /*
        * Intent:
        * 1. start with source node as current node, mark it as visited and add to queue
        * 2. queue.poll to get currentNode
        * 3. find all neighbors of current node
        * 4. check if any of neighbors is destination (if yes return true)
        * 5. Add all non-visited neighbors to queue
        * 6. Repeat steps 2-5 until queue is not empty
        * */
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] edge: edges) {
            int a = edge[0], b = edge[1];
            graph.computeIfAbsent(a,val-> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b,val-> new ArrayList<>()).add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        visited[source] = true;
        q.add(source);

        while(!q.isEmpty()) {
            int currentNode = q.poll();
            if(currentNode == destination)
                return true;

            for(int node: graph.get(currentNode)) {
                if(!visited[node]) {
                    visited[node] = true;
                    q.add(node);
                }
            }
        }
        return false;
        /*
        * Time Complexity = o(n + m) --> where n is no of nodes and m is no of edges
        * Space Complexity = o(n + m)
        * */
    }

    public static void main(String[] args) {
        //BFS - o(n + m)
        System.out.println("Using BFS: "+validPathUsingBFS(3, new int[][]{{0,1},{1,2},{2,0}},0,2));
        System.out.println("Using BFS: "+validPathUsingBFS(6, new int[][]{{0,1},{0,2},{3,5},{5,4},{4,3}},0,5));

        //DFS - o(n + m)
        System.out.println();
        System.out.println("Using DFS: "+validPathUsingBFS(3, new int[][]{{0,1},{1,2},{2,0}},0,2));
        System.out.println("Using DFS: "+validPathUsingBFS(6, new int[][]{{0,1},{1,2},{2,0}},0,5));
    }
}
