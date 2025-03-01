package com.problems.arraysAndStrings;

/*
* Question: Median of Two Sorted Array
* Given two sorted arrays nums1 and nums2 of size m and n respectively,
* return the median of the two sorted arrays.
* Follow up : The overall run time complexity should be O(log (m+n)).
* Median is middle most element for odd length and sum of two middle most elements for even length
* */
public class MedianOfTwoSortedArrays {

    private static double bruteForceSolutionUsingMerging(int nums1[], int nums2[]) {
        /*
        * Intent: 1. Merge two sorted arrays into third array
        *         2. Find median
        * */
        int length = nums1.length + nums2.length;
        int[] arr = new int[length];
        int i=0, j=0, k=0;
        while(i<nums1.length && j<nums2.length) {
            if (i < nums1.length && nums1[i] < nums2[j]) {
                arr[k++] = nums1[i++];
            } else if (j < nums2.length && nums1[i] > nums2[j]) {
                arr[k++] = nums2[j++];
            } else {
                arr[k++] = nums1[i++];
                arr[k++] = nums2[j++];
            }
        }

        while(i<nums1.length){
            arr[k++] = nums1[i++];
        }

        while(j<nums2.length){
            arr[k++] = nums2[j++];
        }

        int mid = length/2;
        if(length%2 == 0) {//even
            return (arr[mid-1] + arr[mid])/2.0;
        } else //odd
            return arr[mid];
        /*
        *   Time Complexity = o(n) + o(m) = o(m+n)
        *   Space Complexity = o(n) + o(m) = o(m+n)
        * */
    }

    private static double solutionWithoutMergingArrays(int nums1[], int nums2[]) {
        /*
         * Intent: 1. Traverse two sorted arrays to find middle-most elements
         *         2. Find median
         * */
        int length = nums1.length + nums2.length;
        int m1=0, m2=0, i=0, j=0;
        for(int count=0; count<=length/2; count++) {
            m1=m2;
            if(i!=nums1.length && j!=nums2.length) {
                m2 = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
            } else if(i!=nums1.length) {
                m2 = nums1[i++];
            } else
                m2 = nums2[j++];
        }
        if(length%2 == 0) {
            return (double)(m1+m2)/2;
        }
        return m2;
        /*
         *   Time Complexity =  o(log (m+n))
         *   Space Complexity = o(1)
         * */
    }

    public static void main(String[] args) {
        /* ******* Odd no of elements use-case ******* */
        int[] nums1 = new int[] {1,3};
        int[] nums2 = new int[] {2};

        //Brute force solution - o(n) + o(m) =o(n+m)
        System.out.println("Median: "+ bruteForceSolutionUsingMerging(nums1, nums2));

        //Optimal solution - o(log (m+n))
        System.out.println("Median: "+ solutionWithoutMergingArrays(nums1, nums2));

        /* ******* Even no of elements use-case ******* */
        nums1 = new int[] {1,3};
        nums2 = new int[] {2,4};

        //Brute force solution - o(n) + o(m) =o(n+m)
        System.out.println("Median: "+ bruteForceSolutionUsingMerging(nums1, nums2));

        //Optimal solution - o(log (m+n))
        System.out.println("Median: "+ solutionWithoutMergingArrays(nums1, nums2));
    }
}
