package com.problems.arraysAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Question:
* A website domain "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com",
* at the next level, we have "leetcode.com" and at the lowest level, "discuss.leetcode.com".
* When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com"
* and "com" implicitly. A count-paired domain is a domain that has one of the two formats "rep d1.d2.d3"
* or "rep d1.d2" where rep is the number of visits to the domain and d1.d2.d3 is the domain itself.
*
* For example, "9001 discuss.leetcode.com" is a count-paired domain that indicates that discuss.leetcode.com
* was visited 9001 times. Given an array of count-paired domains cpdomains,
* return an array of the count-paired domains of each subdomain in the input. You may return the answer in any order.
* */
public class SubDomainVisitCount {
    public static List<String> subDomainVisits(String[] cpdomains) {
        HashMap<String, Integer> resultMap = new HashMap<>();
        for(String s: cpdomains) {
            String[] str = s.split(" ");
            int count = Integer.parseInt(str[0]);
            while(str[1].contains(".")) {
                resultMap.put(str[1], resultMap.getOrDefault(str[1],0)+count);
                str[1] = str[1].substring(str[1].indexOf(".")+1);
            }
            resultMap.put(str[1], resultMap.getOrDefault(str[1],0)+count);
        }

        List<String> resultList = new ArrayList<>();
        for(Map.Entry<String,Integer> entry: resultMap.entrySet()) {
            resultList.add(entry.getValue() + " " + entry.getKey());
        }
        return resultList;
        /*
        * Time Complexity = o(n * m) --> n length of cpDomain array and m is length of largest string
        * Space Complexity = o(n * m)
        * */
    }

    public static void main(String[] args) {
        System.out.println("Result: "+subDomainVisits(new String[]{"9001 discuss.leetcode.com"}));
        System.out.println("Result: "+subDomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));
    }
}
