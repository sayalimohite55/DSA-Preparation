package com.problems.simulation;

import java.util.*;

/*
* Question:
* You are given a 2D 0-indexed array of strings, access_times, with size n. For each i where
* 0 <= i <= n - 1, access_times[i][0] represents the name of an employee, and access_times[i][1]
* represents the access time of that employee. All entries in access_times are within the same day.
* The access time is represented as four digits using a 24-hour time format, for example, "0800" or "2250".
* An employee is said to be high-access if he has accessed the system three or more times within a one-hour
* period. Times with exactly one hour of difference are not considered part of the same one-hour period. For
* example, "0815" and "0915" are not part of the same one-hour period. Access times at the start and end of
* the day are not counted within the same one-hour period. For example, "0005" and "2350" are not part of the
* same one-hour period. Return a list that contains the names of high-access employees with any order you want.
*
* Examples:
*
* Input: access_times = [["a","0549"],["b","0457"],["a","0532"],["a","0621"],["b","0540"]]
* Output: ["a"]
* Explanation: "a" has three access times in the one-hour period of [05:32, 06:31] which are 05:32, 05:49,
* and 06:21. But "b" does not have more than two access times at all. So the answer is ["a"].
*
* Input: access_times = [["d","0002"],["c","0808"],["c","0829"],["e","0215"],["d","1508"],["d","1444"],
* ["d","1410"],["c","0809"]]
* Output: ["c","d"]
* Explanation: "c" has three access times in the one-hour period of [08:08, 09:07] which are 08:08, 08:09,
* and 08:29. "d" has also three access times in the one-hour period of [14:10, 15:09] which are 14:10, 14:44,
* and 15:08. However, "e" has just one access time, so it can not be in the answer and the final answer is
* ["c","d"].
*
* Input: access_times = [["cd","1025"],["ab","1025"],["cd","1046"],["cd","1055"],["ab","1124"],["ab","1120"]]
* Output: ["ab","cd"]
* Explanation: "ab" has three access times in the one-hour period of [10:25, 11:24] which are 10:25, 11:20,
* and 11:24. "cd" has also three access times in the one-hour period of [10:25, 11:24] which are 10:25, 10:46,
* and 10:55. So the answer is ["ab","cd"].
*
* Constraints:
* 1 <= access_times.length <= 100
* access_times[i].length == 2
* 1 <= access_times[i][0].length <= 10
* access_times[i][0] consists only of English small letters.
* access_times[i][1].length == 4
* access_times[i][1] is in 24-hour time format.
* access_times[i][1] consists only of '0' to '9'
* */
public class HighAccessEmployees {
    public static List<String> findHighAccessEmployees(String[][] access_times) {
        /*
        * Intent:
        * Store employee wise access attempts in a map
        * Now sort all timestamps and check if three or more timestamps are coming in a 60 minute window
        * eg:  0815 0856 0912 0922 --> use 100 for comparison
        * */
        Map<String,List<Integer>> accessMap = new HashMap<>();
        for(String[] record: access_times) {
            accessMap.computeIfAbsent(record[0],obj->new ArrayList<>()).add(Integer.parseInt(record[1]));
        }

        List<String> result = new ArrayList<>();
        for(Map.Entry<String,List<Integer>> entry : accessMap.entrySet()){
            String name = entry.getKey();
            List<Integer> timestamps = entry.getValue();

            //sort timestamp list
            Collections.sort(timestamps);
            int n = timestamps.size();
            for(int i=2; i<n ; i++) {
                if(timestamps.get(i) - timestamps.get(i-2) < 100)
                    result.add(name);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Result: "+ findHighAccessEmployees(new String[][]{{"a","0549"},{"b","0457"},
                {"a","0532"},{"a","0621"},{"b","0540"}}));
        System.out.println("Result: "+ findHighAccessEmployees(new String[][]{{"d","0002"},{"c","0808"},
                {"c","0829"},{"e","0215"},{"d","1508"},{"d","1444"},{"d","1410"},{"c","0809"}}));
        System.out.println("Result: "+ findHighAccessEmployees(new String[][]{{"cd","1025"},{"ab","1025"},
                {"cd","1046"},{"cd","1055"},{"ab","1124"},{"ab","1120"}}));

        /*
        * Time Complexity = o(n log n)
        * Space Complexity = o(n)
        * */
    }
}
