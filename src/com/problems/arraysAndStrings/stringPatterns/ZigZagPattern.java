package com.problems.arraysAndStrings.stringPatterns;

import java.util.ArrayList;

/*
* Question:
* The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
* (you may want to display this pattern in a fixed font for better legibility)
* P   A   H   N
* A P L S I I G
* Y   I   R
* And then read line by line: "PAHNAPLSIIGYIR"
* Write the code that will take a string and make this conversion given a number of rows:
* string convert(string s, int numRows);
* */
public class ZigZagPattern {
    private static String zigZagPattern(String s, int numRows) {
        /*
        * Intent:
        * Create list of row strings and append characters to lists in zigZag pattern
        * */
        if(numRows == 1 || s.length() < numRows)
            return s;

        int n = s.length();
        ArrayList<StringBuilder> list = new ArrayList<>();
        for(int i=0;  i<n; i++) {
            list.add(new StringBuilder());
        }

        int count, i=0;
        while(i<n) {
            for(int j=0; j<numRows && i<n; j++, i++) {
                list.get(j).append(s.charAt(i));
            }

            //Now to append in zigzag pattern
            count = numRows-2;
            while(count > 0 && i<n) {
                list.get(count).append(s.charAt(i));
                i++;
                count--;
            }
        }

        StringBuilder result = new StringBuilder();
        for(StringBuilder sb : list) {
            result.append(sb);
        }

        return result.toString();
        /*
        * Time Complexity : o(numRows + n * numRows + numRows) = o(n * numRows)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        System.out.println("output string: "+zigZagPattern("PAYPALISHIRING",3));
        System.out.println("output string: "+zigZagPattern("PAYPALISHIRING",4));
        System.out.println("output string: "+zigZagPattern("A",1));
    }
}
