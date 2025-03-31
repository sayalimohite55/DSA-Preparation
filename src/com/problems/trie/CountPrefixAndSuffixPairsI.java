package com.problems.trie;

/*
* Question:
* You are given a 0-indexed string array words. Let's define a boolean function isPrefixAndSuffix that takes two strings,
* str1 and str2: isPrefixAndSuffix(str1, str2) returns true if str1 is both a prefix and a suffix of str2,
* and false otherwise. For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa"
* and also a suffix, but isPrefixAndSuffix("abc", "abcd") is false.
* Return an integer denoting the number of index pairs (i, j) such that i < j, and isPrefixAndSuffix(words[i], words[j])
* is true.
* */
public class CountPrefixAndSuffixPairsI {

    private static int countPrefixSuffixPairs(String[] words) {
        int count = 0, n = words.length;
        for(int i=0;i<n ; i++) {
            for(int j= i+1; j<n; j++) {
                int l1 = words[i].length();
                int l2 = words[j].length();

                if(l1 <= l2 && words[j].indexOf(words[i]) == 0
                        && words[j].lastIndexOf(words[i]) == l2-l1)
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2 * m) --> where n is length of words array and m is length of largest word
        * Space Complexity = o(1)
        * */
    }

    private static int countPrefixSuffixPairsOptimised(String[] words) {
        int count = 0, n = words.length;
        for(int i=0;i<n ; i++) {
            for(int j= i+1; j<n; j++) {
                int l1 = words[i].length();
                int l2 = words[j].length();

                if(l1 > l2)
                    continue;

                int k=0;
                for(; k<l1;k++)
                    if(words[i].charAt(k) != words[j].charAt(k))
                        break;

                if(k == l1) {

                    for(k=0; k<l1; k++)
                        if(words[i].charAt(k) != words[j].charAt(l2-l1+k))
                            break;
                    if(k == l1)
                        count++;
                }
            }
        }
        return count;
        /*
         * Time Complexity = o(n^2 * m) --> where n is length of words array and m is length of largest word
         * Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        //Brute force Solution - o(n^2 * m)
        System.out.println("Count: "+countPrefixSuffixPairs(new String[]{"bb","bb"}));
        System.out.println("Count: "+countPrefixSuffixPairs(new String[]{"a","aba","ababa","aa"}));
        System.out.println("Count: "+countPrefixSuffixPairs(new String[]{"pa","papa","ma","mama"}));
        System.out.println("Count: "+countPrefixSuffixPairs(new String[]{"abab","ab"}));

        //Optimised Solution - o(n^2 * m)
        System.out.println("\n\nCount: "+countPrefixSuffixPairsOptimised(new String[]{"bb","bb"}));
        System.out.println("Count: "+countPrefixSuffixPairsOptimised(new String[]{"a","aba","ababa","aa"}));
        System.out.println("Count: "+countPrefixSuffixPairsOptimised(new String[]{"pa","papa","ma","mama"}));
        System.out.println("Count: "+countPrefixSuffixPairsOptimised(new String[]{"abab","ab"}));
    }
}
