package com.problems.arraysAndStrings;

/*
* Question:
* Given a string s and an integer k, return the maximum number of vowel letters in any substring of s
* with length k. Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
*
* Examples:
*
* Input: s = "abciiidef", k = 3      Output: 3
* Explanation: The substring "iii" contains 3 vowel letters.
*
* Input: s = "aeiou", k = 2          Output: 2
* Explanation: Any substring of length 2 contains 2 vowels.
*
* Input: s = "leetcode", k = 3        Output: 2
* Explanation: "lee", "eet" and "ode" contain 2 vowels.
*
* Constraints:
* 1 <= s.length <= 105
* s consists of lowercase English letters.
* 1 <= k <= s.length
* */
public class MaxCountOfVowelsInSubstring {
    public static int maxVowels(String s, int k) {
        int i=0, j=0, n = s.length();
        int maxCount = 0, count = 0;

        while(j< n) {
            if(i == 0) {
                for(j = i; j < i+k && j < n; j++)
                    if(isVowel(s.charAt(j)))
                        count++;
            } else {
                if(isVowel(s.charAt(j)))
                    count++;
                j++;
            }

            if(count == k)
                return count;
            maxCount = Math.max(maxCount, count);
            if(isVowel(s.charAt(i)))
                count--;

            i++;
        }
        return maxCount;
    }

    public static boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }

    public static void main(String[] args) {
        System.out.println("Result: "+maxVowels("abciiidef",3));
        System.out.println("Result: "+maxVowels("aeiou",2));
        System.out.println("Result: "+maxVowels("leetcode",3));
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }
}
