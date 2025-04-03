package com.problems.trie;
/*
* Question:
* A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a
* dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
* Implement the Trie class:
*   Trie() Initializes the trie object.
*   void insert(String word) Inserts the string word into the trie.
*   boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
*   boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix, and false otherwise.
* */
public class Trie {
    public class TrieNode {
        int n = 26;
        TrieNode[] links;
        boolean isEnd;
        String word;

        public TrieNode() {
            links = new TrieNode[n];
        }

        public boolean containsKey(char ch) {
            return links[ch-'a'] != null;
        }

        public TrieNode get(char ch){
            return links[ch-'a'];
        }

        public void put(char ch , TrieNode node) {
            links[ch-'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getWord() {
            return word;
        }
    }

    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        int n = word.length();
        for(int i=0; i<n; i++) {
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
        node.setWord(word);
    }

    public TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        int n= prefix.length();

        for(int i=0;i<n; i++) {
            char ch = prefix.charAt(i);
            if(node.containsKey(ch))
                node = node.get(ch);
            else
                return null;
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node!=null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}
