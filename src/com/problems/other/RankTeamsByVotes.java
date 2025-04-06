package com.problems.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
* Question:
* In a special ranking system, each voter gives a rank from highest to lowest to all teams participating
* in the competition. The ordering of teams is decided by who received the most position-one votes.
* If two or more teams tie in the first position, we consider the second position to resolve the conflict,
* if they tie again, we continue this process until the ties are resolved.
* If two or more teams are still tied after considering all positions, we rank them alphabetically based on
* their team letter.
* You are given an array of strings votes which is the votes of all voters in the ranking systems.
* Sort all teams according to the ranking system described above.
* Return a string of all teams sorted by the ranking system.
*
* Examples:
*
* Input: votes = ["ABC","ACB","ABC","ACB","ACB"]    Output: "ACB"
* Explanation:
* Team A was ranked first place by 5 voters. No other team was voted as first place, so team A is the first team.
* Team B was ranked second by 2 voters and ranked third by 3 voters.
* Team C was ranked second by 3 voters and ranked third by 2 voters.
* As most of the voters ranked C second, team C is the second team, and team B is the third.
*
* Input: votes = ["WXYZ","XYZW"]    Output: "XWYZ"
* Explanation:
* X is the winner due to the tie-breaking rule. X has the same votes as W for the first position, but X has
* one vote in the second position, while W does not have any votes in the second position.
*
* Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]     Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
* Explanation: Only one voter, so their votes are used for the ranking.
*
* Constraints:
* 1 <= votes.length <= 1000
* 1 <= votes[i].length <= 26
* votes[i].length == votes[j].length for 0 <= i, j < votes.length.
* votes[i][j] is an English uppercase letter.
* All characters of votes[i] are unique.
* All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
* */
public class RankTeamsByVotes {
    public static String rankTeams(String[] votes) {
        /*
        * Intent:
        * Create a hashmap to store team wise ranking
        * Sort rankings
        * Return sequence of teams in a string
        * */
        if(votes.length == 1) // only one person voted
            return votes[0];

        HashMap<Character, int[]> votesMap = new HashMap<>();
        int n = votes[0].length();
        //Map all votes w.r.t. teams
        for(String vote: votes) {
            for(int i =0;i<n; i++) {
                char ch = vote.charAt(i);
                votesMap.computeIfAbsent(ch, obj -> new int[n])[i]++;
            }
        }

        List<Character> teams = new ArrayList<>(votesMap.keySet());
        //Sort teams based on votes
        Collections.sort(teams,(a,b) -> {
            for(int i=0;i<n;i++) {
                //Sort based on no of votes
                if(votesMap.get(a)[i] != votesMap.get(b)[i]) {
                    return votesMap.get(b)[i] -  votesMap.get(a)[i];
                }
            }
            //If its a tie, refer to the alphabetical order
            return a-b;
        });

        StringBuilder result = new StringBuilder();
        for(char ch : teams) {
            result.append(ch);
        }
        return result.toString();
        /*
        * Time Complexity = o(n * m * log m)
        * Space Complexity = o(n * m)
        * where n is no of votes and m is no of teams
        * */
    }

    public static void main(String[] args) {
        //Solution: o(n * m log m)
        System.out.println("Result: "+rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"}));
        System.out.println("Result: "+rankTeams(new String[]{"WXYZ","XYZW"}));
        System.out.println("Result: "+rankTeams(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"}));
    }
}
