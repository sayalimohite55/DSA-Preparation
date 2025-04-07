package com.problems.simulation;

/*
* Question:
* Given two integers num1 and num2, return the sum of the two integers.
* */
public class AddTwoIntegers {
    private static int sum(int n1, int n2) {
        return n1+n2;
        /*
        * Time Complexity = o(1)
        * Space Complexity = o(1)
        * */
    }
    public static void main(String[] args) {
        System.out.println("Addition: "+sum(5,7));
    }
}
