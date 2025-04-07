package com.problems.simulation;

import java.util.*;

/*
* Question:
* You are asked to design a file system that allows you to create new paths and associate them with
* different values. The format of a path is one or more concatenated strings of the form: / followed
* by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid
* paths while an empty string "" and "/" are not.
* Implement the FileSystem class:
* bool createPath(string path, int value) Creates a new path and associates a value to it if possible
*           and returns true. Returns false if the path already exists or its parent path doesn't exist.
* int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
*
* Examples:
*Input: ["FileSystem","createPath","get"]   [[],["/a",1],["/a"]]
* Output: [null,true,1]
* Explanation:
*   FileSystem fileSystem = new FileSystem();
*   fileSystem.createPath("/a", 1); // return true
*   fileSystem.get("/a"); // return 1
*
* Input: ["FileSystem","createPath","createPath","get","createPath","get"]  [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
* Output:  [null,true,true,2,false,-1]
* Explanation:
*   FileSystem fileSystem = new FileSystem();
*   fileSystem.createPath("/leet", 1); // return true
*   fileSystem.createPath("/leet/code", 2); // return true
*   fileSystem.get("/leet/code"); // return 2
*   fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
*   fileSystem.get("/c"); // return -1 because this path doesn't exist.
*
* Constraints:
* 2 <= path.length <= 100
* 1 <= value <= 109
* Each path is valid and consists of lowercase English letters and '/'.
* At most 104 calls in total will be made to createPath and get.
* */
public class FileSystem {
    static Map<String, Integer> fileMap = new HashMap<>();

    public static boolean createPath(String path, int value) {
        if(fileMap.containsKey(path)) {
            return false;
        }

        String parent = path.substring(0,path.lastIndexOf("/"));
        if(parent == "" || fileMap.containsKey(parent)) {
            fileMap.put(path, value);
            return true;
        }
        return false;
    }

    public static int get(String path) {
        return fileMap.getOrDefault(path, -1);
    }

    public static void main(String[] args) {
        String[] instructions = new String[]{"createPath","createPath","get","createPath","get"};
        List<List<String>> inputList = new ArrayList<>();
        inputList.add(Arrays.asList("/leet","1"));
        inputList.add(Arrays.asList("/leet/code","2"));
        inputList.add(List.of("/leet/code"));
        inputList.add(Arrays.asList("/c/d","1"));
        inputList.add(List.of("/c/d"));

        int i =0;
        System.out.println();
        for(String instruction: instructions) {
            List<String> list = inputList.get(i++);
            switch(instruction) {
                case "createPath":
                    System.out.print(createPath(list.get(0),Integer.parseInt(list.get(1))) + " ");
                    break;
                case "get":
                    System.out.print(get(list.getFirst()) + " ");
                    break;
            }
        }

        /*
        * Time Complexity = o(n)
        *   - createPath time complexity o(1)
        *   - get time complexity o(1)
        * Space Complexity = o(n)
        * */
    }
}
