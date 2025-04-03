package com.problems.arraysAndStrings.stringPatterns;

/*
* Question:
* Given an array of characters chars, compress it using the following algorithm:
* Begin with an empty string s. For each group of consecutive repeating characters in chars:
*   If the group's length is 1, append the character to s.
*   Otherwise, append the character followed by the group's length.
* The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
* Note that group lengths that are 10 or longer will be split into multiple characters in chars.
* After you are done modifying the input array, return the new length of the array.
* You must write an algorithm that uses only constant extra space.
*
* Example:
* Input: chars = ["a","a","b","b","c","c","c"]
* Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
* Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
* */
public class StringCompression {
    private static int compress(char[] arr) {
        int i =0, k=0, n=arr.length;
        while(i < n) {
            int groupLength = 1;
            while(i+groupLength < n && arr[i] == arr[i+groupLength])
                groupLength++;

            arr[k++] = arr[i];
            if(groupLength > 1) {
                for(char ch : String.valueOf(groupLength).toCharArray())
                    arr[k++] = ch;
            }
            i +=groupLength;
        }
        return k;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(1)
        * */
    }

    private static void print(char[] arr, int n) {
        System.out.println();
        for(int i=0;i<n; i++)
            System.out.print(arr[i]+" ");
    }

    public static void main(String[] args) {
        char[] arr = new char[]{'a','a','b','b','c','c','c'};
        int n = compress(arr);
        print(arr, n);

        arr = new char[]{'a'};
        n = compress(arr);
        print(arr, n);

        arr = new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'};
        n = compress(arr);
        print(arr, n);
    }
}
