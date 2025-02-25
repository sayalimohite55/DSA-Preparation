package com.problems.arraysAndStrings;

/*
* Question:
* Assume you have a method isSubstring which checks if one word is a substring of another.
* Given two strings s1 and s2, write code to check if s2 is a rotation of s1
* Follow up : using only one call to isSubstring
* eg. waterbottle is a rotation of erbottlewat
* */
public class StringRotation {

    private static boolean bruteForceSolution(String s1, String s2) {
        /*
        * Intent: If we start rotating string one character by one character
        *         it will be equivalent to s2 at some point.
        * */
        StringBuilder sb = new StringBuilder(s1);
        int n = s1.length();
        while(n>0) {
            if(isSubString(sb.toString(),s2))
                return true;
            char ch = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(ch);
            n--;
        }
        return isSubString(sb.toString(),s2);
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    private static boolean optimisedSolution(String s1, String s2) {
        /*
         * Intent: If s2 is a rotation of s1 then if we search s2 inside s1+s1, we should find s2
         * */

        return isSubString(s1+s1,s2);
        /*
         * Time Complexity = o(1)
         * Space Complexity = o(1)
         * */
    }

    private static boolean isSubString(String s1, String s2) {
        return s1.contains(s2);
    }

    public static void main(String[] args) {
        //Brute Force Solution
        System.out.println("Is s2 a rotation of s1: " + bruteForceSolution("waterbottle","erbottlewat"));

        //Optimised solution
        System.out.println("Is s2 a rotation of s1: " + optimisedSolution("waterbottle","erbottlewat"));
    }
}
