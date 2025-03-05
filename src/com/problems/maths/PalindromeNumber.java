package com.problems.maths;

/*
* Question:
* Given an integer x, return true if x is a palindrome, and false otherwise.*/
public class PalindromeNumber {
    private static boolean isPalindromeNumber(int x) {
        int rem = 0;
        int num = x;
        int ans = 0;
        while(num > 0) {
            rem = num%10;
            ans = ans * 10 + rem;
            num = num/10;
        }
        return (x == ans);
        /*
        * Time Complexity = o(log x) - to the base 10
        * Space Complexity = o(1)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Number 123: "+isPalindromeNumber(123));
        System.out.println("Number -121: "+isPalindromeNumber(-121));
        System.out.println("Number 54245: "+isPalindromeNumber(54245));
        System.out.println("Number 10: "+isPalindromeNumber(10));
    }
}
