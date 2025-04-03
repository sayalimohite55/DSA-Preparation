package com.problems.dfs_bfs;

import com.problems.trie.Trie;

import java.util.*;

/*
* Question:
* Given an m x n board of characters and a list of strings words, return all words on the board.
* Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells
* are horizontally or vertically neighboring. The same letter cell may not be used more than once
* in a word.
*
* Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
* words = ["oath","pea","eat","rain"]
* Output: ["eat","oath"]
* */
public class WordSearchII {
    static int row, column;
    static int minLength, maxLength;
    public static List<String> findWords(char[][] board, String[] words) {
        row = board.length;
        column = board[0].length;

        List<String> result = new ArrayList<>();
        for(String word: words) {
            for(int i=0; i<row;i++) {
                boolean flag = false;
                for(int j=0;j<column; j++) {
                    if(isPresentWord(board,word,i,j,0)) {
                        result.add(word);
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;
            }
        }
        return result;
        /*
        * Time Complexity = o(n * row * column * maxWordLength)
        * Space Complexity = o(1)
        * */
    }

    public static boolean isPresentWord(char[][] board,String word, int i, int j, int index) {
        if(index == word.length())
            return true;

        if(i<0 || j<0 || i==row || j==column)
            return false;

        if(board[i][j] != word.charAt(index))
            return false;

        board[i][j] = '#';
        index++;
        boolean result = false;
        result = isPresentWord(board,word,i-1,j,index) || isPresentWord(board,word,i+1,j,index)
                || isPresentWord(board,word,i,j-1,index) || isPresentWord(board,word,i,j+1,index);

        board[i][j] = word.charAt(index-1);
        return result;
    }

    public static List<String> findWordsBruteForce(char[][] board, String[] words) {
        minLength = Integer.MAX_VALUE;
        maxLength = Integer.MAX_VALUE;
        //Find min and max length from array of words
        for(String word: words) {
            int length = word.length();
            minLength = Math.min(minLength, length);
            maxLength = Math.max(maxLength, length);
        }

        //Form all strings that can be formed
        Set<String> setOfWords = new HashSet<>();
        for(int i=0;i<row; i++) {
            for(int j =0; j<column; j++) {
                formWords(board, i, j, setOfWords, new StringBuilder());
            }
        }

        List<String> result = new ArrayList<>();
        for(String word: words) {
            if(setOfWords.contains(word))
                result.add(word);
        }
        return result;
        /*
        * Time Complexity = o(n) + o(row * column * maxLength) +o(n)
        * Space Complexity = o(row * column * maxLength)
        * Stack Overflow Error
        * */
    }

    public static void formWords(char[][] board, int i, int j, Set<String> setOfWords, StringBuilder prefix) {
        int length = prefix.length();
        if(length >= minLength && length <= maxLength)
            setOfWords.add(prefix.toString());

        if(i<0 || j<0 || i==row || j==column || length > maxLength)
            return;

        prefix.append(board[i][j]);
        formWords(board, i-1, j, setOfWords,prefix);
        formWords(board, i+1, j, setOfWords,prefix);
        formWords(board, i, j-1, setOfWords,prefix);
        formWords(board, i, j+1, setOfWords,prefix);
    }

    public static class TrieNode {
        public HashMap<Character, TrieNode> map = new HashMap<>();
        public String word = null;

        public TrieNode(){}
    }

    public static List<String> findWordsUsingTrie(char[][] board, String[] words) {
        TrieNode root = new TrieNode();

        for(String word: words) {
            TrieNode node = root;
            for(char ch : word.toCharArray()) {
                if(node.map.containsKey(ch)) {
                    node = node.map.get(ch);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.map.put(ch, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }

        Set<String> resultSet = new HashSet<>();
        for(int i=0;i<row; i++) {
            for(int j=0;j<column; j++) {
                if(root.map.containsKey(board[i][j]))
                    dfs(board, i, j, resultSet, root);
            }
        }
        return new ArrayList<>(resultSet);
    }

    public static void dfs(char[][] board, int i, int j, Set<String> resultSet, TrieNode parent) {
        char ch = board[i][j];
        TrieNode currentNode = parent.map.get(ch);
        if(currentNode.word != null) {
            resultSet.add(currentNode.word);
            currentNode.word = null;
        }

        //masrk current cell as visited and then explore neighbors
        board[i][j] = '#';
        int[] rowOffset = { -1, 0, 1, 0 };
        int[] colOffset = { 0, 1, 0, -1 };
        for(int k=0;k<4;k++) {
            int rowIndex = i+rowOffset[k];
            int colIndex = j+colOffset[k];
//            if(rowIndex >= 0 && rowIndex < row
//                    && colIndex >= 0 && colIndex < column && currentNode.map.containsKey(board[rowIndex][colIndex]))
//                dfs(board,rowIndex,colIndex,resultSet,currentNode);
            if(rowIndex<0 || rowIndex==row || colIndex<0 || colIndex==column)
                continue;
            if(currentNode.map.containsKey(board[rowIndex][colIndex]))
                dfs(board,rowIndex,colIndex,resultSet,currentNode);
        }

        board[i][j] = ch;
        if(currentNode.map.isEmpty())
            parent.map.remove(ch);
    }

    public static void main(String[] args){
        // Brute Force Solution - Stack Overflow error
        /*System.out.println("\nResult: "+
                findWordsBruteForce(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},
                        new String[]{"oath","pea","eat","rain"}));
        System.out.println("Result: "+
                findWordsBruteForce(new char[][]{{'a','b'},{'c','d'}}, new String[]{"abcb"}));*/

        //Another Brute Force Solution - o(n * row * column * maxWordLength)
        System.out.println("Result: "+
                findWords(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},
                        new String[]{"oath","pea","eat","rain"}));
        System.out.println("Result: "+
                findWords(new char[][]{{'a','b'},{'c','d'}}, new String[]{"abcb"}));

        //Optimised Solution - using Trie
        System.out.println("Result: "+
                findWordsUsingTrie(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},
                        new String[]{"oath","pea","eat","rain"}));
        System.out.println("Result: "+
                findWordsUsingTrie(new char[][]{{'a','b'},{'c','d'}}, new String[]{"abcb"}));
    }
}
