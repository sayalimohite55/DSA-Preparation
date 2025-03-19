package com.problems.arraysAndStrings;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* You are given a 0-indexed integer array nums.
* A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].
* Return the total number of bad pairs in nums.
* */
public class CountBadPairs {
    private static long countBadPairsBruteForce(int[] nums){
        long count =0;
        int n = nums.length;
        for(int i=0;i<n; i++) {
            for(int j= i+1; j<n; j++) {
                if(j-i != nums[j]-nums[i])
                    count++;
            }
        }
        return count;
        /*
        * Time Complexity = o(n^2)
        * Space Complexity = o(1)
        * */
    }

    private static long countBadPairsUsingHashmap(int[] nums){
        /*
        * Intent:
        * Condition is i<j and j-i != nums[j]-nums[i]
        *                   => nums[i] - i != nums[j]-j
        * So, we will use Hash table to record count of nums[i]-i to identify bad pairs
        * Also, no of bad pairs = no. of total pairs - no. of good pairs
        * where good pair is where nums[i] - i == nums[j] - j
        * */
        long count =0;
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n; i++) {
            int diff = i - nums[i];

            //Get count of pairs with same difference
            int goodPairs = map.getOrDefault(diff,0);

            //no of pairs -no of good pairs = bad pairs at any given position
            count += i - goodPairs;

            //Update value in map
            map.put(diff, goodPairs+1);
        }
        return count;
        /*
         * Time Complexity = o(n)
         * Space Complexity = o(n)
         * */
    }

    public static void main(String args[]) {
        int[] arr1 = new int[] {4,1,3,3};
        int[] arr2 = new int[] {1,2,3,4,5};
        int[] arr3 = new int[] {
                410477557,600836478,610843474,2138417,319471113,319471114,590778757,45733621,763656119,
                375603771,590757522,338820432,505027409,526814356,615033801,338820436,45733630,848336524,
                646274968,763656130,763656131,927494994,691084641,338820444,912705037,366239966,600836503,
                660566025,969488028,126978232,974095102,974095103,50429018,763656144,941145986,50429021,600950666,269769772,115877469,600950669,776667507,897899772,267119604,418515959,759808657,510970149,267119608,29156113,505027445,646274999,382987035,660566049,868818298,72843619,389238208,219762957,188965478,219762959,271739368,375603821,927495033,612443762,126978265,608298354,984811157,941146017,776667533,265768189,969488068,698962124,848336577,115877502,875416360,788157954,382987059,420704404,248279207,897899808,133592492,691084698,410477637,848365910,770106603,72872971,927524379,581351323,615063195,612473110,366269351,897929142,698991467,127007616,29185480,590786927,912734429,291917159,312612885,255301134,255301135,615063208,944782065,510999527,855353110,490364517,600980056,271768737,420733757,56618797,590786942,984840524,298149605,615063220,490364526,941175387,115906867,375633199,157646530,698991494,698991495,526843784,490364534,646304393,610872916,615063232,581351362,581351363,691114067,759838062,612473151,133621865,267149014,188994875,347954580,759838068,526843799,418545373,72873024,771904313,968594154,56618829,698991517,944782106,698991519,610872937,2167880,897929198,763685579,115906900,868847716,319500580,45763086,612473174,600980104,383016460,855353162,505056874,269799213,788187360,319500589,875445769,610872954,759838096,600980114,366269426,188994908,375633249,219792390,855353175,771904344,319500600,859990376,791136864,941175446,600980125,855353182,133621911,248308629,875445787,490364592,698991556,590787014,338849924,925188662,269799240,984840599,848366014,271768818,791136880,723097022,912734524,925188670,941175465,763685625,271768825,590808267,868847763,759838131,600980149,984840613,974124593,723097034,490364615,610872996,848366032,944782169,897929258,366269469,};
        //Brute Force Solution - o(n^2)
        System.out.println("Count: "+countBadPairsBruteForce(arr1));
        System.out.println("Count: "+countBadPairsBruteForce(arr2));
        System.out.println("Count: "+countBadPairsBruteForce(arr3));

        //Optimised Solution using HashMap - o(n)
        System.out.println("Count: "+countBadPairsUsingHashmap(arr1));
        System.out.println("Count: "+countBadPairsUsingHashmap(arr2));
        System.out.println("Count: "+countBadPairsUsingHashmap(arr3));
    }
}

