package com.problems.maths;

/*
* Question: Reverse Integer
* Given a signed 32-bit integer x, return x with its digits reversed.
* If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1],
* then return 0.
* Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
* */
public class ReverseInteger {

    private static int reverseInteger(int x) {
        /*
        * Intent:
        * Logic to reverse a number is as follows :
        *   reminder = x % 10
        *   x= x / 10
        *   num = num * 10 + reminder
        *   Repeat till x becomes 0
        *
        * Here in order to make sure that reversed number is in 32 bit signed range,
        * use Integer.MAX_VALUE and Integer.MIN_VALUE for comparison.
        *
        * Integer.MAX_VALUE = 2,147,483,647, or 2^31 - 1
        * Integer.MIN_VALUE = -2,147,483,648 or 2^31
        * */
        int num = 0;
        while(x != 0) {
            int rem = x % 10;
            x = x/10;

            if(num > Integer.MAX_VALUE/10 || (num == Integer.MAX_VALUE/10 && rem > 7))
                return 0;
            if(num < Integer.MIN_VALUE/10 || (num == Integer.MIN_VALUE/10 && rem < -8))
                return 0;
            num = num * 10 + rem;
        }
        return num;
        /*
        * Time Complexity = o(log x)
        * Space Complexity = o(1)
        * */
    }

    public static void main(String args[]) {
        System.out.println("Reverse of 321: "+reverseInteger(321));
        System.out.println("Reverse of -123: "+reverseInteger(-123));
        System.out.println("Reverse of 1200: "+reverseInteger(1200));
        System.out.println("Reverse of 1234567878: "+reverseInteger(1234567878));
    }
}
