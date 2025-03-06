package com.problems.arraysAndStrings;

/*
* Question:
* Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
*/
public class RotateArray {
    private static void bruteForceSolution(int[] arr, int k) {
        /*
        * Intent:
        * Rotate each element by 1 place at a time
        * Repeat process for k times
        * */

        if(k==0 || arr.length <=1) {
            printOutput(arr);
            return;
        }

        //If k is bigger than arr.length, this step will give exact no of steps to be taken
        k = k % arr.length;

        for(int i=0; i<k ; i++) {
            int lastValue = arr[arr.length-1];
            for(int j=arr.length-1; j>0 ;j--) {
                arr[j] = arr[j-1];
            }
            arr[0] = lastValue;
        }
        printOutput(arr);
        /*
        * Time Complexity = o(k * n)
        * Space Complexity = o(1)
        * */
    }

    private static void solutionUsingExtraSpace(int[] arr, int k) {
        /*
         * Intent:
         * Create a new Array to copy k elements followed by rest of elements
         * */

        if(k==0 || arr.length <=1) {
            printOutput(arr);
            return;
        }

        //If k is bigger than arr.length, this step will give exact no of steps to be taken
        k = k % arr.length;

        int[] newArray = new int[arr.length];
        int j=0;
        for(int i=arr.length-k; i<arr.length ; i++) {
            newArray[j++] = arr[i];
        }

        for(int i=0; i< arr.length-k; i++) {
            newArray[j++] = arr[i];
        }
        printOutput(newArray);
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    private static void solutionUsingCyclicReplacements(int[] arr, int k) {
        /*
         * Intent:
         *
         * Maintain prev element at current index
         * get nextIndex using formula (currentIndex + k)%arr.length
         * get a temporary variable to store value of nextIndex
         * shift prev element to this nextIndex
         * and now store value in temp variable into prev
         * now currentIndex points to nextIndex and repeat process
         *
         * Repeat above process till arr.length number of times elements are shifted
         * */

        if(k==0 || arr.length <=1) {
            printOutput(arr);
            return;
        }

        //If k is bigger than arr.length, this step will give exact no of steps to be taken
        k = k % arr.length;

        int i=0, count = 0; //To keep track of no of replacements done as we aim to complete job in o(n)
        while(count < arr.length) {
            int prev = arr[i];
            int currentIndex = i;
            do {
                int nextIndex = (currentIndex + k) % arr.length;

                //Shifting logic
                int temp = arr[nextIndex];
                arr[nextIndex] = prev;
                prev = temp;

                currentIndex = nextIndex;
                count++;
            } while(currentIndex != i);
            i++;
        }
        printOutput(arr);
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    private static void solutionUsingReverse(int[] arr, int k) {
        /*
         * Intent:
         * Original List                   : 1 2 3 4 5 6 7
         * After reversing all numbers     : 7 6 5 4 3 2 1
         * After reversing first k numbers : 5 6 7 4 3 2 1
         * After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
         * */

        int n = arr.length;
        //If k is bigger than arr.length, this step will give exact no of steps to be taken
        k = k % arr.length;

        reverse(arr, 0, n-1);
        reverse(arr, 0, k-1);
        reverse(arr, k, n-1);
        printOutput(arr);
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(1)
         * */
    }

    private static void reverse(int[] arr, int start, int end) {
        while(start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    // Helper method to print the array
    private static void printOutput(int[] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    public static void main(String[] args) {
        //Brute Force Solution - o(nk)
        bruteForceSolution(new int[] {1,2,3,4,5,6,7}, 3);
        bruteForceSolution(new int[] {-1,-100,3,99}, 2);
        bruteForceSolution(new int[] {-1,-100,3,99}, 15);
        bruteForceSolution(new int[] {7}, 5);

        //Better Solution using extra space- o(n)
        System.out.println();
        solutionUsingExtraSpace(new int[] {1,2,3,4,5,6,7}, 3);
        solutionUsingExtraSpace(new int[] {-1,-100,3,99}, 2);
        solutionUsingExtraSpace(new int[] {-1,-100,3,99}, 15);
        solutionUsingExtraSpace(new int[] {7}, 5);

        //Better Solution using cyclic replacements (no extra space)- o(n)
        System.out.println();
        solutionUsingCyclicReplacements(new int[] {1,2,3,4,5,6,7}, 3);
        solutionUsingCyclicReplacements(new int[] {-1,-100,3,99}, 2);
        solutionUsingCyclicReplacements(new int[] {-1,-100,3,99}, 15);
        solutionUsingCyclicReplacements(new int[] {7}, 5);

        //Better Solution using solutionUsingReverse (no extra space)- o(n)
        System.out.println();
        solutionUsingReverse(new int[] {1,2,3,4,5,6,7}, 3);
        solutionUsingReverse(new int[] {-1,-100,3,99}, 2);
        solutionUsingReverse(new int[] {-1,-100,3,99}, 15);
        solutionUsingReverse(new int[] {7}, 5);
    }
}
