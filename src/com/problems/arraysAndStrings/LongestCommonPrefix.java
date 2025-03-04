package com.problems.arraysAndStrings;

/*
* Question : Longest Common Prefix
* Write a function to find the longest common prefix string amongst an array of strings.
* If there is no common prefix, return an empty string ""
* */
public class LongestCommonPrefix {

    private static String longestCommonPrefix(String strs[]) {
        /*
        * Intent:
        * Take first string as prefix
        * Iterate through all other strings to find prefix
        * */
        String prefix = strs[0];
        int i=0;
        while(i < prefix.length()) {
            char ch = prefix.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if(ch != strs[j].charAt(i)) {
                    prefix = prefix.substring(0, i);
                    break;
                }
            }
            i++;
        }
        return prefix;
        /*
        * Time Complexity = o(s) - s is the count of all characters in all strings
        * Space Complexity = o(1) - as we only used constant extra space
        * */
    }

    public static void main(String[] args) {
        System.out.println("LongestCommonPrefix: " + longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println("LongestCommonPrefix: " + longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}
