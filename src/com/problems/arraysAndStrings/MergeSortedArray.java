package com.problems.arraysAndStrings;

/*
* Questions:
* You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
* and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
* Merge nums1 and nums2 into a single array sorted in non-decreasing order.
* */
public class MergeSortedArray {

    private static void mergeSortedArraysUsingExtraSpace(int[] arr, int m, int[] b, int n) {
        /*
        * Intent:
        * Create a temp array and copy array 'a' into temp.
        * Then merge array temp and array b into 'a'
        * */
        int[] a = new int[m];
        System.arraycopy(arr, 0, a, 0, m);

        int i=0,j=0,k=0;
        while(i<m || j<n) {
            if(i != m && j != n)
                arr[k++] = a[i] < b[j] ? a[i++] : b[j++];
            else if (i != m)
                arr[k++] = a[i++];
            else
                arr[k++] = b[j++];
        }
        /*
        * Time Complexity = o(m+n)
        * Space Complexity = o(m)
        * */
    }

    private static void mergeSortedArrays(int[] a, int m, int[] b, int n) {
        /*
         * Intent:
         * Idea is to start placing items from rightmost side for in-place sorting
         * */


        int i=m-1, j=n-1, k=a.length-1;
        while((i >= 0 || j >= 0) && k >= 0){
            if(i >= 0 && j >= 0)
                a[k--] = a[i] > b[j] ? a[i--] : b[j--];
            else if(i >= 0)
                a[k--] = a[i--];
            else
                a[k--] = b[j--];
        }
        /*
         * Time Complexity = o(m+n)
         * Space Complexity = o(1)
         * */
    }

    private static void printOutput(int[] arr) {
        System.out.println();
        for(int i =0; i<arr.length; i++)
            System.out.print(" " + arr[i]);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[] {2,5,6};
        int n = 3;

        //Brute Force Solution using extra space - o(m+n)
        mergeSortedArraysUsingExtraSpace(nums1,m,nums2,n);
        printOutput(nums1);

        //Optimised solution - without using extra space - o(m+n)
        nums1 = new int[] {1,2,3,0,0,0};
        nums2 = new int[] {2,5,6};
        //Brute Force Solution using extra space - o(m+n)
        mergeSortedArrays(nums1,m,nums2,n);
        printOutput(nums1);
    }
}
