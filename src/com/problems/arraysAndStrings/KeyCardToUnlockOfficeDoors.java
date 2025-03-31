package com.problems.arraysAndStrings;

import java.util.*;

/*
* Question:
* LeetCode company workers use key-cards to unlock office doors. Each time a worker uses their key-card,
* the security system saves the worker's name and the time when it was used. The system emits an alert if
* any worker uses the key-card three or more times in a one-hour period. You are given a list of strings
* keyName and keyTime where [keyName[i], keyTime[i]] corresponds to a person's name and the time when their
* key-card was used in a single day. Access times are given in the 24-hour time format "HH:MM", such as "23:51"
* and "09:49". Return a list of unique worker names who received an alert for frequent keycard use.
* Sort the names in ascending order alphabetically. Notice that "10:00" - "11:00" is considered to be within a
* one-hour period, while "22:51" - "23:52" is not considered to be within a one-hour period.
*
* Example:
* Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"],
*        keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
* Output: ["daniel"]
* Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").
* */
public class KeyCardToUnlockOfficeDoors {

    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, TreeSet<Integer>> map = new HashMap<>();
        int n = keyName.length;
        for(int i =0; i<n; i++) {
            int time = Integer.parseInt(keyTime[i].substring(0,2)) * 60
                    + Integer.parseInt(keyTime[i].substring(3));
            map.computeIfAbsent(keyName[i],k->new TreeSet<>()).add(time);
        }

        TreeSet<String> result = new TreeSet<>();
        for(Map.Entry<String,TreeSet<Integer>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<Integer> list = new ArrayList<>(entry.getValue());
            n = list.size();
            for(int i =2; i<n; i++) {
                if(list.get(i) - list.get(i-2) <= 60) {
                    result.add(key);
                    break;
                }
            }
        }
        return new ArrayList<>(result);
        /*
        * Time Complexity = o(n log n)
        * Space Complexity = o(n)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result: "+
                alertNames( new String[]{"daniel","daniel","daniel","luis","luis","luis","luis"},
                            new String[]{"10:00","10:40","11:00","09:00","11:00","13:00","15:00"}));
        System.out.println("Result: "+
                alertNames( new String[]{"alice","alice","alice","bob","bob","bob","bob"},
                        new String[]{"12:01","12:00","18:00","21:00","21:20","21:30","23:00"}));
    }
}
