package com.problems.arraysAndStrings;

/*
* Question:
* Implement a method to perform basic string compression using the counts of repeated characters.
* eg. aabcccccaaa => a2b1c5a3
* If the compressed string would not become smaller than original string then return original string.
* You can assume that string has only uppercase and lowercase letters.
* */
public class StringCompression {
    private static String compressString(String str) {
        StringBuilder sb = new StringBuilder();
        int n = str.length();
        for(int i=0;i<n;){
            char ch = str.charAt(i);
            int j = i+1;
            int count = 1;
            while(j<n && str.charAt(j) == ch) {
                j++;
                count++;
            }
            sb.append(ch+""+count);
            i=j;
        }
        if(sb.length() < n)
            return sb.toString();
        return str;

        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n) --due to string builder for new string
        * */
    }

    public static void main(String[] args) {
        System.out.println("Compressed String: "+ compressString("aabcccccaaa"));
    }
}
