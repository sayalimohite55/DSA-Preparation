package com.problems.arraysAndStrings;

import java.util.Arrays;

/*
* Question:
* Write a method to replace all spaces in a string with '%20'.
* You may assume that the string has sufficient space at the end to hold additional characters
* and that you're given a true length of the string. Replace should be done in-place
* */
public class URLify {

    private static String solutionUsingStringReplaceI(String str) {
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == ' ')
                str = str.replaceFirst(" ","%20");
        }
        return str;
        /*
        * Time Complexity = o(n) * o(n) --> o(n) due to for loop and o(n) for replace function
        * Space Complexity = o(1);
        * */
    }

    private static String solutionUsingStringReplaceII(String str) {
        str = str.trim();
        return str.replaceAll(" ","%20");
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1);
        * */
    }

    private static String betterSolutionUsingArrayInput(char[] arr) {
        int index = arr.length-1;
        for(int i=arr.length-1; i>=0 && arr[i] == ' '; i--) {
                index --;
        }

        int i=arr.length-1;
        while(index >= 0 && i-2 >=0) {
            if(arr[i] == ' ' && arr[index] != ' ') {
                //swap
                arr[i] = arr[index];
                arr[index] = ' ';
                i--;
                index--;
            } else if(arr[i] == ' ' && arr[index] == ' ') {
                arr[i] = '0';
                arr[i-1] = '2';
                arr[i-2] = '%';
                i -= 2;
                i--;
                index --;
            } else
                index--;
        }
        return Arrays.toString(arr);
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1);
        * */
    }

    public static void main (String[] args) {
        System.out.println("output: " + solutionUsingStringReplaceI("Mr John Smith   "));
        System.out.println("output: " + solutionUsingStringReplaceII("Mr John Smith   "));

        char[] arr = "Mr John Smith sam      ".toCharArray();
        System.out.println("output: " + betterSolutionUsingArrayInput(arr));
    }
}
