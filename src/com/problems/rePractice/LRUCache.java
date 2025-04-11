package com.problems.rePractice;

import java.util.*;

/*
* Question: LRU Cache
* Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
* Implement the LRUCache class:
* LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
* int get(int key) Return the value of the key if the key exists, otherwise return -1.
* void put(int key, int value) Update the value of the key if the key exists.
*   Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity
*   from this operation, evict the least recently used key.
* The functions get and put must each run in O(1) average time complexity.
*
* Example:
* Input ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
*       [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
* Output        [null, null, null, 1, null, -1, null, -1, 3, 4]
* Explanation
* LRUCache lRUCache = new LRUCache(2);
* lRUCache.put(1, 1); // cache is {1=1}
* lRUCache.put(2, 2); // cache is {1=1, 2=2}
* lRUCache.get(1);    // return 1
* lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
* lRUCache.get(2);    // returns -1 (not found)
* lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
* lRUCache.get(1);    // return -1 (not found)
* lRUCache.get(3);    // return 3
* lRUCache.get(4);    // return 4
*
* Constraints:
* 1 <= capacity <= 3000
* 0 <= key <= 104
* 0 <= value <= 105
* At most 2 * 105 calls will be made to get and put.
*/
public class LRUCache {
    private static class Node{
        int key;
        int value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private final Map<Integer, Node> cache;
    Node head, tail;
    private final int size;

    public LRUCache(int capacity) {
        this.size = capacity;
        this.cache = new HashMap<>(size);
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            this.removeNode(node);
            this.addRecentNode(node);
            return node.value;
        }
        return -1;
    }

    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void addRecentNode(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void put(int key, int value) {
        if(this.size == cache.size()) {
            Node leastRecentlyUsedNode = tail.prev;
            int leastRecentlyUsedKey = leastRecentlyUsedNode.key;
            this.removeNode(leastRecentlyUsedNode);
            cache.remove(leastRecentlyUsedKey);
        }

        Node newNode;
        if(cache.containsKey(key)) {
            newNode = cache.get(key);
            this.removeNode(newNode);
        } else {
            newNode = new Node(key, value);
        }
        this.addRecentNode(newNode);
        cache.put(key, newNode);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.print(lRUCache.get(1)+ " ");    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.print(lRUCache.get(2)+ " ");    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.print(lRUCache.get(1)+ " ");    // return -1 (not found)
        System.out.print(lRUCache.get(3)+ " ");    // return 3
        System.out.print(lRUCache.get(4)+ " ");    // return 4
        /*
         * Time Complexity = o(1)
         * Space Complexity = o(n)
         * */
    }
}
