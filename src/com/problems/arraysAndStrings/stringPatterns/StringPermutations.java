package com.problems.arraysAndStrings.stringPatterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* Question:
* Construct a method that when given a string finds all possible unique permutations of that string
* e.g.
* permute("abc") => ["abc", "acb", "bac", "bca", "cab", "cba"]
* permute("aba") => ["aba", "aab", "baa"]
* */
public class StringPermutations {
    static Set<String> result;
    public static List<String> permute(String str) {
        result = new HashSet<>();
        result.add(str);
        if(str.length() > 1) {
            permute(str,"");
        }
        return new ArrayList<>(result);
    }

    public static void permute(String str,String prefix) {
        if(str.isEmpty()) {
            result.add(prefix);
            return;
        }

        for(int i=0; i<str.length(); i++) {
            String remainingStr = str.substring(0,i) + str.substring(i+1);
            permute(remainingStr, prefix+str.charAt(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("abc => "+permute("abc"));
        System.out.println("aba => "+permute("aba"));
        System.out.println("bisleri => "+permute("bisleri"));
        System.out.println("=> "+permute(""));
        /*
         * Time Complexity = o(n!)
         * Space Complexity = o(n!)
         * */
    }
}
