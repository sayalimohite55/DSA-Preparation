package com.problems.other;

import java.util.HashMap;
import java.util.Map;

/*
* Question:
* Design a logger system that receives a stream of messages along with their timestamps.
* Each unique message should only be printed at most every 10 seconds
* (i.e. a message printed at timestamp t will prevent other identical messages from being printed until
* timestamp t + 10).
* All messages will come in chronological order. Several messages may arrive at the same timestamp.
* Implement the Logger class:
* - Logger() Initializes the logger object.
* - bool shouldPrintMessage(int timestamp, string message)
*   Returns true if the message should be printed in the given timestamp, otherwise returns false.
* */
public class LoggerRateLimiter {
    private final Map<String, Integer> messageMap;
    public LoggerRateLimiter() {
        messageMap = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if(messageMap.containsKey(message)) {
            int oldTimestamp = messageMap.get(message);

            if(oldTimestamp > timestamp)
                return false;
        }
        messageMap.put(message, timestamp+10);
        return true;
    }

    public static void main(String[] args) {
        //Driver code
        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(1,"foo");
        inputMap.put(2,"bar");
        inputMap.put(3,"foo");
        inputMap.put(8,"bar");
        inputMap.put(10,"foo");
        inputMap.put(11,"foo");

        LoggerRateLimiter logger = new LoggerRateLimiter();
        for(Map.Entry<Integer,String> entry : inputMap.entrySet()) {
            System.out.println(logger.shouldPrintMessage(entry.getKey(),entry.getValue()));
        }
        /*
        * Time Complexity = o(1)
        * Space Complexity = o(n) --> where n is count of instructions
        * */
    }
}
