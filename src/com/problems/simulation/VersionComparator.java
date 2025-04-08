package com.problems.simulation;

import java.util.Arrays;
import java.util.Comparator;

/*
* Question:
* Construct a comparator that can be used to sort an array of version number strings in ascending order
* e.g. versionSort(new String[]{"4.5.1","10","0.99","4.5","1.0.0.1"} => ["0.99","1.0.0.1","4.5","4.5.1","10"]
* */
public class VersionComparator {
    public static void sortAscending(String[] versions) {
        Arrays.sort(versions, new Comparator<String>() {
            @Override
            public int compare(String v1, String v2) {
                String[] parts1 = v1.split("\\.");
                String[] parts2 = v2.split("\\.");

                int l1 = parts1.length;
                int l2 = parts2.length;
                int length = Math.max(l1,l2);

                for(int i=0; i<length;i++){
                    int m = (i < l1) ? Integer.parseInt(parts1[i]) : 0;
                    int n = i < l2 ? Integer.parseInt(parts2[i]) : 0;

                    if(m < n)
                        return -1;
                    else if(m > n)
                        return 1;
                    //else m == n in which case we will compare next part of the version string
                }
                return 0;
            }
        });
    }

    public static void main(String[] args) {
        String[] versions = new String[]{"4.5.1","10","0.99","4.5","1.0.0.1"};
        sortAscending(versions);
        System.out.println("Sorted Array: "+ Arrays.toString(versions));
        /*
        * Time Complexity = o(m * n)
        * Space Complexity = o(n)
        * where m is length of array versions and n is max length of arrays parts1 and parts2
        * */
    }
}
