package com.problems.arraysAndStrings;

import java.util.Stack;

/*
* Question:
* Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
* determine if the input string is valid.
* An input string is valid if:
* - Open brackets must be closed by the same type of brackets.
* - Open brackets must be closed in the correct order.
* - Every close bracket has a corresponding open bracket of the same type.
* */
public class ValidParanthesis {
    private static boolean isValidParanthesis(String str) {
        Stack<Character> stack = new Stack<>();
        int n = str.length();
        for(int i=0;i<n;i++) {
            char ch = str.charAt(i);
            if(ch == '{' || ch == '(' || ch =='[') {
                stack.push(ch);
            } else {
                char prevChar = stack.pop();
                if((ch == '}' && prevChar != '{') || (ch == ')' && prevChar != '(') || (ch == ']' && prevChar != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
        /*
        * Time Complexity = o(n) - n is length of string
        * Space Complexity = o(n) - as we may end up adding all characters into stack
        * */
    }

    public static void main(String[] args) {
        System.out.println("Is valid paranthesis for (): "+isValidParanthesis("()"));
        System.out.println("Is valid paranthesis for ()[]{}: "+isValidParanthesis("()[]{}"));
        System.out.println("Is valid paranthesis for (]: "+isValidParanthesis("(]"));
        System.out.println("Is valid paranthesis for ([]): "+isValidParanthesis("([])"));
    }
}
