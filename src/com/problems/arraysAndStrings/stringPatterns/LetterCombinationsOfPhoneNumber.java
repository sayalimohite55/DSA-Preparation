package com.problems.arraysAndStrings.stringPatterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Question:
* Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
* Return the answer in any order.
* A mapping of digits to letters (just like on the telephone buttons) is given below.
* Note that 1 does not map to any letters.
*
* Examples:
* Input: digits = "23"  Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
* Input: digits = ""    Output: []
* Input: digits = "2"   Output: ["a","b","c"]
*
* Constraints: 0 <= digits.length <= 4  and digits[i] is a digit in the range ['2', '9'].
* */
public class LetterCombinationsOfPhoneNumber {
    private static Map<Character,String> map;

    private static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if(digits.length() != 0) {
            map = new HashMap<>();
            map.put('2', "abc");
            map.put('3',"def");
            map.put('4',"ghi");
            map.put('5',"jkl");
            map.put('6',"mno");
            map.put('7',"pqrs");
            map.put('8',"tuv");
            map.put('9',"wxyz");

            backtrack(digits,0,new StringBuilder(), result);
        }

        return result;
    }

    private static void backtrack(String digits, int index, StringBuilder currentString, List<String> result) {
        if(currentString.length() == digits.length()) {
            result.add(currentString.toString());
            return;
        }

        if(index == digits.length())
            return;

        char currentDigit = digits.charAt(index);
        if(map.containsKey(currentDigit)) {
            for (char ch : map.get(currentDigit).toCharArray()) {
                currentString.append(ch);
                backtrack(digits, index+1, currentString, result);
                currentString.deleteCharAt(currentString.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        //Solution using hashmap and backtracking
        System.out.println("Result: "+ letterCombinations("23"));
        System.out.println("Result: "+ letterCombinations(""));
        System.out.println("Result: "+ letterCombinations("2"));
        System.out.println("Result: "+ letterCombinations("232323"));
        /*
        * Time Complexity = o(3^n)
        * Space Complexity = o(3^n)
        * where --> n is length digits string and as each digit has atleast 3 characters
        * */
    }
}
