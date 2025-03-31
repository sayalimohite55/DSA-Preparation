package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* You are given an array of strings words and a string chars.
* A string is good if it can be formed by characters from chars
* (each character can only be used once).
* Return the sum of lengths of all good strings in words.
* */
public class CountWordsCanFormByCharArray {
    public static int countCharactersUsingMap(String[] words, String chars) {
        int result = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch: chars.toCharArray())
            map.put(ch,map.getOrDefault(ch,0)+1);

        for(String word: words) {
            HashMap<Character, Integer> wordMap = new HashMap<>();
            for(char ch: word.toCharArray())
                wordMap.put(ch,wordMap.getOrDefault(ch,0)+1);

            boolean flag = true;
            for(Map.Entry<Character,Integer> entry: wordMap.entrySet()) {
                char key = entry.getKey();
                int val = entry.getValue();

                if(map.containsKey(key) && map.get(key) >= val);
                else {
                    flag=false;
                    break;
                }
            }
            if(flag) {
                result += word.length();
            }
        }
        return result;
        /*
        * Time Complexity : o(charsLength) + o(n * m) = o(n * m)
        * Space Complexity : o(charsLength) + o(m) = o(charsLength + m)
        * where --> charsLength is length of chars string,
        *           n is count of words and
        *           m is length of largest word from list
        * */
    }

    private static int countCharacters(String[] words, String chars) {
        int[] countChars = new int[26];
        for(char ch : chars.toCharArray())
            countChars[ch-'a'] ++;

        int result = 0;
        for(String word: words) {
            if(canFormString(word, countChars)) {
                result += word.length();
            }
        }
        return result;
        /*
        * Time Complexity = o(n * m) --> n is length of list and m is length of largest word
        * Space Complexity = o(1)
        * */
    }

    private static boolean canFormString(String word, int[] charCount) {
        char[] wordCount = new char[26];
        for(char ch : word.toCharArray()) {
            wordCount[ch-'a'] ++;
            if(wordCount[ch-'a'] > charCount[ch-'a'])
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        //Solution using HashMap- o(n * m )
        System.out.println("Result: "+countCharactersUsingMap(new String[]{"cat","bt","hat","tree"},"atach"));
        System.out.println("Result: "+countCharactersUsingMap(new String[]{"hello","world","leetcode"},"welldonehoneyr"));

        //Optimised Solution - o(n * m)
        System.out.println("\nResult: "+countCharacters(new String[]{"cat","bt","hat","tree"},"atach"));
        System.out.println("Result: "+countCharacters(new String[]{"hello","world","leetcode"},"welldonehoneyr"));
    }
}
