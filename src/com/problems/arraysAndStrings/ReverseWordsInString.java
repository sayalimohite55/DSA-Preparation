package com.problems.arraysAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* Question:
* Given an input string s, reverse the order of the words. A word is defined as a sequence of
* non-space characters. The words in s will be separated by at least one space.
* Return a string of the words in reverse order concatenated by a single space.
* Note that s may contain leading or trailing spaces or multiple spaces between two words.
* The returned string should only have a single space separating the words. Do not include any extra spaces.
*
* Examples:
* Input: s = "the sky is blue"      Output: "blue is sky the"
*
* Input: s = "  hello world  "      Output: "world hello"
* Explanation: Your reversed string should not contain leading or trailing spaces.
*
* Input: s = "a good   example"     Output: "example good a"
* Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
*
* Constraints:
* 1 <= s.length <= 104
* s contains English letters (upper-case and lower-case), digits, and spaces ' '.
* There is at least one word in s.
*
* Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1)
* extra space?
* */
public class ReverseWordsInString {

    public static String reverseWordsApparoachI(String s) {
        s = s.replaceAll("\\s+"," ");
        StringBuilder sb = new StringBuilder();
        for(String str: s.split(" ")) {
            if(!str.equals(" ")) {
                sb.insert(0, str.trim()+" ");
            }
        }
        return sb.toString().trim();
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static String reverseWordsApparoachII(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));
        Collections.reverse(list);
        return String.join(" ",list);
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        //Approach I
        System.out.println("Result: " + reverseWordsApparoachI("the sky is blue"));
        System.out.println("Result: " + reverseWordsApparoachI("  hello world  "));
        System.out.println("Result: " + reverseWordsApparoachI("a good   example"));

        //Approach II
        System.out.println("\nResult: " + reverseWordsApparoachII("the sky is blue"));
        System.out.println("Result: " + reverseWordsApparoachII("  hello world  "));
        System.out.println("Result: " + reverseWordsApparoachII("a good   example"));
    }
}
