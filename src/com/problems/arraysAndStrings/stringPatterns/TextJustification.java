package com.problems.arraysAndStrings.stringPatterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* Question:
* Given an array of strings words and a width maxWidth, format the text such that each line has
* exactly maxWidth characters and is fully (left and right) justified.You should pack your words
* in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' '
* when necessary so that each line has exactly maxWidth characters. Extra spaces between words
* should be distributed as evenly as possible. If the number of spaces on a line does not divide
* evenly between words, the empty slots on the left will be assigned more spaces than the slots
* on the right. For the last line of text, it should be left-justified, and no extra space is
* inserted between words. Note: A word is defined as a character sequence consisting of non-space
* characters only.Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
* The input array words contains at least one word.
*
* Example 1: Input: words = ["This", "is", "an", "example", "of", "text", "justification."],
* maxWidth = 16
* Output:["This    is    an","example  of text","justification.  "]
*/
public class TextJustification {
    private static List<String> fullJustify(String[] words, int maxWidth) {
        /*
        * Intent:
        * Identify how many words can be packed into a line
        * Check how many spaces and extra spaces to be added in a line
        * then start appending word and spaces accordingly into the line
        * */
        List<String> result = new ArrayList<>();
        int l=0,r=0,n=words.length;

        while(l<n) {
            int length = 0;
            while(r<n && length + words[r].length() +1 <= maxWidth+1) {
                //Checking +1 for case of adding a space between two words
                length += words[r++].length()+1;
            }

            int spaces = 1, extraSpaces = 0;
            //Check if it is a last line or last word
            if(r!=n && l+1 != r) {
                spaces = (maxWidth - length + 1)/ (r-l-1)+1;
                extraSpaces = (maxWidth - length + 1)% (r-l-1);
            }

            StringBuilder sb = new StringBuilder(words[l++]);
            while(l<r) {
                for(int i=0;i<spaces; i++)
                    sb.append(" ");
                if(extraSpaces-- > 0)
                    sb.append(" ");
                sb.append(words[l++]);
            }
            //Check if any remaining spaces
            int remainingSpaces = maxWidth = sb.length();
            while(remainingSpaces-- > 0)
                sb.append(" ");

            result.add(sb.toString());
        }
        return result;
        /*
        * Time Complexity = o(n * k) - o(m)
        * where --->
        *           n is length of words list
        *           k is total number of characters added
        *           m is sum of all characters in all words
        * Space Complexity = o(maxWidth) --> for each row we have used fixed maxWidth space
        * */
    }

    private static void print(List<String> list) {
        for(String str: list) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        List<String> result = fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."},16);
        System.out.println("\nResult: ");print(result);

        result = fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"},16);
        System.out.println("\nResult: ");print(result);

        result = fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"},20);
        System.out.println("\nResult: ");print(result);
    }
}
