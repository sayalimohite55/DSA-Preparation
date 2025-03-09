package com.problems.dp;

import java.util.*;

public class GenerateParentheses {
    private static List<String> bruteForceSolutionUsingQueue(int n) {
        /*
         * Intent:
         * Generate all possible strings with ( and ) of length 2*n
         * Every time string length is equal to 2*n, check if string is valid by verifying parentheses sequence
         * If string is valid add it to list
         * */
        List<String> list = new ArrayList<>();
        Queue<String> q = new LinkedList<>(List.of(""));

        while(!q.isEmpty()) {
            String s = q.remove();
            if(s.length() == 2*n) {
                if(isValidParentheses(s))
                    list.add(s);
                continue;
            }
            q.add(s+"(");
            q.add(s+")");
        }
        return list;
        /*
         * Time Complexity = o(2^n * n)
         * Space Complexity = o(2^n * n)
         *
         * Problem with this approach is that, it generates too many invalid strings and hence it is highly likely
         * that the program will fail with stack overflow exception.
         * */
    }

    private static boolean isValidParentheses(String str) {
        Stack<Character> stack = new Stack<>();
        for(char ch : str.toCharArray()) {
            if(ch == '(')
                stack.push(ch);
            else if(ch == ')' && (stack.isEmpty() || stack.pop() != '('))
                return false;
        }
        return stack.isEmpty();
    }

    private static List<String> solutionUsingRecursion(int n) {
        /*
         * Intent:
         * Generate all possible strings with ( and ) of length 2*n
         * But idea here is to maintain count of ( and ) for backtracking purpose
         * and to make sure to generate valid string combinations
         *
         * We will generate string of length 2 * n and maintain count for open and close bracket
         * if openBracketCount < n (meaning less than half of final string)
         *      we append ( and increment openBracketCount
         * else if openBracketCount > closeBracketCount
         *      we append ) and increment closeBracketCount
         * */
        List<String> list = new ArrayList<>();
        generateParenthesesUsingBacktracking(n, list, 0, 0, new StringBuilder());
        return list;
        /*
        * Time Complexity: o(4^n /n * root(n))
        * Here, we only track valid prefixes during backtracking procedure.
        * When considering each valid string, it is important to note that we use a mutable instance
        * (StringBuilder in Java) and as a result, in order to add each instance of a valid string to answer,
        * we must first convert it to a string. This process brings an additional n factor in the time complexity.
        *
        * Space Complexity: o(n)
        * The space complexity of a recursive call depends on the maximum depth of the recursive call stack,
        * which is 2n. As each recursive call either adds a left parenthesis or a right parenthesis,
        * and the total number of parentheses is 2n. Therefore, at most O(n) levels of recursion will be created,
        * and each level consumes a constant amount of space.
         */
    }

    private static void generateParenthesesUsingBacktracking(int n,
                                                             List<String> list,
                                                             int openBracketCount,
                                                             int closeBracketCount,
                                                             StringBuilder prefix) {
        if(prefix.length() == 2*n) {
            list.add(prefix.toString());
            return;
        }

        if(openBracketCount < n) {
            generateParenthesesUsingBacktracking(n, list,
                    openBracketCount+1,closeBracketCount, prefix.append("("));
            prefix.deleteCharAt(prefix.length()-1);
        }

        if(openBracketCount > closeBracketCount) {
            generateParenthesesUsingBacktracking(n, list,
                    openBracketCount,closeBracketCount+1, prefix.append(")"));
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    private static void printOutput(List<String> list) {
        for(String s : list) {
            System.out.print("  " + s);
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        //Brute Force Solution -o(2^n * n)
        printOutput(bruteForceSolutionUsingQueue(5));

        //better solution using backtracking - o(4^n /n * root(n)) Technically half of first approach
        printOutput(solutionUsingRecursion(5));
    }
}
