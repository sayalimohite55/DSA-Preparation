package com.problems.arraysAndStrings.stringPatterns;

import java.util.*;

/*
* Question:
* Sort n strings in a custom sort manner. They had a way to sort strings.
* Eg : www.google.com -> the string to be considered to be sorted was com.google.www.
* Given such n string first convert the strings and then sort it
* */
public class CustomSortGivenStrings {
    public static List<String> customSortStrings(String[] strings) {
        SortedSet<String> result = new TreeSet<>();

        for(String str : strings) {
            String s[] = str.split("\\.");
            Arrays.sort(s);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<s.length; i++) {
                sb.append(s[i]);
                if(i < s.length-1)
                    sb.append(".");
            }
            result.add(sb.toString());
        }
        return new ArrayList<>(result);
        /*
        * Time Complexity = o(n * m)
        * Space Complexity = o(n * m)
        * where --> n is length of input string array
        *           m is length of largest string
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result: "+customSortStrings(new String[]{"www.google.com","www.yahoo.com","www.gmail.co.in","www.indigo.co.ca"}));
    }
}
