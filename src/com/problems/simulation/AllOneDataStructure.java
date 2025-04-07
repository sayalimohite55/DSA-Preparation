package com.problems.simulation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* Question:
* Design a data structure to store the strings' count with the ability to
* return the strings with minimum and maximum counts.
* Implement the AllOne class:
*   AllOne() Initializes the object of the data structure.
*   inc(String key) Increments the count of the string key by 1.
*                   If key does not exist in the data structure, insert it with count 1.
*   dec(String key) Decrements the count of the string key by 1.
*                   If the count of key is 0 after the decrement, remove it from the data structure.
*                   It is guaranteed that key exists in the data structure before the decrement.
*   getMaxKey() Returns one of the keys with the maximal count. If no element exists,
*               return an empty string "".
*   getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
* Note that each function must run in O(1) average time complexity.
*
* Example:
*
* Input ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
*       [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
* Output [null, null, null, "hello", "hello", null, "hello", "leet"]
* Explanation
* AllOne allOne = new AllOne();
* allOne.inc("hello");
* allOne.inc("hello");
* allOne.getMaxKey(); // return "hello"
* allOne.getMinKey(); // return "hello"
* allOne.inc("leet");
* allOne.getMaxKey(); // return "hello"
* allOne.getMinKey(); // return "leet"
*
* Constraints:
* 1 <= key.length <= 10
* key consists of lowercase English letters.
* It is guaranteed that for each call to dec, key is existing in the data structure.
* At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
* */

public class AllOneDataStructure {
    static class Node {
        int frequency;
        Set<String> keys;
        Node prev, next;

        public Node(int frequency){
            this.frequency = frequency;
            this.keys = new HashSet<>();
            this.prev = null;
            this.next = null;
        }
    }

    Map<String, Node> map;
    Node head;
    Node tail;

    public AllOneDataStructure() {
        this.map = new HashMap<>();
        this.head = new Node(0);
        this.tail = new Node(0);

        head.next = tail;
        tail.prev = head;
    }

    public void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public void inc(String key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            int frequency = node.frequency + 1;
            node.keys.remove(key);

            Node nextNode = node.next;
            if(nextNode == tail || nextNode.frequency > frequency) {
                //create new node
                Node newNode = new Node(frequency);
                newNode.keys.add(key);

                node.next = newNode;
                newNode.next = nextNode;
                nextNode.prev = newNode;
                newNode.prev = node;
                map.put(key, newNode);
            } else {
                nextNode.keys.add(key);
                map.put(key,nextNode);
            }

            if(node.keys.isEmpty()) {
                removeNode(node);
            }
        } else {
            Node node = head.next;
            if(node == tail || node.frequency > 1) {
                Node newNode = new Node(1);
                newNode.keys.add(key);

                head.next = newNode;
                newNode.next = node;
                node.prev = newNode;
                newNode.prev = head;
                map.put(key, newNode);
            } else {
                node.keys.add(key);
                map.put(key, node);
            }
        }

    }

    public void dec(String key) {
        Node node = map.get(key);
        node.keys.remove(key);
        int frequency = node.frequency - 1;

        Node prevNode = node.prev;
        if(frequency == 0) {
            map.remove(key);
        } else if(prevNode == head || prevNode.frequency < frequency) {
            //Add new node
            Node newNode = new Node(frequency);
            newNode.keys.add(key);

            newNode.next = prevNode.next;
            newNode.next.prev = newNode;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            map.put(key,newNode);
        } else {
            prevNode.keys.add(key);
            map.put(key, prevNode);
        }

        if(node.keys.isEmpty()) {
            removeNode(node);
        }
    }

    public String getMaxKey() {
        if(tail.prev == head)
            return "";
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if(head.next == tail)
            return "";
        return head.next.keys.iterator().next();
    }

    public static void main(String[] args) {
        //Example
        AllOneDataStructure allOne = new AllOneDataStructure();
        String[] instructions = new String[]{"inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"};
        String[] keys = new String[]{"hello", "hello","leet"};

        System.out.println("\nResult: ");
        int i=0;
        for(String instruction: instructions) {
            switch(instruction) {
                case "inc":
                    allOne.inc(keys[i++]);
                    break;
                case "dec":
                    allOne.dec(keys[i++]);
                    break;
                case "getMaxKey":
                    System.out.print(allOne.getMaxKey()+" ");
                    break;
                case "getMinKey":
                    System.out.print(allOne.getMinKey()+" ");
                    break;
            }
        }
        /*
        * Time Complexity:
        *   increament operation : 0(1)
        *   decreament operation : 0(1)
        *   getMinKey  operation: 0(1)
        *   getMaxKey operation : 0(1)
        * This assumes that map operations typically run in "average-case Θ(1)".
        * However, in the worst case, where many hash collisions occur, these operations can degrade to O(N).
        *
        * Space Complexity = o(n)
        * The space used by the AllOne data structure is primarily due to the hash map and the linked list of Nodes.
        * The hash map stores pointers to nodes for each unique key, requiring O(N) space where N is the number
        * of unique keys.
        * */
    }
}
