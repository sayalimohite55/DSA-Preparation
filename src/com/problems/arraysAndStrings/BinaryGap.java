package com.problems.arraysAndStrings;

/*
* Question:
* A binary gap within a positive integer N is any maximal sequence of consecutive zeros
* that is surrounded by ones at both ends in the binary representation of N.
*
* eg:
*  given N = 1041 the function should return 5, because N has binary representation 10000010001
*  and so its longest binary gap is of length 5
* */
public class BinaryGap {

    private static int binaryGap(int N) {
        char[] binaryArray = Integer.toBinaryString(N).toCharArray();
        int n = binaryArray.length;

        int i=0, j=1, maxGap=0;
        while(j<n) {
            if(binaryArray[i] != '1') {
                i++;
            } else if(binaryArray[j] == '1') {
                maxGap = Math.max(maxGap, j-i-1);
                i = j++;
            } else {
                j++;
            }
        }
        return maxGap;
        /*
        * Time Complexity = o(n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        System.out.println("BinaryGap: "+binaryGap(1041));
        System.out.println("BinaryGap: "+binaryGap(15));
        System.out.println("BinaryGap: "+binaryGap(1376796946));
    }
}
